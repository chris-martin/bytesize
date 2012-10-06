package org.codeswarm.bytesize;

import org.testng.annotations.Test;

import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.round;
import static org.codeswarm.bytesize.ByteSizes.*;
import static org.testng.Assert.assertEquals;

public class ByteSizeTest {

  /**
   * 67 MB is equal to 67000 kB.
   */
  @Test
  public void testUnitConversion() {
    ExactByteSize bs = byteSize(67, MB);
    assertEquals(round(bs.numberOfBytes(kB)), 67000);
  }

  /**
   * 31 GiB is equal to 31 * 2^30 bytes.
   */
  @Test
  public void testExactByteSizeWithUnit() {
    ExactByteSize bs = byteSize(31, GiB);
    assertEquals(
      bs.numberOfBytes().longValue(),
      31L * 1024 * 1024 * 1024);
  }

  /**
   * If we construct a {@link ByteSize} with a unit and retrieve
   * its value in the same unit, no math should be performed, so
   * there should be no floating point error.
   */
  @Test
  public void testFloatingPoint() {
    double x = 73 * 119 * Math.PI;
    ByteSize bs = byteSize(x, KiB);
    assertEquals(bs.numberOfBytes(KiB), x);
  }

  /**
   * 2^77 YiB is approximately 2^(80+77-20) MiB.
   */
  @Test
  public void testLargeNumbers() {
    ByteSize bs = byteSize(pow(2, 77), yobibyte);
    assertEquals(round(
      log(bs.numberOfBytes(mebibyte)) / log(2)
    ), 80 + 77 - 20);
  }

}
