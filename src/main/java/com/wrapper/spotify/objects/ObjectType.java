package com.wrapper.spotify.objects;

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
