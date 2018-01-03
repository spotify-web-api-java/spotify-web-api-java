package com.wrapper.spotify.enums;

/**
 * An enumeration of all possible album types.
 */
public enum AlbumType {

  ALBUM("album"),
  SINGLE("single"),
  APPEARS_ON("appears_on"),
  COMPILATION("compilation");

  public final String type;

  AlbumType(final String type) {
    this.type = type;
  }

  /**
   * Get the album type as a string.
   *
   * @return Album type as string.
   */
  public String getType() {
    return type;
  }

}
