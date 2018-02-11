package com.wrapper.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration with the two modality types.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Mode_(music)">Wikipedia: Mode (Music)</a>
 */
public enum Modality {

  MAJOR(1),
  MINOR(0);

  private static Map<Integer, Modality> map = new HashMap<>();

  static {
    for (Modality modality : Modality.values()) {
      map.put(modality.mode, modality);
    }
  }

  public final int mode;

  Modality(final int mode) {
    this.mode = mode;
  }

  public static Modality keyOf(int mode) {
    return map.get(mode);
  }

  /**
   * Get the {@link Modality} type as a string.
   *
   * @return {@link Modality} type as a string.
   */
  public int getType() {
    return this.mode;
  }

}
