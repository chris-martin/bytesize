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
      .locale( Locale.forLanguageTag( "eo" ) )
      .unitSystem( ByteSizeUnits.IEC )
      .numberFormat( new DecimalFormat( "#,###.##" ) )
      .build();
  }

  @Test
  public void test1() {
    String string = esperantoIEC.format( byteSize( 8, MiB ), ABBREVIATION );
    assertEquals( string, "8 MiB" );
  }

  @Test
  public void test2() {
    String string = esperantoIEC.format( byteSize( 8, MiB ), FULL );
    assertEquals( string, "8 mebibajtoj" );
  }

  @Test
  public void test3() {
    String string = esperantoIEC.format( byteSize( 1024, MiB ), FULL );
    assertEquals( string, "1 gibibajto" );
  }

  @Test
  public void test4() {
    String string = esperantoIEC.format( byteSize( 1280, MiB ), FULL );
    assertEquals( string, "1.25 gibibajtoj" );
  }

  @Test
  public void test5() throws ParseException {
    ByteSize byteSize = esperantoIEC.parse( "1.25 gibibajtoj" );
    assertTrue( byteSize.compareTo( byteSize( 1280, MiB ) ) == 0 );
  }

}
