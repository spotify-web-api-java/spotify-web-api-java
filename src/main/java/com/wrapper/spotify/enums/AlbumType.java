package com.wrapper.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible album types.
 */
public enum AlbumType {

  ALBUM("album"),
  APPEARS_ON("appears_on"),
  COMPILATION("compilation"),
  SINGLE("single");

  private static Map<String, AlbumType> map = new HashMap<>();

  static {
    for (AlbumType albumType : AlbumType.values()) {
      map.put(albumType.type, albumType);
    }
  }

  public final String type;

  AlbumType(final String type) {
    this.type = type;
  }

  public static AlbumType keyOf(String type) {
    return map.get(type);
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
