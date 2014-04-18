package se.michaelthelin.spotify;

import se.michaelthelin.spotify.methods.AlbumRequest;
import se.michaelthelin.spotify.methods.Request;
import se.michaelthelin.spotify.UtilProtos.Url;

/**
 * Instances of the Api class provide access to the Spotify Web API.
 */
public class Api {

  /**
   * The default host of Spotify API calls.
   */
  public static final String DEFAULT_HOST = "https://api.spotify.com";

  /**
   * The default port of Spotify API calls.
   */
  public static final int DEFAULT_PORT = 80;

  /**
   * A HttpManager configured with default settings.
   */
  public static final HttpManager DEFAULT_HTTP_MANAGER = SpotifyHttpManager.builder().build();

  /**
   * The default http scheme of Spotify API calls.
   */
  public static final Url.Scheme DEFAULT_SCHEME = Url.Scheme.HTTP;

  /**
   * The default API version.
   */
  public static final String DEFAULT_VERSION = "/v1";

  /**
   * Returns a an album with the id given below.
   *
   * @param id The base62 id of the album you're trying to retrieve.
   * @return An {AlbumRequest.Builder} instance.
   */
  public AlbumRequest.Builder album() {
    AlbumRequest.Builder builder = AlbumRequest.builder();
    setDefaults(builder);
    return builder;
  }

  void setDefaults(Request.Builder builder) {
    builder.httpManager(DEFAULT_HTTP_MANAGER);
    builder.host(DEFAULT_HOST);
    builder.port(DEFAULT_PORT);
    builder.version(DEFAULT_VERSION);
    builder.scheme(DEFAULT_SCHEME);
  }
}

