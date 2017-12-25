package com.wrapper.spotify.objects.model_objects.enums;

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
