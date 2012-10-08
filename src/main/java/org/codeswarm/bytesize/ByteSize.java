package org.codeswarm.bytesize;

/**
 * <p>Represents some quantity of bytes.</p>
 *
 * <p>The quantity may be stored as a floating-point number.
 * If you need precise byte counts, make sure your {@link ByteSize}
 * is an {@link ExactByteSize}.</p>
 */
public interface ByteSize extends Comparable<ByteSize> {

  double numberOfBytes( ExactByteSize unit );

  /**
   * Use {@link #compareTo(Object)}, not {@link #equals(Object)}, to compare byte sizes.
   *
   * {@inheritDoc}
   */
  boolean equals( Object o );

}
