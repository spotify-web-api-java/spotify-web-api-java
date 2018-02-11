package com.wrapper.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible release date precisions.
 */
public enum ReleaseDatePrecision {

  DAY("day"),
  MONTH("month"),
  YEAR("year");

  private static Map<String, ReleaseDatePrecision> map = new HashMap<>();

  static {
    for (ReleaseDatePrecision releaseDatePrecision : ReleaseDatePrecision.values()) {
      map.put(releaseDatePrecision.precision, releaseDatePrecision);
    }
  }

  public final String precision;

  ReleaseDatePrecision(final String precision) {
    this.precision = precision;
  }

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
