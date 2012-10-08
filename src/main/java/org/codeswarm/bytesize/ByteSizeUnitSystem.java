package org.codeswarm.bytesize;

import java.util.List;

public interface ByteSizeUnitSystem {

  /**
   * @return This system's units in increasing order.
   */
  List<ByteSizeUnit> units();

}
