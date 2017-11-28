package com.wrapper.spotify.model_objects;

public enum CopyrightType {

  C("C"),
  P("P");

  public final String type;

  CopyrightType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
