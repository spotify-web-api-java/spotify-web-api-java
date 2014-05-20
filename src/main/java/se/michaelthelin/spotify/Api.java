package se.michaelthelin.spotify;

import se.michaelthelin.spotify.UtilProtos.Url.Scheme;
import se.michaelthelin.spotify.methods.*;
import se.michaelthelin.spotify.methods.authentication.RefreshAccessTokenRequest;
import se.michaelthelin.spotify.methods.authentication.TokenRequest;

import java.util.Arrays;
import java.util.List;

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

  public static final String DEFAULT_AUTHENTICATION_HOST = "accounts.spotify.com";

  public static final int DEFAULT_AUTHENTICATION_PORT = 443;

  public static final Scheme DEFAULT_AUTHENTICATION_SCHEME = Scheme.HTTPS;

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


    if (builder.httpManager == null) {
      this.httpManager = SpotifyHttpManager
              .builder()
              .build();
    } else {
      this.httpManager = builder.httpManager;
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
  public AlbumRequest.Builder getAlbum(String id) {
    AlbumRequest.Builder builder = AlbumRequest.builder();
    setDefaults(builder);
    builder.id(id);
    return builder;
  }

  public AlbumsRequest.Builder getAlbums(String... ids) {
    return getAlbums(Arrays.asList(ids));
  }

  public AlbumsRequest.Builder getAlbums(List<String> ids) {
    AlbumsRequest.Builder builder = AlbumsRequest.builder();
    setDefaults(builder);
    builder.id(ids);
    return builder;
  }

  public AlbumsForArtistRequest.Builder getAlbumsForArtist(String artistId) {
    AlbumsForArtistRequest.Builder builder = AlbumsForArtistRequest.builder();
    setDefaults(builder);
    builder.forArtist(artistId);
    return builder;
  }

  public ArtistRequest.Builder getArtist(String id) {
    ArtistRequest.Builder builder = ArtistRequest.builder();
    setDefaults(builder);
    builder.id(id);
    return builder;
  }

  public ArtistsRequest.Builder getArtists(String... ids) {
    return getArtists(Arrays.asList(ids));
  }

  public ArtistsRequest.Builder getArtists(List<String> ids) {
    ArtistsRequest.Builder builder = ArtistsRequest.builder();
    setDefaults(builder);
    builder.id(ids);
    return builder;
  }

  public TrackRequest.Builder getTrack(String id) {
    TrackRequest.Builder builder = TrackRequest.builder();
    setDefaults(builder);
    builder.id(id);
    return builder;
  }

  public TracksRequest.Builder getTracks(String... ids) {
    return getTracks(Arrays.asList(ids));
  }

  public TracksRequest.Builder getTracks(List<String> ids) {
    TracksRequest.Builder builder = TracksRequest.builder();
    setDefaults(builder);
    builder.id(ids);
    return builder;
  }

  public AlbumSearchRequest.Builder searchAlbums(String query) {
    AlbumSearchRequest.Builder builder = AlbumSearchRequest.builder();
    setDefaults(builder);
    builder.query(query);
    return builder;
  }

  public TrackSearchRequest.Builder searchTracks(String query) {
    TrackSearchRequest.Builder builder = TrackSearchRequest.builder();
    setDefaults(builder);
    builder.query(query);
    return builder;
  }

  public ArtistSearchRequest.Builder searchArtists(String query) {
    ArtistSearchRequest.Builder builder = ArtistSearchRequest.builder();
    setDefaults(builder);
    builder.query(query);
    return builder;
  }

  public TopTracksRequest.Builder getTopTracksForArtist(String artistId, String countryCode) {
    TopTracksRequest.Builder builder = TopTracksRequest.builder();
    setDefaults(builder);
    builder.id(artistId);
    builder.countryCode(countryCode);
    return builder;
  }

  public UserRequest.Builder getUser(String userId) {
    UserRequest.Builder builder = UserRequest.builder();
    setDefaults(builder);
    builder.username(userId);
    return builder;
  }

  public UserPlaylistsRequest.Builder getPlaylistsForUser(String userId) {
    UserPlaylistsRequest.Builder builder = UserPlaylistsRequest.builder();
    setDefaults(builder);
    builder.username(userId);
    return builder;
  }

  public TokenRequest.Builder getTokens(String clientId, String clientSecret, String code, String redirectUri) {
    TokenRequest.Builder builder = TokenRequest.builder();
    builder.grantType("authorization_code");
    builder.authorizationHeader(clientId, clientSecret);
    builder.code(code);
    builder.redirectUri(redirectUri);
    setDefaults(builder);
    return builder;
  }

  public RefreshAccessTokenRequest.Builder refreshAccessToken(String clientId, String clientSecret, String refreshToken) {
    RefreshAccessTokenRequest.Builder builder = RefreshAccessTokenRequest.builder();
    setDefaults(builder);
    builder.grantType("refresh_token");
    builder.refreshToken(refreshToken);
    builder.authorizationHeader(clientId, clientSecret);
    return builder;
  }

  void setDefaults(Request.Builder builder) {
    builder.httpManager(httpManager);
    builder.scheme(scheme);
    builder.host(host);
    builder.port(port);
  }

  public static class Builder {

    private String host = DEFAULT_HOST;
    private int port = DEFAULT_PORT;
    private HttpManager httpManager = null;
    private Scheme scheme = DEFAULT_SCHEME;

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

