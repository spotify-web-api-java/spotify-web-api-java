package com.wrapper.spotify.model_objects;

public enum ReleaseDatePrecision {

  year("year"),
  month("month"),
  day("day");

  public final String precision;

  ReleaseDatePrecision(String precision) {
    this.precision = precision;
  }

  public String getPrecision() {
    return precision;
  }

}
