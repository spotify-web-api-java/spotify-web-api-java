package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible authorization scopes.
 */
public enum AuthorizationScope {
  /** App remote control authorization scope. */
  APP_REMOTE_CONTROL("app-remote-control"),
  /** Playlist modify private authorization scope. */
  PLAYLIST_MODIFY_PRIVATE("playlist-modify-private"),
  /** Playlist modify public authorization scope. */
  PLAYLIST_MODIFY_PUBLIC("playlist-modify-public"),
  /** Playlist read collaborative authorization scope. */
  PLAYLIST_READ_COLLABORATIVE("playlist-read-collaborative"),
  /** Playlist read private authorization scope. */
  PLAYLIST_READ_PRIVATE("playlist-read-private"),
  /** Streaming authorization scope. */
  STREAMING("streaming"),
  /** UGC image upload authorization scope. */
  UGC_IMAGE_UPLOAD("ugc-image-upload"),
  /** User follow modify authorization scope. */
  USER_FOLLOW_MODIFY("user-follow-modify"),
  /** User follow read authorization scope. */
  USER_FOLLOW_READ("user-follow-read"),
  /** User library modify authorization scope. */
  USER_LIBRARY_MODIFY("user-library-modify"),
  /** User library read authorization scope. */
  USER_LIBRARY_READ("user-library-read"),
  /** User modify playback state authorization scope. */
  USER_MODIFY_PLAYBACK_STATE("user-modify-playback-state"),
  /** User read currently playing authorization scope. */
  USER_READ_CURRENTLY_PLAYING("user-read-currently-playing"),
  /** User read email authorization scope. */
  USER_READ_EMAIL("user-read-email"),
  /** User read playback position authorization scope. */
  USER_READ_PLAYBACK_POSITION("user-read-playback-position"),
  /** User read playback state authorization scope. */
  USER_READ_PLAYBACK_STATE("user-read-playback-state"),
  /** User read private authorization scope. */
  USER_READ_PRIVATE("user-read-private"),
  /** User read recently played authorization scope. */
  USER_READ_RECENTLY_PLAYED("user-read-recently-played"),
  /** User top read authorization scope. */
  USER_TOP_READ("user-top-read");

  private static final Map<String, AuthorizationScope> map = new HashMap<>();

  static {
    for (AuthorizationScope authorizationScope : AuthorizationScope.values()) {
      map.put(authorizationScope.scope, authorizationScope);
    }
  }

  /** The authorization scope string. */
  public final String scope;

  AuthorizationScope(String scope) {
    this.scope = scope;
  }

  /**
   * Get an AuthorizationScope by its type string.
   *
   * @param type The scope type string.
   * @return The corresponding AuthorizationScope or null if not found.
   */
  public static AuthorizationScope keyOf(String type) {
    return map.get(type);
  }

  /**
   * Get the authorization scope as a string.
   *
   * @return Authorization scope as string.
   */
  public String GetScope() {
    return scope;
  }
}
