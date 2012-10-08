package org.codeswarm.bytesize;

import java.math.BigInteger;

class ExactByteSizeImpl implements ExactByteSize {

  final BigInteger n;

  ExactByteSizeImpl( BigInteger n ) {
    this.n = n;
  }

  public BigInteger numberOfBytes() {
    return n;
  }

  public double numberOfBytes( ExactByteSize unit ) {
    return n.divide( unit.numberOfBytes() ).doubleValue();
  }

  public int compareTo(ByteSize o) {
    if (o instanceof ExactByteSize) {
      return n.compareTo(((ExactByteSize) o).numberOfBytes());
    } else {
      ByteSizeUnit unit = ByteSizeUnits.BYTE;
      return Double.compare(numberOfBytes(unit), o.numberOfBytes(unit));
    }
  }

  public String toString() {
    return n.toString() + " bytes";
  }

  public boolean equals( Object o ) {
    if ( this == o ) return true;
    if ( o == null || getClass() != o.getClass() ) return false;
    ExactByteSizeImpl that = (ExactByteSizeImpl) o;
    return n.equals( that.n );
  }

  public int hashCode() {
    return n.hashCode();
  }

}
