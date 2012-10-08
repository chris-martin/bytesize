package org.codeswarm.bytesize;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.ResourceBundle;

public abstract class AbstractByteSizeFormat implements ByteSizeFormat {

  protected abstract NumberFormat getNumberFormat();

  protected abstract ResourceBundle getResourceBundle();

  protected abstract Collection<ByteSizeUnit> getUnits();

  protected abstract ByteSizeUnitSystem getUnitSystem();

  public String format( ByteSize byteSize, WordLength wordLength ) {
    ByteSizeUnit unit = ByteSizeFormats.determineReasonableUnit( byteSize, getUnitSystem() );
    return format( byteSize, unit, wordLength );
  }

  public String format( ByteSize byteSize, ByteSizeUnit unit, WordLength wordLength ) {
    return ByteSizeFormats.format( byteSize, unit, getNumberFormat(), wordLength, getResourceBundle() );
  }

  public ByteSize parse( String s ) throws ParseException {
    return ByteSizeFormats.parse( s, getNumberFormat(), getUnits(), getResourceBundle() );
  }

}
