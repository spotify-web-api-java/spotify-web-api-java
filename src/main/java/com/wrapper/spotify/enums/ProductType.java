package com.wrapper.spotify.enums;

/**
 * An enumeration of all possible Spotify product types.
 */
public enum ProductType {

  BASIC_DESKTOP("basic-desktop"),
  DAYPASS("daypass"),
  FREE("free"),
  OPEN("open"),
  PREMIUM("premium");

  public final String type;

  ProductType(final String type) {
    this.type = type;
  }

  /**
   * Get the Spotify product type as a string.
   *
   * @return The Spotify product type as a string.
   */
  public String getType() {
    return type;
  }

}
