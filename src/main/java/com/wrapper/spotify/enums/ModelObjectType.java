package com.wrapper.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible model object types.
 */
public enum ModelObjectType {

  ALBUM("album"),
  ARTIST("artist"),
  AUDIO_FEATURES("audio_features"),
  GENRE("genre"),
  PLAYLIST("playlist"),
  TRACK("track"),
  USER("user");

  private static Map<String, ModelObjectType> map = new HashMap<>();

  static {
    for (ModelObjectType modelObjectType : ModelObjectType.values()) {
      map.put(modelObjectType.type, modelObjectType);
    }
  }

  public final String type;

  ModelObjectType(final String type) {
    this.type = type;
  }

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
