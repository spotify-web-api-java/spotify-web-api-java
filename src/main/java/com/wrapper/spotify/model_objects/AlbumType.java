package com.wrapper.spotify.model_objects;

/**
 * An enumeration of all possible album types.
 */
public enum AlbumType {

  ALBUM("album"),
  SINGLE("single"),
  COMPILATION("compilation");

  public final String type;

  AlbumType(String type) {
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
