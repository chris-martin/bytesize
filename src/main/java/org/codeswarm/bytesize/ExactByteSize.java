package org.codeswarm.bytesize;

import java.math.BigInteger;

public interface ExactByteSize extends ByteSize {

  BigInteger numberOfBytes();

}
