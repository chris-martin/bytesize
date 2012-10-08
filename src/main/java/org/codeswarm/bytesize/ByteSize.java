package org.codeswarm.bytesize;

public interface ByteSize extends Comparable<ByteSize> {

  double numberOfBytes( ExactByteSize unit );

}
