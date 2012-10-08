package org.codeswarm.bytesize;

import java.math.BigInteger;

public final class ByteSizes {

  private ByteSizes() {}

  /**
   * @param n A number of bytes.
   * @return An immutable object.
   */
  public static ExactByteSize byteSize( BigInteger n ) {
    return new ExactByteSizeImpl( n );
  }

  /**
   * <p>Equivalent to {@code byteSize(BigInteger.valueOf(n))}.</p>
   * @param n A number of bytes.
   * @return An immutable object.
   */
  public static ExactByteSize byteSize( long n ) {
    return new ExactByteSizeImpl( BigInteger.valueOf( n ) );
  }

  /**
   * @param n A scalar to be multiplied by {@code unit} to yield a number of bytes.
   * @return An immutable object.
   */
  public static ExactByteSize byteSize( BigInteger n, ExactByteSize unit ) {
    return new ExactByteSizeImpl( n.multiply( unit.numberOfBytes() ) );
  }

  /**
   * <p>Equivalent to {@code byteSize(BigInteger.valueOf(n), unit)}.</p>
   * @param n A scalar to be multiplied by {@code unit} to yield a number of bytes.
   * @return An immutable object.
   */
  public static ExactByteSize byteSize( long n, ExactByteSize unit ) {
    BigInteger numberOfByes = BigInteger.valueOf( n ).multiply( unit.numberOfBytes() );
    return new ExactByteSizeImpl( numberOfByes );
  }

  /**
   * @param n An approximate number of bytes.
   * @return An immutable object.
   */
  public static ByteSize byteSize( double n ) {
    return new DoubleWithUnit( n, ByteSizeUnits.BYTE );
  }

  /**
   * @param n A scalar to be multiplied by {@code unit} to yield an approximate number of bytes.
   * @return An immutable object.
   */
  public static ByteSize byteSize( double n, ExactByteSize unit ) {
    return new DoubleWithUnit( n, unit );
  }

}
