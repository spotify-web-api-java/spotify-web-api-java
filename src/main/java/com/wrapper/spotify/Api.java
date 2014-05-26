package com.wrapper.spotify;

import com.wrapper.spotify.UtilProtos.Url.Scheme;
import com.wrapper.spotify.methods.*;
import com.wrapper.spotify.methods.authentication.ApplicationAuthenticationRequest;
import com.wrapper.spotify.methods.authentication.RefreshAccessTokenRequest;
import com.wrapper.spotify.methods.authentication.TokenRequest;

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
  private String accessToken;

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
    accessToken = builder.accessToken;
    scheme = builder.scheme;
    host = builder.host;
    port = builder.port;
  }

  public static Builder builder() {
    return new Builder();
  }

  public AlbumRequest.Builder getAlbum() {
    AlbumRequest.Builder builder = AlbumRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public AlbumsRequest.Builder getAlbums() {
    AlbumsRequest.Builder builder = AlbumsRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public AlbumsForArtistRequest.Builder getAlbumsForArtist() {
    AlbumsForArtistRequest.Builder builder = AlbumsForArtistRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public ArtistRequest.Builder getArtist() {
    ArtistRequest.Builder builder = ArtistRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public ArtistsRequest.Builder getArtists() {
    ArtistsRequest.Builder builder = ArtistsRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public TrackRequest.Builder getTrack() {
    TrackRequest.Builder builder = TrackRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public TracksRequest.Builder getTracks() {
    TracksRequest.Builder builder = TracksRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public AlbumSearchRequest.Builder searchAlbums() {
    AlbumSearchRequest.Builder builder = AlbumSearchRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public TrackSearchRequest.Builder searchTracks() {
    TrackSearchRequest.Builder builder = TrackSearchRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public ArtistSearchRequest.Builder searchArtists() {
    ArtistSearchRequest.Builder builder = ArtistSearchRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public TopTracksRequest.Builder getTopTracksForArtist() {
    TopTracksRequest.Builder builder = TopTracksRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public UserRequest.Builder getUser() {
    UserRequest.Builder builder = UserRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public UserPlaylistsRequest.Builder getPlaylistsForUser() {
    UserPlaylistsRequest.Builder builder = UserPlaylistsRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public TokenRequest.Builder getTokens() {
    TokenRequest.Builder builder = TokenRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public RefreshAccessTokenRequest.Builder refreshAccessToken() {
    RefreshAccessTokenRequest.Builder builder = RefreshAccessTokenRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public ApplicationAuthenticationRequest.Builder applicationAuthentication() {
    ApplicationAuthenticationRequest.Builder builder = ApplicationAuthenticationRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public PlaylistRequest.Builder getPlaylist() {
    PlaylistRequest.Builder builder = PlaylistRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public CurrentUserRequest.Builder getCurrentUser() {
    final CurrentUserRequest.Builder builder = CurrentUserRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public PlaylistCreationRequest.Builder createPlaylist() {
    final PlaylistCreationRequest.Builder builder = PlaylistCreationRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public AddTrackToPlaylistRequest.Builder addTracksToPlaylist() {
    final AddTrackToPlaylistRequest.Builder builder = AddTrackToPlaylistRequest.builder();
    setDefaults(builder);
    if (accessToken != null) {
      builder.withAccessToken(accessToken);
    }
    return builder;
  }

  private void setDefaults(Request.Builder builder) {
    builder.httpManager(httpManager);
    builder.scheme(scheme);
    builder.host(host);
    builder.port(port);
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public static class Builder {

    private String host = DEFAULT_HOST;
    private int port = DEFAULT_PORT;
    private HttpManager httpManager = null;
    private Scheme scheme = DEFAULT_SCHEME;
    private String accessToken;

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

    public Builder accessToken(String accessToken) {
      this.accessToken = accessToken;
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

