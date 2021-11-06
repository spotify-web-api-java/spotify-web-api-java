package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible currently playing types.
 */
public enum CurrentlyPlayingType {

  TRACK("track"),
  EPISODE("episode"),
  AD("ad"),
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
