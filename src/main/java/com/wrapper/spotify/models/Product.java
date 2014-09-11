package com.wrapper.spotify.models;

public enum Product {
  PREMIUM("premium"),
  FREE("free"),
  OPEN("open"),
  DAYPASS("daypass");

  public final String type;

  Product(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
