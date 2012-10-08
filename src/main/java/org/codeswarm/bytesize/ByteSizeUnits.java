package org.codeswarm.bytesize;

import java.math.BigInteger;
import java.util.*;

import static java.util.Arrays.asList;

public final class ByteSizeUnits {

  private ByteSizeUnits() {}

  /** A single byte with no unit system. */
  public static final ByteSizeUnit BYTE = new SingleByteUnit();

  static class SIUnit extends ExactByteSizeImpl implements ByteSizeUnit {

    final int power;

    SIUnit( int power ) {
      super( BigInteger.TEN.pow( power ) );
      this.power = power;
    }

    public String unitName() {
      return String.format( "SI %d", power );
    }

  }

  /** SI unit: kB = kilobyte = 10<sup>3</sup> bytes. */
  public static final ByteSizeUnit kB = new SIUnit( 3 ), kilobyte = kB;

  /** SI unit: MB = megabyte = 10<sup>6</sup> bytes. */
  public static final ByteSizeUnit MB = new SIUnit( 6 ), megabyte = MB;

  /** SI unit: GB = gigabyte = 10<sup>9</sup> bytes. */
  public static final ByteSizeUnit GB = new SIUnit( 9 ), gigabyte = GB;

  /** SI unit: TB = terabyte = 10<sup>12</sup> bytes. */
  public static final ByteSizeUnit TB = new SIUnit( 12 ), terabyte = TB;

  /** SI unit: PB = petabyte = 10<sup>15</sup> bytes. */
  public static final ByteSizeUnit PB = new SIUnit( 15 ), petabyte = PB;

  /** SI unit: EB = exabyte = 10<sup>18</sup> bytes. */
  public static final ByteSizeUnit EB = new SIUnit( 18 ), exabyte = EB;

  /** SI unit: ZB = zettabyte = 10<sup>21</sup> bytes. */
  public static final ByteSizeUnit ZB = new SIUnit( 21 ), zettabyte = ZB;

  /** SI unit: YB = yotta byte = 10<sup>24</sup> bytes. */
  public static final ByteSizeUnit YB = new SIUnit( 24 ), yottabyte = YB;

  /**
   * <p>Powers of ten denoted by Metric (SI) prefixes.</p>
   * <table>
   * <tr><th colspan=2>Unit</th><th>Bytes</th></tr>
   * <tr><td>kilobyte </td><td>kB</td><td>10<sup> 3</sup></td></tr>
   * <tr><td>megabyte </td><td>MB</td><td>10<sup> 6</sup></td></tr>
   * <tr><td>gigabyte </td><td>GB</td><td>10<sup> 9</sup></td></tr>
   * <tr><td>terabyte </td><td>TB</td><td>10<sup>12</sup></td></tr>
   * <tr><td>petabyte </td><td>PB</td><td>10<sup>15</sup></td></tr>
   * <tr><td>exabyte  </td><td>EB</td><td>10<sup>18</sup></td></tr>
   * <tr><td>zettabyte</td><td>ZB</td><td>10<sup>21</sup></td></tr>
   * <tr><td>yottabyte</td><td>YB</td><td>10<sup>24</sup></td></tr>
   * </table>
   */
  public static final ByteSizeUnitSystem SI = new ByteSizeUnitSystem() {
    public List<ByteSizeUnit> units() {
      return asList( BYTE, kB, MB, GB, TB, PB, EB, ZB, YB );
    }
  };

  static class IECUnit extends ExactByteSizeImpl implements ByteSizeUnit {

    final int power;

    IECUnit( int power ) {
      super( BigInteger.valueOf( 2 ).pow( power ) );
      this.power = power;
    }

    public String unitName() {
      return String.format( "IEC %d", power );
    }

  }

  /** IEC unit: KiB = kibibyte = 2<sup>10</sup> bytes. */
  public static final ByteSizeUnit KiB = new IECUnit( 10 ), kibibyte = KiB;

  /** IEC unit: MiB = mebibyte = 2<sup>20</sup> bytes. */
  public static final ByteSizeUnit MiB = new IECUnit( 20 ), mebibyte = MiB;

  /** IEC unit: GiB = gibibyte = 2<sup>30</sup> bytes. */
  public static final ByteSizeUnit GiB = new IECUnit( 30 ), gibibyte = GiB;

  /** IEC unit: TiB = tebibyte = 2<sup>40</sup> bytes. */
  public static final ByteSizeUnit TiB = new IECUnit( 40 ), tebibyte = TiB;

  /** IEC unit: PiB = pebibyte = 2<sup>50</sup> bytes. */
  public static final ByteSizeUnit PiB = new IECUnit( 50 ), pebibyte = PiB;

  /** IEC unit: EiB = exbibyte = 2<sup>60</sup> bytes. */
  public static final ByteSizeUnit EiB = new IECUnit( 60 ), exbibyte = EiB;

  /** IEC unit: ZiB = zebibyte = 2<sup>70</sup> bytes. */
  public static final ByteSizeUnit ZiB = new IECUnit( 70 ), zebibyte = ZiB;

  /** IEC unit: YiB = yobibyte = 2<sup>80</sup> bytes. */
  public static final ByteSizeUnit YiB = new IECUnit( 80 ), yobibyte = YiB;

  /**
   * <p>Powers of two denoted by International Electrotechnical Commission (IEC) prefixes.</p>
   * <table>
   * <tr><th colspan=2>Unit</th><th>Bytes</th></tr>
   * <tr><td>kibibyte</td><td>KiB</td><td>2<sup>10</sup></td></tr>
   * <tr><td>mebibyte</td><td>MiB</td><td>2<sup>20</sup></td></tr>
   * <tr><td>gibibyte</td><td>GiB</td><td>2<sup>30</sup></td></tr>
   * <tr><td>tebibyte</td><td>TiB</td><td>2<sup>40</sup></td></tr>
   * <tr><td>pebibyte</td><td>PiB</td><td>2<sup>50</sup></td></tr>
   * <tr><td>exbibyte</td><td>EiB</td><td>2<sup>60</sup></td></tr>
   * <tr><td>zebibyte</td><td>ZiB</td><td>2<sup>70</sup></td></tr>
   * <tr><td>yobibyte</td><td>YiB</td><td>2<sup>80</sup></td></tr>
   * </table>
   */
  public static final ByteSizeUnitSystem IEC = new ByteSizeUnitSystem() {
    public List<ByteSizeUnit> units() {
      return asList( BYTE, KiB, MiB, GiB, TiB, PiB, EiB, ZiB, YiB );
    }
  };

  public static Collection<ByteSizeUnit> getAllDefaultUnits() {
    return asList(BYTE, kB,  MB,  GB,  TB,  PB,  EB,  ZB,  YB,
                        KiB, MiB, GiB, TiB, PiB, EiB, ZiB, YiB);
  }

  public static Collection<ByteSizeUnit> getUnitsForSystems(
      Iterable<ByteSizeUnitSystem> unitSystems ) {

    Set<ByteSizeUnit> units = new HashSet<ByteSizeUnit>();
    for ( ByteSizeUnitSystem unitSystem : unitSystems ) {
      units.addAll( unitSystem.units() );
    }
    return units;
  }

  public static ByteSizeUnitSystem getDefaultUnitSystem(
      ResourceBundle resourceBundle ) {

    Map<String, ByteSizeUnitSystem> unitSystems = new HashMap<String, ByteSizeUnitSystem>();
    unitSystems.put( "SI", SI );
    unitSystems.put( "IEC", IEC );
    return getDefaultUnitSystem( resourceBundle, unitSystems );
  }
  public static ByteSizeUnitSystem getDefaultUnitSystem(
      ResourceBundle resourceBundle,
      Map<String, ByteSizeUnitSystem> unitSystems ) {

    String[] names = resourceBundle.getString( "unit systems" ).trim().split( ", ?" );
    for ( String name : names ) {
      name = name.trim();
      ByteSizeUnitSystem unitSystem = unitSystems.get( name );
      if ( unitSystem != null ) {
        return unitSystem;
      }
    }
    throw new IllegalArgumentException( "No unit systems found." );
  }

}
