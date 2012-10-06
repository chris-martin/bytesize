package org.codeswarm.bytesize;

import java.util.Locale;
import java.util.ResourceBundle;

public class ByteSizeStrings {

  public static final String RESOURCE_BUNDLE_NAME = "org.codeswarm.bytesize.Units";

  public enum WordType {SINGLE, PLURAL, ABBREVIATION }

  public static ResourceBundle getResourceBundle() {
    return getResourceBundle(Locale.getDefault());
  }

  public static ResourceBundle getResourceBundle(Locale locale) {
    return ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
  }

  public static String unitWord(ByteSizeUnit unit, WordType wordType) {
    return unitWord(unit, wordType, getResourceBundle());
  }

  public static String unitWord(ByteSizeUnit unit, WordType wordType, Locale locale) {
    return unitWord(unit, wordType, getResourceBundle(locale));
  }

  public static String unitWord(ByteSizeUnit unit, WordType wordType, ResourceBundle resourceBundle) {
    return resourceBundle.getString(String.format("%s %s", unit.unitName(), wordType.toString().toLowerCase()));
  }

}
