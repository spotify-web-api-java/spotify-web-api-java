package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration with the two modality types.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Mode_(music)">Wikipedia: Mode (Music)</a>
 */
public enum Modality {

  /** Major modality (mode = 1). */
  MAJOR(1),
  /** Minor modality (mode = 0). */
  MINOR(0);

  private static final Map<Integer, Modality> map = new HashMap<>();

  static {
    for (Modality modality : Modality.values()) {
      map.put(modality.mode, modality);
    }
  }

  /** The numeric mode value (0 for minor, 1 for major). */
  public final int mode;

  Modality(final int mode) {
    this.mode = mode;
  }

  /**
   * Get the {@link Modality} associated with the given mode value.
   *
   * @param mode the mode value (0 for minor, 1 for major)
   * @return the corresponding {@link Modality}, or null if not found
   */
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
