package com.wrapper.spotify.models;

public class Copyright {

  private String text;
  private CopyrightType type;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public CopyrightType getType() {
    return type;
  }

  public void setType(CopyrightType type) {
    this.type = type;
  }
}
