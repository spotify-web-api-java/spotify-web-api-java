package com.wrapper.spotify.models;

public enum Product {
  PREMIUM("premium"), FREE("free");

  public final String type;

  Product(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
