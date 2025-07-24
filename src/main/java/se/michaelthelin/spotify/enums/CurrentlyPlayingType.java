package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible currently playing types.
 */
public enum CurrentlyPlayingType {

  /** A music track. */
  TRACK("track"),
  /** A podcast episode. */
  EPISODE("episode"),
  /** An advertisement. */
  AD("ad"),
  /** Unknown or unsupported content type. */
  UNKNOWN("unknown");

  private static final Map<String, CurrentlyPlayingType> map = new HashMap<>();

  static {
    for (CurrentlyPlayingType currentlyPlayingType : CurrentlyPlayingType.values()) {
      map.put(currentlyPlayingType.type, currentlyPlayingType);
    }
  }


  private final String type;

  CurrentlyPlayingType(final String type) {
    this.type = type;
  }

  /**
   * Get the {@link CurrentlyPlayingType} associated with the given type string.
   *
   * @param type the currently playing type string
   * @return the corresponding {@link CurrentlyPlayingType}, or null if not found
   */
  public static CurrentlyPlayingType keyOf(String type) {
    return map.get(type);
  }

  /**
   * Get the currently playing type as a string.
   *
   * @return The currently playing type as a string.
   */
  public String getType() {
    return type;
  }
}
