package org.codeswarm.bytesize;

import static org.codeswarm.bytesize.ByteSizeFormat.WordLength.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

import static org.codeswarm.bytesize.ByteSizes.byteSize;
import static org.testng.Assert.assertEquals;
import static org.codeswarm.bytesize.ByteSizeUnits.*;
import static org.testng.Assert.assertTrue;

public class ByteSizeFormatBuilderTest {

  ByteSizeFormat esperantoIEC;

  @BeforeMethod
  public void setUp() {
    esperantoIEC = new ByteSizeFormatBuilder()
      .locale(Locale.forLanguageTag("eo"))
      .unitSystem(ByteSizeUnits.IEC)
      .numberFormat(new DecimalFormat("#,###.##"))
      .build();
  }

  @Test
  public void test1() {
    assertEquals(esperantoIEC.format(byteSize(8, MiB), ABBREVIATION), "8 MiB");
  }

  @Test
  public void test2() {
    assertEquals(esperantoIEC.format(byteSize(8, MiB), FULL), "8 mebibajtoj");
  }

  @Test
  public void test3() {
    assertEquals(esperantoIEC.format(byteSize(1024, MiB), FULL), "1 gibibajto");
  }

  @Test
  public void test4() {
    assertEquals(esperantoIEC.format(byteSize(1280, MiB), FULL), "1.25 gibibajtoj");
  }

  @Test
  public void test5() throws ParseException {
    assertTrue(esperantoIEC.parse("1.25 gibibajtoj").compareTo(byteSize(1280, MiB)) == 0);
  }

}
