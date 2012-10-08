package org.codeswarm.bytesize;

/**
 * <p>Canonically, the metric (SI) prefixes refer to powers of ten:</p>
 * <blockquote>(3 megabytes = 3*10<sup>6</sup> = 3000000 bytes</blockquote>
 * <p>and the prefixes defined by the International Electrotechnical Commission
 * (IEC) refer to powers of two:</p>
 * <blockquote>3 mebibytes = 3*2<sup>20</sup> = 3,145,728 bytes</blockquote>
 * <p>Much confusion surrounds quantification of file sizes and storage capacities.
 * The IEC prefixes failed to gain popular usage, and the SI prefixes are often
 * used inappropriately.</p>
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
public interface ByteSizeUnit extends ExactByteSize {

  String unitName();

}
