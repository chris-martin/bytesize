package org.codeswarm.bytesize;

import org.codeswarm.bytesize.ByteSizeFormat.WordLength;
import org.codeswarm.bytesize.ByteSizeFormats.WordType;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.lang.Math.pow;
import static org.codeswarm.bytesize.ByteSizeUnits.*;
import static org.codeswarm.bytesize.ByteSizes.byteSize;
import static org.testng.Assert.assertEquals;

public class ByteSizeFormatsTest {

  /**
   * Get "terabyte" for US locale.
   */
  @Test
  public void testUnitWordUS() {
    ResourceBundle resourceBundle = ByteSizeFormats.getResourceBundle( Locale.US );
    String word = ByteSizeFormats.unitWord( TB, WordType.SINGLE, resourceBundle );
    assertEquals( word, "terabyte" );
  }

  /**
   * Get byte abbreviation for Slovak locale.
   */
  @Test
  public void testByteSlovak() {
    Locale locale = Locale.forLanguageTag( "sk" );
    ResourceBundle resourceBundle = ByteSizeFormats.getResourceBundle( locale );
    String word = ByteSizeFormats.unitWord( BYTE, WordType.ABBREVIATION, resourceBundle );
    assertEquals( word, "b" );
  }

  /**
   * "gigabytes" in Esperanto is "gigabajtoj".
   */
  @Test
  public void testUnitWordEsperanto() {
    Locale locale = Locale.forLanguageTag( "eo" );
    ResourceBundle resourceBundle = ByteSizeFormats.getResourceBundle( locale );
    String word = ByteSizeFormats.unitWord( GB, WordType.PLURAL, resourceBundle );
    assertEquals( word, "gigabajtoj" );
  }

  @Test
  public void testFormat4MB() {
    ResourceBundle resourceBundle = ByteSizeFormats.getResourceBundle( Locale.US );
    NumberFormat numberFormat = new DecimalFormat( "#,###.#" );
    ByteSize size = byteSize( 4, MB );
    String str = ByteSizeFormats.format( size, kB, numberFormat, WordLength.FULL, resourceBundle );
    assertEquals( str, "4,000 kilobytes" );
  }

  @Test
  public void testFormat1byte() {
    ResourceBundle resourceBundle = ByteSizeFormats.getResourceBundle( Locale.US );
    NumberFormat numberFormat = new DecimalFormat( "#" );
    ByteSize size = byteSize( 1.2, BYTE );
    String str = ByteSizeFormats.format( size, BYTE, numberFormat, WordLength.FULL, resourceBundle );
    assertEquals( str, "1 byte" );
  }

  @Test
  public void testFormat1b() {
    Locale locale = Locale.forLanguageTag( "eo" );
    ResourceBundle resourceBundle = ByteSizeFormats.getResourceBundle( locale );
    NumberFormat numberFormat = new DecimalFormat( "#" );
    ByteSize size = byteSize( 1.2, BYTE );
    String str = ByteSizeFormats.format( size, BYTE, numberFormat, WordLength.ABBREVIATION, resourceBundle );
    assertEquals( str, "1 b" );
  }

  @Test
  public void testParse1() throws ParseException {
    ResourceBundle resourceBundle = ByteSizeFormats.getResourceBundle( Locale.US );
    NumberFormat numberFormat = NumberFormat.getInstance( Locale.US );
    Collection<ByteSizeUnit> units = ByteSizeUnits.getAllDefaultUnits();
    String s = "1";
    ByteSize byteSize = ByteSizeFormats.parse( s, numberFormat, units, resourceBundle );
    assertEquals( byteSize, byteSize( 1 ) );
  }

  @Test
  public void testParseAbbrDecimalWithSpaces() throws ParseException {
    ResourceBundle resourceBundle = ByteSizeFormats.getResourceBundle( Locale.US );
    NumberFormat numberFormat = NumberFormat.getInstance( Locale.US );
    Collection<ByteSizeUnit> units = ByteSizeUnits.getAllDefaultUnits();
    String s = " 3.6 GiB ";
    ByteSize byteSize = ByteSizeFormats.parse( s, numberFormat, units, resourceBundle );
    assertEquals( byteSize, byteSize( 3.6, GiB ) );
  }

  @Test
  public void testParseWordWithoutSpace() throws ParseException {
    ResourceBundle resourceBundle = ByteSizeFormats.getResourceBundle( Locale.US );
    NumberFormat numberFormat = NumberFormat.getInstance( Locale.US );
    Collection<ByteSizeUnit> units = ByteSizeUnits.getAllDefaultUnits();
    String s = "4kilobyte";
    ByteSize byteSize = ByteSizeFormats.parse( s, numberFormat, units, resourceBundle );
    assertEquals( byteSize, byteSize( 4, kB ) );
  }

  @Test
  public void testDetermineReasonableUnitByte() {
    ByteSizeUnit unit = ByteSizeFormats.determineReasonableUnit( byteSize( 6 ), SI );
    assertEquals( unit, ByteSizeUnits.BYTE );
  }

  @Test
  public void testDetermineReasonableUnitMB() {
    ByteSizeUnit unit = ByteSizeFormats.determineReasonableUnit( byteSize( 6000000 ), SI );
    assertEquals( unit, ByteSizeUnits.MB );
  }

  @Test
  public void testDetermineReasonableUnitYiB() {
    ByteSizeUnit unit = ByteSizeFormats.determineReasonableUnit( byteSize( pow( 2, 120 ) ), IEC );
    assertEquals( unit, ByteSizeUnits.YiB );
  }

}
