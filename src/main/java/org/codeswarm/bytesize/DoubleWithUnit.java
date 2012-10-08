package org.codeswarm.bytesize;

import java.math.BigInteger;

class DoubleWithUnit implements ByteSize {

  private final double n;
  private final ExactByteSize unit;

  public DoubleWithUnit(double n, ExactByteSize unit) {
    this.n = n;
    this.unit = unit;
  }

  public double numberOfBytes( ExactByteSize unit ) {
    double retval = n;
    if ( ! this.unit.equals( unit ) ) {
      retval *= this.unit.numberOfBytes().doubleValue() / unit.numberOfBytes().doubleValue();
    }
    return retval;
  }

  public int compareTo(ByteSize o) {
    return Double.compare(numberOfBytes(unit), o.numberOfBytes(unit));
  }

  public String toString() {
    BigInteger numberOfBytes = new BigInteger( Double.toString( numberOfBytes( ByteSizeUnits.BYTE ) ) );
    return numberOfBytes.toString() + " bytes";
  }

  public boolean equals(Object o) {
    if ( this == o ) return true;
    if ( o == null || getClass() != o.getClass() ) return false;
    DoubleWithUnit that = (DoubleWithUnit) o;
    return n == that.n && unit.equals( that.unit );
  }

  public int hashCode() {
    int result;
    long temp;
    temp = n != +0.0d ? Double.doubleToLongBits( n ) : 0L;
    result = (int) ( temp ^ ( temp >>> 32 ) );
    result = 31 * result + unit.hashCode();
    return result;
  }

}
