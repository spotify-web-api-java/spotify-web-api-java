package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible authorization scopes.
 */
public enum AuthorizationScope {
  APP_REMOTE_CONTROL("app-remote-control"),
  PLAYLIST_MODIFY_PRIVATE("playlist-modify-private"),
  PLAYLIST_MODIFY_PUBLIC("playlist-modify-public"),
  PLAYLIST_READ_COLLABORATIVE("playlist-read-collaborative"),
  PLAYLIST_READ_PRIVATE("playlist-read-private"),
  STREAMING("streaming"),
  UGC_IMAGE_UPLOAD("ugc-image-upload"),
  USER_FOLLOW_MODIFY("user-follow-modify"),
  USER_FOLLOW_READ("user-follow-read"),
  USER_LIBRARY_MODIFY("user-library-modify"),
  USER_LIBRARY_READ("user-library-read"),
  USER_MODIFY_PLAYBACK_STATE("user-modify-playback-state"),
  USER_READ_CURRENTLY_PLAYING("user-read-currently-playing"),
  USER_READ_EMAIL("user-read-email"),
  USER_READ_PLAYBACK_POSITION("user-read-playback-position"),
  USER_READ_PLAYBACK_STATE("user-read-playback-state"),
  USER_READ_PRIVATE("user-read-private"),
  USER_READ_RECENTLY_PLAYED("user-read-recently-played"),
  USER_TOP_READ("user-top-read");

  private static final Map<String, AuthorizationScope> map = new HashMap<>();

  static {
    for (AuthorizationScope authorizationScope : AuthorizationScope.values()) {
      map.put(authorizationScope.scope, authorizationScope);
    }
  }

  public final String scope;

  AuthorizationScope(String scope) {
    this.scope = scope;
  }

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
