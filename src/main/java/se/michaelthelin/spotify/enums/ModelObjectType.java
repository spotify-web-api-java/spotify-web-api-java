package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible model object types.
 */
public enum ModelObjectType {

  /** Album object type. */
  ALBUM("album"),
  /** Artist object type. */
  ARTIST("artist"),
  /** Audio features object type. */
  AUDIO_FEATURES("audio_features"),
  /** Episode object type. */
  EPISODE("episode"),
  /** Genre object type. */
  GENRE("genre"),
  /** Playlist object type. */
  PLAYLIST("playlist"),
  /** Show object type. */
  SHOW("show"),
  /** Track object type. */
  TRACK("track"),
  /** User object type. */
  USER("user");

  private static final Map<String, ModelObjectType> map = new HashMap<>();

  static {
    for (ModelObjectType modelObjectType : ModelObjectType.values()) {
      map.put(modelObjectType.type, modelObjectType);
    }
  }

  /** The model object type identifier. */
  public final String type;

  ModelObjectType(final String type) {
    this.type = type;
  }

  /**
   * Get the {@link ModelObjectType} associated with the given type string.
   *
   * @param type the model object type string
   * @return the corresponding {@link ModelObjectType}, or null if not found
   */
  public static ModelObjectType keyOf(String type) {
    return map.get(type);
  }

  /**
   * Get the model object type as a string.
   *
   * @return The model object type as a string.
   */
  public String getType() {
    return this.type;
  }

}
