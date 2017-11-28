package com.wrapper.spotify.model_objects;

public enum ObjectType {

  ALBUM("album"),
  ARTIST("artist"),
  AUDIO_FEATURES("audio_features"),
  PLAYLIST("playlist"),
  TRACK("track"),
  USER("user");

  public final String type;

  ObjectType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

}
