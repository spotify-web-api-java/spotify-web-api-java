package com.wrapper.spotify.enums;

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

  public final String type;

  ModelObjectType(final String type) {
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
