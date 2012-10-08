package org.codeswarm.bytesize;

import org.codeswarm.bytesize.ByteSizeFormat.WordLength;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.*;

import static org.codeswarm.bytesize.ByteSizes.byteSize;

public final class ByteSizeFormats {

  private ByteSizeFormats() {}

  public static final String RESOURCE_BUNDLE_NAME =
    "org.codeswarm.bytesize.Units";

  public enum WordType { SINGLE, PLURAL, ABBREVIATION }

  public static ResourceBundle getResourceBundle( Locale locale ) {

    return ResourceBundle.getBundle( RESOURCE_BUNDLE_NAME, locale );
  }

  public static String unitWord(
      ByteSizeUnit unit,
      WordType wordType,
      ResourceBundle resourceBundle ) {

    String resourceKey = unit.unitName()
      + " " + wordType.toString().toLowerCase();
    return resourceBundle.getString( resourceKey );
  }

  public static String format(
      ByteSize byteSize,
      ByteSizeUnit unit,
      NumberFormat numberFormat,
      WordLength wordLength,
      ResourceBundle resourceBundle ) {

    WordType wordType;
    if ( wordLength == WordLength.ABBREVIATION ) {
      wordType = WordType.ABBREVIATION;
    } else {
      if ( numberFormat.getMaximumFractionDigits() == 0
           && Math.round( byteSize.numberOfBytes( unit ) ) == 1 ) {
        wordType = WordType.SINGLE;
      } else {
        wordType = WordType.PLURAL;
      }
    }
    return numberFormat.format( byteSize.numberOfBytes( unit ) )
      + " " + unitWord( unit, wordType, resourceBundle );
  }

  public static ByteSizeUnit determineReasonableUnit(
      ByteSize size,
      ByteSizeUnitSystem unitSystem ) {

    Iterator<ByteSizeUnit> units = unitSystem.units().iterator();
    ByteSizeUnit unit, previousUnit;
    try {
      unit = units.next();
    } catch (NoSuchElementException e) {
      throw new IllegalArgumentException("Unit system has no units");
    }
    while (units.hasNext()) {
      previousUnit = unit;
      unit = units.next();
      if (unit.compareTo(size) > 0) {
        return previousUnit;
      }
    }
    return unit;
  }

  public static ByteSize parse(
      final String s,
      NumberFormat numberFormat,
      Collection<ByteSizeUnit> units,
      ResourceBundle resourceBundle )
      throws ParseException {

    String $ = s.trim();

    class UnitWord implements Comparable<UnitWord> {

      final ByteSizeUnit unit;
      final String word;

      UnitWord(ByteSizeUnit unit, String word) {
        this.unit = unit;
        this.word = word;
      }

      public int compareTo(UnitWord o) {
        // sort the unit string list by string length descending - this prevents
        // us from finding "bytes" when we should have found "kilobytes"
        return o.word.compareTo(word);
      }
    }
    List<UnitWord> unitWords = new ArrayList<UnitWord>();
    for (ByteSizeUnit unit : units) {
      for (WordType wordType : WordType.values()) {
        String word = unitWord(unit, wordType, resourceBundle);
        unitWords.add(new UnitWord(unit, word));
      }
    }

    Collections.sort(unitWords);

    // default to byte if parsing does not find a unit
    ByteSizeUnit unit = ByteSizeUnits.BYTE;

    // find a unit and remove it from the parsed string
    for ( UnitWord unitWord : unitWords ) {
      if ($.endsWith(unitWord.word)) {
        unit = unitWord.unit;
        $ = $.substring(0, $.length() - unitWord.word.length()).trim();
        break;
      }
    }

    // enable BigDecimal parsing
    if (numberFormat instanceof DecimalFormat) {
      numberFormat = (NumberFormat) numberFormat.clone();
      DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
      decimalFormat.setParseBigDecimal(true);
    }

    // parse the number, throwing ParseException upon failure
    Number n;
    {
      ParsePosition parsePosition = new ParsePosition(0);
      n = numberFormat.parse($, parsePosition);
      int parsePositionIndex = parsePosition.getIndex();
      if (parsePositionIndex != $.length()) {
        throw new ParseException(s, parsePositionIndex);
      }
    }

    // constuct a ByteSize, preferring ExactByteSize if n is an integer
    if (n instanceof BigDecimal) {
      BigDecimal bigDecimal = (BigDecimal) n;
      try {
        // coerce decimal to integer
        BigInteger bigInteger = bigDecimal.toBigIntegerExact();
        return byteSize(bigInteger, unit);
      } catch (ArithmeticException ignored) {
        // exception indicates that bigDecimal has a fractional part
      }
      return byteSize(bigDecimal.doubleValue(), unit);
    } else if (n instanceof Long) {
      return byteSize((Long) n, unit);
    } else {
      return byteSize(n.doubleValue(), unit);
    }
  }

}
