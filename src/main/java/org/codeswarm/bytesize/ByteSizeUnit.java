package org.codeswarm.bytesize;

public interface ByteSizeUnit extends ExactByteSize {

  ByteSizeUnitSystem unitSystem();

  int power();

}
