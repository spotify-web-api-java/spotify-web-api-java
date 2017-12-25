package com.wrapper.spotify.enums;

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

  public String getType() {
    return this.type;
  }

}
