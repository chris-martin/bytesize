package org.codeswarm.bytesize;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ByteSizeFormatBuilder {

  private ByteSizeUnitSystem unitSystem;
  private Collection<ByteSizeUnit> units;
  private Map<String, ByteSizeUnitSystem> unitSystems;
  private Locale locale;
  private ResourceBundle resourceBundle;
  private NumberFormat numberFormat;

  public ByteSizeFormatBuilder locale( Locale locale ) {
    this.locale = locale;
    return this;
  }

  public ByteSizeFormatBuilder resourceBundle( ResourceBundle resourceBundle ) {
    this.resourceBundle = resourceBundle;
    return this;
  }

  public ByteSizeFormatBuilder unitSystem( ByteSizeUnitSystem unitSystem ) {
    this.unitSystem = unitSystem;
    return this;
  }

  public ByteSizeFormatBuilder unitSystems( Map<String, ByteSizeUnitSystem> unitSystems ) {
    this.unitSystems = unitSystems;
    return this;
  }

  public ByteSizeFormatBuilder units( Collection<ByteSizeUnit> units ) {
    this.units = units;
    return this;
  }

  public ByteSizeFormatBuilder numberFormat( NumberFormat numberFormat ) {
    this.numberFormat = numberFormat;
    return this;
  }

  public ByteSizeFormat build() {
    Impl impl = new Impl();
    impl.numberFormat = getNumberFormat();
    impl.resourceBundle = getResourceBundle();
    impl.units = getUnits();
    impl.unitSystem = getUnitSystem();
    return impl;
  }

  private NumberFormat getNumberFormat() {
    if ( numberFormat != null ) {
      return numberFormat;
    }
    return NumberFormat.getInstance( getLocale() );
  }

  private Locale getLocale() {
    if ( locale != null ) {
      return locale;
    }
    return Locale.getDefault();
  }

  private ResourceBundle getResourceBundle() {
    if ( resourceBundle != null ) {
      return resourceBundle;
    }
    return ByteSizeFormats.getResourceBundle( getLocale() );
  }

  private Collection<ByteSizeUnit> getUnits() {
    if ( units != null ) {
      return units;
    }
    if ( unitSystems != null ) {
      return ByteSizeUnits.getUnitsForSystems( unitSystems.values() );
    }
    return ByteSizeUnits.getAllDefaultUnits();
  }

  private ByteSizeUnitSystem getUnitSystem() {
    if ( unitSystem != null ) {
      return unitSystem;
    }
    if ( unitSystems != null ) {
      return ByteSizeUnits.getDefaultUnitSystem( getResourceBundle(), unitSystems );
    }
    return ByteSizeUnits.getDefaultUnitSystem( getResourceBundle() );
  }

  static class Impl extends AbstractByteSizeFormat {

    private NumberFormat numberFormat;
    private ResourceBundle resourceBundle;
    private Collection<ByteSizeUnit> units;
    private ByteSizeUnitSystem unitSystem;

    protected NumberFormat getNumberFormat() {
      return numberFormat;
    }

    protected ResourceBundle getResourceBundle() {
      return resourceBundle;
    }

    protected Collection<ByteSizeUnit> getUnits() {
      return units;
    }

    protected ByteSizeUnitSystem getUnitSystem() {
      return unitSystem;
    }

  }

}
