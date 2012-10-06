package org.codeswarm.bytesize;

import java.math.BigInteger;

public class ByteSizes {

  /**
   * @param n A number of bytes.
   * @return An immutable object.
   */
  public static ExactByteSize byteSize(BigInteger n) {
    return new ExactByteSizeImpl(n);
  }

  /**
   * <p>Equivalent to {@code byteSize(BigInteger.valueOf(n))}.</p>
   * @param n A number of bytes.
   * @return An immutable object.
   */
  public static ExactByteSize byteSize(long n) {
    return new ExactByteSizeImpl(BigInteger.valueOf(n));
  }

  /**
   * @param n A scalar to be multiplied by {@code unit} to yield a number of bytes.
   * @return An immutable object.
   */
  public static ExactByteSize byteSize(BigInteger n, ExactByteSize unit) {
    return new ExactByteSizeImpl(n.multiply(unit.numberOfBytes()));
  }

  /**
   * <p>Equivalent to {@code byteSize(BigInteger.valueOf(n), unit)}.</p>
   * @param n A scalar to be multiplied by {@code unit} to yield a number of bytes.
   * @return An immutable object.
   */
  public static ExactByteSize byteSize(long n, ExactByteSize unit) {
    return new ExactByteSizeImpl(BigInteger.valueOf(n).multiply(unit.numberOfBytes()));
  }

  /**
   * @param n An approximate number of bytes.
   * @return An immutable object.
   */
  public static ByteSize byteSize(double n) {
    return new DoubleWithUnit(n, BYTE);
  }

  /**
   * @param n A scalar to be multiplied by {@code unit} to yield an approximate number of bytes.
   * @return An immutable object.
   */
  public static ByteSize byteSize(double n, ExactByteSize unit) {
    return new DoubleWithUnit(n, unit);
  }

  /** A single byte with no unit system. */
  public static final ByteSizeUnit BYTE = new SingleByteUnit();

  /** SI unit: kB = kilobyte = 10<sup>3</sup> bytes. */
  public static final ByteSizeUnit kB = new SIUnit(3), kilobyte = kB;

  /** SI unit: MB = megabyte = 10<sup>6</sup> bytes. */
  public static final ByteSizeUnit MB = new SIUnit(6), megabyte = MB;

  /** SI unit: GB = gigabyte = 10<sup>9</sup> bytes. */
  public static final ByteSizeUnit GB = new SIUnit(9), gigabyte = GB;

  /** SI unit: TB = terabyte = 10<sup>12</sup> bytes. */
  public static final ByteSizeUnit TB = new SIUnit(12), terabyte = TB;

  /** SI unit: PB = petabyte = 10<sup>15</sup> bytes. */
  public static final ByteSizeUnit PB = new SIUnit(15), petabyte = PB;

  /** SI unit: EB = exabyte = 10<sup>18</sup> bytes. */
  public static final ByteSizeUnit EB = new SIUnit(18), exabyte = EB;

  /** SI unit: ZB = zettabyte = 10<sup>21</sup> bytes. */
  public static final ByteSizeUnit ZB = new SIUnit(21), zettabyte = ZB;

  /** SI unit: YB = yotta byte = 10<sup>24</sup> bytes. */
  public static final ByteSizeUnit YB = new SIUnit(24), yottabyte = YB;

  /** IEC unit: KiB = kibibyte = 2<sup>10</sup> bytes. */
  public static final ByteSizeUnit KiB = new IECUnit(10), kibibyte = KiB;

  /** IEC unit: MiB = mebibyte = 2<sup>20</sup> bytes. */
  public static final ByteSizeUnit MiB = new IECUnit(20), mebibyte = MiB;

  /** IEC unit: GiB = gibibyte = 2<sup>30</sup> bytes. */
  public static final ByteSizeUnit GiB = new IECUnit(30), gibibyte = GiB;

  /** IEC unit: TiB = tebibyte = 2<sup>40</sup> bytes. */
  public static final ByteSizeUnit TiB = new IECUnit(40), tebibyte = TiB;

  /** IEC unit: PiB = pebibyte = 2<sup>50</sup> bytes. */
  public static final ByteSizeUnit PiB = new IECUnit(50), pebibyte = PiB;

  /** IEC unit: EiB = exbibyte = 2<sup>60</sup> bytes. */
  public static final ByteSizeUnit EiB = new IECUnit(60), exbibyte = EiB;

  /** IEC unit: ZiB = zebibyte = 2<sup>70</sup> bytes. */
  public static final ByteSizeUnit ZiB = new IECUnit(70), zebibyte = ZiB;

  /** IEC unit: YiB = yobibyte = 2<sup>80</sup> bytes. */
  public static final ByteSizeUnit YiB = new IECUnit(80), yobibyte = YiB;

  static class SingleByteUnit implements ByteSizeUnit {

    public BigInteger numberOfBytes() {
      return BigInteger.ONE;
    }

    public double numberOfBytes(ExactByteSize unit) {
      double retval = 1;
      if (!unit.numberOfBytes().equals(BigInteger.ONE)) {
        retval /= unit.numberOfBytes().doubleValue();
      }
      return retval;
    }

    public String unitName() {
      return "0";
    }

  }

  static class SIUnit extends ExactByteSizeImpl implements ByteSizeUnit {

    final int power;

    SIUnit(int power) {
      super(BigInteger.TEN.pow(power));
      this.power = power;
    }

    public String unitName() {
      return String.format("SI %d", power);
    }

  }

  static class IECUnit extends ExactByteSizeImpl implements ByteSizeUnit {

    final int power;

    IECUnit(int power) {
      super(BigInteger.valueOf(2).pow(power));
      this.power = power;
    }

    public String unitName() {
      return String.format("IEC %d", power);
    }

  }

  static class ExactByteSizeImpl implements ExactByteSize {

    final BigInteger n;

    ExactByteSizeImpl(BigInteger n) {
      this.n = n;
    }

    public BigInteger numberOfBytes() {
      return n;
    }

    public double numberOfBytes(ExactByteSize unit) {
      return n.divide(unit.numberOfBytes()).doubleValue();
    }

    public String toString() {
      return n.toString() + " bytes";
    }

    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ExactByteSizeImpl that = (ExactByteSizeImpl) o;
      return n.equals(that.n);
    }

    public int hashCode() {
      return n.hashCode();
    }

  }

  static class DoubleWithUnit implements ByteSize {

    private final double n;
    private final ExactByteSize unit;

    public DoubleWithUnit(double n, ExactByteSize unit) {
      this.n = n;
      this.unit = unit;
    }

    public double numberOfBytes(ExactByteSize unit) {
      double retval = n;
      if (!this.unit.equals(unit)) {
        retval *= this.unit.numberOfBytes().doubleValue() / unit.numberOfBytes().doubleValue();
      }
      return retval;
    }

    public String toString() {
      return new BigInteger(Double.toString(numberOfBytes(BYTE))).toString() + " bytes";
    }

    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      DoubleWithUnit that = (DoubleWithUnit) o;
      return n == that.n && unit.equals(that.unit);
    }

    public int hashCode() {
      int result;
      long temp;
      temp = n != +0.0d ? Double.doubleToLongBits(n) : 0L;
      result = (int) (temp ^ (temp >>> 32));
      result = 31 * result + unit.hashCode();
      return result;
    }

  }

}
