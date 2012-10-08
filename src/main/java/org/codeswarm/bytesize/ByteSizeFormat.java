package org.codeswarm.bytesize;

import java.text.ParseException;

public interface ByteSizeFormat {

  public enum WordLength { FULL, ABBREVIATION }

  /**
   * Renders a string representation of {@code byteSize}
   * in an automatically-determined unit.
   */
  String format( ByteSize byteSize, WordLength wordLength );

  /**
   * Renders a string representation of {@code byteSize}.
   */
  String format( ByteSize byteSize, ByteSizeUnit unit, WordLength wordLength );

  ByteSize parse( String s ) throws ParseException;

}
