package org.codeswarm.bytesize;

import java.math.BigInteger;

class SingleByteUnit implements ByteSizeUnit {

  public BigInteger numberOfBytes() {
    return BigInteger.ONE;
  }

  public double numberOfBytes( ExactByteSize unit ) {
    double retval = 1;
    if ( ! unit.numberOfBytes().equals( BigInteger.ONE ) ) {
      retval /= unit.numberOfBytes().doubleValue();
    }
    return retval;
  }

  public String unitName() {
    return "0";
  }

  public int compareTo(ByteSize o) {
    if (o instanceof ExactByteSize) {
      return BigInteger.ONE.compareTo(((ExactByteSize) o).numberOfBytes());
    } else {
      return Double.compare(1, o.numberOfBytes(ByteSizeUnits.BYTE));
    }
  }

}
