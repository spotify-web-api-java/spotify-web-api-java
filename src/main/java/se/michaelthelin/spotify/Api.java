package se.michaelthelin.spotify;

import se.michaelthelin.spotify.UtilProtos.Url.Scheme;
import se.michaelthelin.spotify.methods.*;

/**
 * Instances of the Api class provide access to the Spotify Web API.
 */
public class Api {

  /**
   * The default host of Spotify API calls.
   */
  public static final String DEFAULT_HOST = "api.spotify.com";

  /**
   * The default port of Spotify API calls.
   */
  public static final int DEFAULT_PORT = 443;

  /**
   * A HttpManager configured with default settings.
   */
  public static final HttpManager DEFAULT_HTTP_MANAGER = SpotifyHttpManager.builder().build();

  /**
   * The default http scheme of Spotify API calls.
   */
  public static final Scheme DEFAULT_SCHEME = Scheme.HTTPS;

  /**
   * Api instance with the default settings.
   */
  public static final Api DEFAULT_API = Api.builder().build();

  private HttpManager httpManager = null;
  private Scheme scheme;
  private int port;
  private String host;

  private Api(Builder builder) {
    assert (builder.host != null);
    assert (builder.port > 0);
    assert (builder.scheme != null);
    if (builder.httpManager != null) {
      this.httpManager = builder.httpManager;
    } else {
      this.httpManager = DEFAULT_HTTP_MANAGER;
    }
    scheme = builder.scheme;
    host = builder.host;
    port = builder.port;
  }

  public static Builder builder() {
    return new Builder();
  }

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

  public AlbumsRequest.Builder albums() {
    AlbumsRequest.Builder builder = AlbumsRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public ArtistRequest.Builder artist() {
    ArtistRequest.Builder builder = ArtistRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public ArtistsRequest.Builder artists() {
    ArtistsRequest.Builder builder = ArtistsRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public TrackRequest.Builder track() {
    TrackRequest.Builder builder = TrackRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public TracksRequest.Builder tracks() {
    TracksRequest.Builder builder = TracksRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public SearchRequest.Builder search() {
    SearchRequest.Builder builder = SearchRequest.builder();
    setDefaults(builder);
    return builder;
  }

  void setDefaults(Request.Builder builder) {
    builder.httpManager(httpManager);
    builder.scheme(scheme);
    builder.host(host);
    builder.port(port);
  }

  public static class Builder {

    String host = DEFAULT_HOST;
    int port = DEFAULT_PORT;
    HttpManager httpManager = null;
    Scheme scheme = DEFAULT_SCHEME;

    public Builder scheme(Scheme scheme) {
      this.scheme = scheme;
      return this;
    }

    public Builder host(String host) {
      this.host = host;
      return this;
    }

    public Builder port(int port) {
      this.port = port;
      return this;
    }

    public Builder httpManager(HttpManager httpManager) {
      this.httpManager = httpManager;
      return this;
    }

    public Api build() {
      assert (host != null);
      assert (port > 0);
      assert (scheme != null);
      return new Api(this);
    }

  }



}

