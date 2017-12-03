package com.wrapper.spotify.objects;

public enum ReleaseDatePrecision {

  YEAR("year"),
  MONTH("month"),
  DAY("day");

  public final String precision;

  ReleaseDatePrecision(String precision) {
    this.precision = precision;
  }

  public String getPrecision() {
    return precision;
  }

}
