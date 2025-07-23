package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible release date precisions.
 */
public enum ReleaseDatePrecision {

  /** Day-level precision (YYYY-MM-DD). */
  DAY("day"),
  /** Month-level precision (YYYY-MM). */
  MONTH("month"),
  /** Year-level precision (YYYY). */
  YEAR("year");

  private static final Map<String, ReleaseDatePrecision> map = new HashMap<>();

  static {
    for (ReleaseDatePrecision releaseDatePrecision : ReleaseDatePrecision.values()) {
      map.put(releaseDatePrecision.precision, releaseDatePrecision);
    }
  }

  /** The precision level identifier. */
  public final String precision;

  ReleaseDatePrecision(final String precision) {
    this.precision = precision;
  }

  /**
   * Get the {@link ReleaseDatePrecision} associated with the given precision string.
   *
   * @param precision the precision string
   * @return the corresponding {@link ReleaseDatePrecision}, or null if not found
   */
  public static ReleaseDatePrecision keyOf(String precision) {
    return map.get(precision);
  }

  /**
   * Get the release date precision as a string.
   *
   * @return The release date precision as a string.
   */
  public String getPrecision() {
    return precision;
  }

}
