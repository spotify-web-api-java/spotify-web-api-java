package se.michaelthelin.spotify.enums;

public enum AuthScope {
  IMAGE_UPLOAD("ugc-image-upload"),
  MODIFY_PLAYBACK_STATE("user-modify-playback-state"),
  USER_FOLLOW_MODIFY("user-follow-modify"),
  USER_READ_RECENTLY_PLAYED("user-read-recently-played"),
  USER_READ_PLAYBACK_POSITION("user-read-playback-position"),
  PLAYLIST_READ_COLLABORATIVE("playlist-read-collaborative"),
  APP_REMOTE_CONTROL("app-remote-control"),
  USER_READ_PLAYBACK_STATE("user-read-playback-state"),
  USER_READ_EMAIL("user-read-email"),
  STREAMING("streaming"),
  USER_TOP_READ("user-top-read"),
  PLAYLIST_MODIFY_PUBLIC("playlist-modify-public"),
  USER_LIBRARY_MODIFY("user-library-modify"),
  USER_FOLLOW_READ("user-follow-read"),
  USER_READ_CURRENTLY_PLAYING("user-read-currently-playing"),
  USER_LIBRARY_READ("user-library-read"),
  PLAYLIST_READ_PRIVATE("playlist-read-private"),
  USER_READ_PRIVATE("user-read-private"),
  PLAYLIST_MODIFY_PRIVATE("playlist-modify-private");

  private String scope;

  AuthScope(String scope) {
    this.scope = scope;
  }

  public String GetScope() {
    return scope;
  }
}
