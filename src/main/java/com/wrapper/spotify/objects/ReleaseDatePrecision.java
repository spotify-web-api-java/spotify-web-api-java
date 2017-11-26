package com.wrapper.spotify.objects;

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
