package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible album types.
 */
public enum AlbumType {

  /** Album type. */
  ALBUM("album"),
  /** Compilation type. */
  COMPILATION("compilation"),
  /** Single type. */
  SINGLE("single");

  private static final Map<String, AlbumType> map = new HashMap<>();

  static {
    for (AlbumType albumType : AlbumType.values()) {
      map.put(albumType.type, albumType);
    }
  }

  /** The album type string. */
  public final String type;

  AlbumType(final String type) {
    this.type = type;
  }

  /**
   * Get an AlbumType by its type string.
   *
   * @param type The album type string.
   * @return The corresponding AlbumType or null if not found.
   */
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
