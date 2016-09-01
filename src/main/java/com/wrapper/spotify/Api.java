package com.wrapper.spotify;

import com.wrapper.spotify.UtilProtos.Url.Scheme;
import com.wrapper.spotify.methods.*;
import com.wrapper.spotify.methods.authentication.AuthorizationCodeGrantRequest;
import com.wrapper.spotify.methods.authentication.AuthorizationURLRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.methods.authentication.RefreshAccessTokenRequest;

import net.sf.json.JSONArray;

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
  private String refreshToken;
  private final String clientId;
  private final String clientSecret;
  private final String redirectURI;

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
    accessToken = builder.accessToken;
    refreshToken = builder.refreshToken;
    clientId = builder.clientId;
    clientSecret = builder.clientSecret;
    redirectURI = builder.redirectURI;
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
    builder.path(String.format("/v1/artists/%s", id));
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

  public NewReleasesRequest.Builder getNewReleases() {
    NewReleasesRequest.Builder builder = NewReleasesRequest.builder();
    setDefaults(builder);
    return builder;
  }

  public AudioFeatureRequest.Builder getAudioFeature(String id) {
    AudioFeatureRequest.Builder builder = AudioFeatureRequest.builder();
    setDefaults(builder);
    builder.id(id);
    return builder;
  }

  /**
   * Used to get Featured Playlists.
   * @return A builder that can be used to build requests to get featured playlists.
   */
  public FeaturedPlaylistsRequest.Builder getFeaturedPlaylists() {
    FeaturedPlaylistsRequest.Builder builder = FeaturedPlaylistsRequest.builder();
    setDefaults(builder);
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
    userId = UrlUtil.userToUri(userId);
    builder.username(userId);
    return builder;
  }

  public UserPlaylistsRequest.Builder getPlaylistsForUser(String userId) {
    UserPlaylistsRequest.Builder builder = UserPlaylistsRequest.builder();
    setDefaults(builder);
    userId = UrlUtil.userToUri(userId);
    builder.username(userId);
    return builder;
  }

  /**
   * Returns a builder that can be used to build requests for authorization code
   * grants.
   * Requires client ID, client secret, and redirect URI to be set.
   * @param code An authorization code.
   * @return A builder that builds authorization code grant requests.
   */
  public AuthorizationCodeGrantRequest.Builder authorizationCodeGrant(String code) {
    AuthorizationCodeGrantRequest.Builder builder = AuthorizationCodeGrantRequest.builder();
    setDefaults(builder);
    builder.grantType("authorization_code");
    builder.basicAuthorizationHeader(clientId, clientSecret);
    builder.code(code);
    builder.redirectUri(redirectURI);
    return builder;
  }

  /**
   * Returns a builder that can be used to build requests to refresh an access token
   * that has been retrieved using the authorization code grant flow.
   * @return A builder that builds refresh access token requests.
   */
  public RefreshAccessTokenRequest.Builder refreshAccessToken() {
    RefreshAccessTokenRequest.Builder builder = RefreshAccessTokenRequest.builder();
    setDefaults(builder);
    builder.grantType("refresh_token");
    builder.refreshToken(refreshToken);
    builder.basicAuthorizationHeader(clientId, clientSecret);
    return builder;
  }

  /**
   * Returns a builder that can be used to build requests for client credential grants.
   * Requires client ID and client secret to be set.
   * @return A builder that builds client credential grant requests.
   */
  public ClientCredentialsGrantRequest.Builder clientCredentialsGrant() {
    ClientCredentialsGrantRequest.Builder builder = ClientCredentialsGrantRequest.builder();
    setDefaults(builder);
    builder.grantType("client_credentials");
    builder.basicAuthorizationHeader(clientId, clientSecret);
    return builder;
  }

  /**
   * Get a playlist.
   * @param userId The playlist's owner's username.
   * @param playlistId The playlist's ID.
   * @return A builder object that can be used to build a request to retrieve a playlist.
   */
  public PlaylistRequest.Builder getPlaylist(String userId, String playlistId) {
    PlaylistRequest.Builder builder = PlaylistRequest.builder();
    setDefaults(builder);
    userId = UrlUtil.userToUri(userId);
    builder.path("/v1/users/" + userId + "/playlists/" + playlistId);
    return builder;
  }

  /**
   * Get information about the user that has given authorization to the application.
   * @return A builder object that can be used to build a request to retrieve information
   * about the current user.
   */
  public CurrentUserRequest.Builder getMe() {
    final CurrentUserRequest.Builder builder = CurrentUserRequest.builder();
    setDefaults(builder);
    return builder;
  }

  /**
   * Create a playlist.
   * @param userId The playlist's owner.
   * @param title The name of the playlist.
   * @return A builder object that can be used to build a request to create a playlist.
   */
  public PlaylistCreationRequest.Builder createPlaylist(String userId, String title) {
    final PlaylistCreationRequest.Builder builder = PlaylistCreationRequest.builder();
    setDefaults(builder);
    builder.title(title);
    userId = UrlUtil.userToUri(userId);
    builder.path("/v1/users/" + userId + "/playlists");
    return builder;
  }

  /**
   * Get artists related/similar to an artist.
   * @param artistId The artist's id.
   * @return A builder object that can be used to build a request to retrieve similar artists.
   */
  public RelatedArtistsRequest.Builder getArtistRelatedArtists(String artistId) {
    final RelatedArtistsRequest.Builder builder = RelatedArtistsRequest.builder();
    setDefaults(builder);
    builder.path("/v1/artists/" + artistId + "/related-artists");
    return builder;
  }

  /**
   * Get a playlist's tracks.
   * @param userId The playlist's owner's username.
   * @param playlistId The playlist's id.
   * @return A builder object that can be used to build a request to retrieve playlist tracks.
   */
  public PlaylistTracksRequest.Builder getPlaylistTracks(String userId, String playlistId) {
    final PlaylistTracksRequest.Builder builder = PlaylistTracksRequest.builder();
    setDefaults(builder);
    userId = UrlUtil.userToUri(userId);
    builder.path("/v1/users/" + userId + "/playlists/" + playlistId + "/tracks");
    return builder;
  }

  /**
   * Get a user's starred tracks.
   * @param userId The starred playlist's owner's username.
   * @return A builder object that can be used to build a request to retrieve a user's starred
   * tracks.
   */
  public PlaylistTracksRequest.Builder getStarred(String userId) {
    final PlaylistTracksRequest.Builder builder = PlaylistTracksRequest.builder();
    setDefaults(builder);
    userId = UrlUtil.userToUri(userId);
    builder.path("/v1/users/" + userId + "/starred/tracks");
    return builder;
  }

  private void setDefaults(AbstractRequest.Builder builder) {
    builder.httpManager(httpManager);
    builder.scheme(scheme);
    builder.host(host);
    builder.port(port);
    if (accessToken != null) {
      builder.header("Authorization", "Bearer " + accessToken);
    }
  }

  /**
   * Add tracks to a playlist.
   * @param userId The owner's username.
   * @param playlistId The playlist's ID.
   * @param trackUris URIs of the tracks to add.
   * @return A builder object that can e used to build a request to add tracks to a playlist.
   */
  public AddTrackToPlaylistRequest.Builder addTracksToPlaylist(String userId, String playlistId, List<String> trackUris) {
    final AddTrackToPlaylistRequest.Builder builder = AddTrackToPlaylistRequest.builder();
    setDefaults(builder);
    final JSONArray jsonArrayUri = new JSONArray();
    jsonArrayUri.addAll(trackUris);
    builder.body(jsonArrayUri);
    userId = UrlUtil.userToUri(userId);
    builder.path("/v1/users/" + userId + "/playlists/" + playlistId + "/tracks");
    return builder;
  }

  /**
   * Update a playlist's properties.
   * @param userId The owner's username.
   * @param playlistId The playlist's ID.
   * @return A builder object that can be used to build a request to change a playlist's details.
   */
  public ChangePlaylistDetailsRequest.Builder changePlaylistDetails(String userId, String playlistId) {
    final ChangePlaylistDetailsRequest.Builder builder = ChangePlaylistDetailsRequest.builder();
    setDefaults(builder);
    userId = UrlUtil.userToUri(userId);
    builder.path("/v1/users/" + userId + "/playlists/" + playlistId);
    return builder;
  }

  /**
   * Get a users Your Music tracks.
   * @return A builder object that can be used to build a request to get the user's Your Music library.
   */
  public GetMySavedTracksRequest.Builder getMySavedTracks() {
    final GetMySavedTracksRequest.Builder builder = GetMySavedTracksRequest.builder();
    setDefaults(builder);
    builder.path("/v1/me/tracks");
    return builder;
  }

  /**
   * Check if a track is saved in the user's Your Music library.
   * @param trackIds The tracks ids to check for in the user's Your Music library.
   * @return A builder object that can be used to check if a user has saved a track.
   */
  public ContainsMySavedTracksRequest.Builder containsMySavedTracks(List<String> trackIds) {
    final ContainsMySavedTracksRequest.Builder builder = ContainsMySavedTracksRequest.builder();
    setDefaults(builder);
    builder.tracks(trackIds);
    builder.path("/v1/me/tracks/contains");
    return builder;
  }

  /**
   * Remove a track if saved to the user's Your Music library.
   * @param trackIds The track ids to remove from the user's Your Music library.
   * @return A builder object that can be used to remove tracks from the user's library.
   */
  public RemoveFromMySavedTracksRequest.Builder removeFromMySavedTracks(List<String> trackIds) {
    final RemoveFromMySavedTracksRequest.Builder builder = RemoveFromMySavedTracksRequest.builder();
    setDefaults(builder);
    builder.tracks(trackIds);
    builder.path("/v1/me/tracks");
    return builder;
  }

  /**
   * Save tracks in the user's Your Music library.
   * @param trackIds The track ids to add to the user's library.
   * @return A builder object that can be used to add tracks to the user's library.
   */
  public AddToMySavedTracksRequest.Builder addToMySavedTracks(List<String> trackIds) {
    final AddToMySavedTracksRequest.Builder builder = AddToMySavedTracksRequest.builder();
    setDefaults(builder);
    builder.tracks(trackIds);
    builder.path("/v1/me/tracks");
    return builder;
  }

  /**
   * Retrieve a URL where the user can give the application permissions.
   * @param scopes The scopes corresponding to the permissions the application needs
   * @param state state A parameter that you can use to maintain a value between the request
   *              and the callback to redirect_uri.It is useful to prevent CSRF exploits.
   * @return The URL where the user can give application permissions.
   */
  public String createAuthorizeURL(List<String> scopes, String state) {
    final AuthorizationURLRequest.Builder builder = AuthorizationURLRequest.builder();
    setDefaults(builder);
    builder.clientId(clientId);
    builder.responseType("code");
    builder.redirectURI(redirectURI);
    if (scopes != null) {
      builder.scopes(scopes);
    }
    if (state != null) {
      builder.state(state);
    }
    return builder.build().toStringWithQueryParameters();
  }

  /**
   * Retrieve a URL where the user can give the application permissions.
   * This method returns a builder instead, so that any optional parameters can be added.
   * @param scopes The scopes corresponding to the permissions the application needs.
   * @return A builder that when built creates a URL where the user can give the application
   * permissions.
   */
  public AuthorizationURLRequest.Builder createAuthorizeURL(List<String> scopes) {
    final AuthorizationURLRequest.Builder builder = AuthorizationURLRequest.builder();
    setDefaults(builder);
    builder.clientId(clientId);
    builder.responseType("code");
    builder.redirectURI(redirectURI);
    if (scopes != null) {
      builder.scopes(scopes);
    }
    return builder;
  }


  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public static class Builder {

    private String host = DEFAULT_HOST;
    private int port = DEFAULT_PORT;
    private HttpManager httpManager = null;
    private Scheme scheme = DEFAULT_SCHEME;
    private String accessToken;
    private String redirectURI;
    private String clientId;
    private String clientSecret;
    private String refreshToken;

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

    public Builder refreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    public Builder clientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    public Builder clientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
      return this;
    }

    public Builder redirectURI(String redirectURI) {
      this.redirectURI = redirectURI;
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

