package com.wrapper.spotify.enums;

public enum ReleaseDatePrecision {

  YEAR("year"),
  MONTH("month"),
  DAY("day");

  public final String precision;

  ReleaseDatePrecision(final String precision) {
    this.precision = precision;
  }

  public String getPrecision() {
    return precision;
  }

}
