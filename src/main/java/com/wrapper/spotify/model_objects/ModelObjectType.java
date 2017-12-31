package com.wrapper.spotify.model_objects;

/**
 * An enumeration of all possible model object types.
 */
public enum ModelObjectType {

  ALBUM("album"),
  ARTIST("artist"),
  AUDIO_FEATURES("audio_features"),
  PLAYLIST("playlist"),
  TRACK("track"),
  USER("user");

  public final String type;

  ModelObjectType(String type) {
    this.type = type;
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
