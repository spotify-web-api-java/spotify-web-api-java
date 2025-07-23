package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Compare to [AlbumType] this field represents relationship between the artist and the album.
 */
public enum AlbumGroup {

  /** Album group type. */
  ALBUM("album"),
  /** Appears on group type. */
  APPEARS_ON("appears_on"),
  /** Compilation group type. */
  COMPILATION("compilation"),
  /** Single group type. */
  SINGLE("single");

  private static final Map<String, AlbumGroup> map = new HashMap<>();

  static {
    for (AlbumGroup albumGroup : AlbumGroup.values()) {
      map.put(albumGroup.group, albumGroup);
    }
  }

  /** The album group string. */
  public final String group;

  AlbumGroup(final String group) {
    this.group = group;
  }

  /**
   * Get an AlbumGroup by its type string.
   *
   * @param type The group type string.
   * @return The corresponding AlbumGroup or null if not found.
   */
  public static AlbumGroup keyOf(String type) {
    return map.get(type);
  }

  /**
   * Get the album group as a string.
   *
   * @return Album group as string.
   */
  public String getGroup() {
    return group;
  }

}
