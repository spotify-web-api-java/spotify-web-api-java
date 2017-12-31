package com.wrapper.spotify;

import com.google.common.base.Joiner;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.model_objects.special.PlaylistTrackPosition;
import com.wrapper.spotify.requests.authentication.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authentication.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.authentication.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.albums.GetAlbumsTracksRequest;
import com.wrapper.spotify.requests.data.albums.GetSeveralAlbumsRequest;
import com.wrapper.spotify.requests.data.artists.*;
import com.wrapper.spotify.requests.data.browse.*;
import com.wrapper.spotify.requests.data.browse.miscellaneous.GetAvailableGenreSeedsRequest;
import com.wrapper.spotify.requests.data.follow.UnfollowPlaylistRequest;
import com.wrapper.spotify.requests.data.library.CheckUsersSavedTracksRequest;
import com.wrapper.spotify.requests.data.library.GetUsersSavedTracksRequest;
import com.wrapper.spotify.requests.data.library.RemoveUsersSavedTracksRequest;
import com.wrapper.spotify.requests.data.library.SaveTracksForUserRequest;
import com.wrapper.spotify.requests.data.player.GetCurrentUsersRecentlyPlayedTracksRequest;
import com.wrapper.spotify.requests.data.player.GetUsersCurrentlyPlayingTrackRequest;
import com.wrapper.spotify.requests.data.playlists.*;
import com.wrapper.spotify.requests.data.search.simplified.SearchAlbumRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTrackRequest;
import com.wrapper.spotify.requests.data.tracks.GetAudioFeaturesForTrackRequest;
import com.wrapper.spotify.requests.data.tracks.GetSeveralTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import com.wrapper.spotify.requests.data.users_profile.GetUsersProfileRequest;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Instances of the Api class provide access to the Spotify Web API.
 */
public class Api {

  public static final String DEFAULT_AUTHENTICATION_HOST = "accounts.spotify.com";

  public static final int DEFAULT_AUTHENTICATION_PORT = 443;

  public static final String DEFAULT_AUTHENTICATION_SCHEME = "https";

  /**
   * The default host of Spotify API calls.
   */
  public static final String DEFAULT_HOST = "api.spotify.com";

  /**
   * A HttpManager configured with default settings.
   */
  public static final IHttpManager DEFAULT_HTTP_MANAGER = new SpotifyHttpManager.Builder().build();

  /**
   * The default port of Spotify API calls.
   */
  public static final int DEFAULT_PORT = 443;

  /**
   * The default http scheme of Spotify API calls.
   */
  public static final String DEFAULT_SCHEME = "https";

  private final IHttpManager httpManager;
  private final String scheme;
  private final String host;
  private final int port;
  private final String proxyUrl;
  private final int proxyPort;
  private final int proxyUsername;
  private final int proxyPassword;
  private final String clientId;
  private final String clientSecret;
  private final String redirectUri;
  private final String accessToken;
  private final String refreshToken;

  private Api(Builder builder) {
    assert (builder.httpManager != null);

    this.httpManager = builder.httpManager;
    this.scheme = builder.scheme;
    this.host = builder.host;
    this.port = builder.port;
    this.proxyUrl = builder.proxyUrl;
    this.proxyPort = builder.proxyPort;
    this.proxyUsername = builder.proxyUsername;
    this.proxyPassword = builder.proxyPassword;
    this.clientId = builder.clientId;
    this.clientSecret = builder.clientSecret;
    this.redirectUri = builder.redirectUri;
    this.accessToken = builder.accessToken;
    this.refreshToken = builder.refreshToken;
  }

  public IHttpManager getHttpManager() {
    return httpManager;
  }

  public String getScheme() {
    return scheme;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public String getProxyUrl() {
    return proxyUrl;
  }

  public int getProxyPort() {
    return proxyPort;
  }

  public int getProxyUsername() {
    return proxyUsername;
  }

  public int getProxyPassword() {
    return proxyPassword;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public String getRedirectURI() {
    return redirectUri;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
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
  public GetAlbumRequest.Builder getAlbum(String id) {
    GetAlbumRequest.Builder builder = new GetAlbumRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.id(id);
    return builder;
  }

  public GetSeveralAlbumsRequest.Builder getAlbums(String... ids) {
    assert (ids.length <= 20);
    GetSeveralAlbumsRequest.Builder builder = new GetSeveralAlbumsRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.ids(Joiner.on(",").join(ids));
    return builder;
  }

  public GetArtistsAlbumsRequest.Builder getAlbumsForArtist(String artistId) {
    GetArtistsAlbumsRequest.Builder builder = new GetArtistsAlbumsRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.id(artistId);
    return builder;
  }

  public GetAlbumsTracksRequest.Builder getTracksForAlbum(
          String albumId
  ) {
    GetAlbumsTracksRequest.Builder builder = new GetAlbumsTracksRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.id(albumId);
    return builder;
  }

  public GetArtistRequest.Builder getArtist(String id) {
    GetArtistRequest.Builder builder = new GetArtistRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.id(id);
    return builder;
  }

  public GetSeveralArtistsRequest.Builder getArtists(String... ids) {
    GetSeveralArtistsRequest.Builder builder = new GetSeveralArtistsRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.ids(Joiner.on(",").join(ids));
    return builder;
  }

  public GetTrackRequest.Builder getTrack(String id) {
    GetTrackRequest.Builder builder = new GetTrackRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.id(id);
    return builder;
  }

  public GetSeveralTracksRequest.Builder getTracks(String... ids) {
    return getTracks(Arrays.asList(ids));
  }

  public GetSeveralTracksRequest.Builder getTracks(List<String> ids) {
    GetSeveralTracksRequest.Builder builder = new GetSeveralTracksRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.ids(Joiner.on(",").join(ids));
    return builder;
  }

  public GetRecommendationsRequest.Builder getRecommendations() {
    GetRecommendationsRequest.Builder builder = new GetRecommendationsRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    return builder;
  }

  public SearchAlbumRequest.Builder searchAlbums(String query) {
    SearchAlbumRequest.Builder builder = new SearchAlbumRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.q(query);
    return builder;
  }

  public SearchTrackRequest.Builder searchTracks(String query) {
    SearchTrackRequest.Builder builder = new SearchTrackRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.q(query);
    return builder;
  }

  public SearchArtistRequest.Builder searchArtists(String query) {
    SearchArtistRequest.Builder builder = new SearchArtistRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.q(query);
    return builder;
  }

  public SearchPlaylistRequest.Builder searchPlaylists(String query) {
    SearchPlaylistRequest.Builder builder = new SearchPlaylistRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.q(query);
    return builder;
  }

  public GetListOfNewReleasesRequest.Builder getNewReleases() {
    GetListOfNewReleasesRequest.Builder builder = new GetListOfNewReleasesRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    return builder;
  }

  public GetAudioFeaturesForTrackRequest.Builder getAudioFeature(String id) {
    GetAudioFeaturesForTrackRequest.Builder builder = new GetAudioFeaturesForTrackRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.id(id);
    return builder;
  }

  public GetCurrentUsersRecentlyPlayedTracksRequest.Builder getRecentlyPlayedTracks() {
    GetCurrentUsersRecentlyPlayedTracksRequest.Builder builder = new GetCurrentUsersRecentlyPlayedTracksRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    return builder;
  }

  public GetUsersCurrentlyPlayingTrackRequest.Builder getCurrentlyPlayingTrack() {
    GetUsersCurrentlyPlayingTrackRequest.Builder builder = new GetUsersCurrentlyPlayingTrackRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    return builder;
  }

  /**
   * Used to get Featured Playlists.
   *
   * @return A builder that can be used to build requests to get featured playlists.
   */
  public GetListOfFeaturedPlaylistsRequest.Builder getFeaturedPlaylists() {
    GetListOfFeaturedPlaylistsRequest.Builder builder = new GetListOfFeaturedPlaylistsRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    return builder;
  }

  public GetArtistsTopTracksRequest.Builder getTopTracksForArtist(String id, CountryCode country) {
    GetArtistsTopTracksRequest.Builder builder = new GetArtistsTopTracksRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.id(id);
    builder.country(country);
    return builder;
  }

  public GetUsersProfileRequest.Builder getUser(String userId) {
    GetUsersProfileRequest.Builder builder = new GetUsersProfileRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.user_id(userId);
    return builder;
  }

  public GetListOfUsersPlaylistsRequest.Builder getPlaylistsForUser(String userId) {
    GetListOfUsersPlaylistsRequest.Builder builder = new GetListOfUsersPlaylistsRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.user_id(userId);
    return builder;
  }

  /**
   * Returns a builder that can be used to build requests for authorization code
   * grants.
   * Requires client ID, client secret, and redirect URI to be set.
   *
   * @param code An authorization code.
   * @return A builder that builds authorization code grant requests.
   */
  public AuthorizationCodeRequest.Builder authorizationCodeGrant(String code) {
    AuthorizationCodeRequest.Builder builder = new AuthorizationCodeRequest.Builder(clientId, clientSecret);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.setGrantType("authorization_code");
    builder.setCode(code);
    builder.setRedirectUri(redirectUri);
    return builder;
  }

  /**
   * Returns a builder that can be used to build requests for client credential grants.
   * Requires client ID and client secret to be set.
   *
   * @return A builder that builds client credential grant requests.
   */
  public ClientCredentialsRequest.Builder clientCredentialsGrant() {
    ClientCredentialsRequest.Builder builder = new ClientCredentialsRequest.Builder(clientId, clientSecret);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.setGrantType("client_credentials");
    return builder;
  }

  /**
   * Get a playlist.
   *
   * @param userId     The playlist's owner's username.
   * @param playlistId The playlist's ID.
   * @return A builder object that can be used to build a request to retrieve a playlist.
   */
  public GetPlaylistRequest.Builder getPlaylist(String userId, String playlistId) {
    GetPlaylistRequest.Builder builder = new GetPlaylistRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.user_id(userId);
    builder.playlist_id(playlistId);
    return builder;
  }

  /**
   * Get information about the user that has given authorization to the application.
   *
   * @return A builder object that can be used to build a request to retrieve information
   * about the current user.
   */
  public GetCurrentUsersProfileRequest.Builder getMe() {
    final GetCurrentUsersProfileRequest.Builder builder = new GetCurrentUsersProfileRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    return builder;
  }

  /**
   * Create a playlist.
   *
   * @param userId The playlist's owner.
   * @param name   The name of the playlist.
   * @return A builder object that can be used to build a request to create a playlist.
   */
  public CreatePlaylistRequest.Builder createPlaylist(String userId, String name) {
    final CreatePlaylistRequest.Builder builder = new CreatePlaylistRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.user_id(userId);
    builder.name(name);
    return builder;
  }

  /**
   * Get artists related/similar to an artist.
   *
   * @param id The artist's id.
   * @return A builder object that can be used to build a request to retrieve similar artists.
   */
  public GetArtistsRelatedArtistsRequest.Builder getArtistRelatedArtists(String id) {
    final GetArtistsRelatedArtistsRequest.Builder builder = new GetArtistsRelatedArtistsRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.id(id);
    return builder;
  }

  /**
   * Get a playlist's tracks.
   *
   * @param userId     The playlist's owner's username.
   * @param playlistId The playlist's id.
   * @return A builder object that can be used to build a request to retrieve playlist tracks.
   */
  public GetPlaylistsTracksRequest.Builder getPlaylistTracks(String userId, String playlistId) {
    final GetPlaylistsTracksRequest.Builder builder = new GetPlaylistsTracksRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.user_id(userId);
    builder.playlist_id(playlistId);
    return builder;
  }

  /**
   * Get a user's starred tracks.
   *
   * @param userId The starred playlist's owner's username.
   * @return A builder object that can be used to build a request to retrieve a user's starred
   * tracks.
   */
  public GetPlaylistsTracksRequest.Builder getStarred(String userId) {
    final GetPlaylistsTracksRequest.Builder builder = new GetPlaylistsTracksRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.setPath("/v1/users/" + userId + "/starred/tracks");
    return builder;
  }

  /**
   * Add tracks to a playlist.
   *
   * @param userId     The owner's username.
   * @param playlistId The playlist's ID.
   * @param trackUris  URIs of the tracks to add.
   * @return A builder object that can e used to build a request to add tracks to a playlist.
   */
  public AddTracksToPlaylistRequest.Builder addTracksToPlaylist(String userId, String playlistId, String[] trackUris) {
    final AddTracksToPlaylistRequest.Builder builder = new AddTracksToPlaylistRequest.Builder(accessToken);

    builder.setDefaults(httpManager, scheme, host, port);
    builder.user_id(userId);
    builder.playlist_id(playlistId);
    builder.uris(Joiner.on(",").join(trackUris));
    return builder;
  }

  /**
   * Replace tracks in a playlist.
   *
   * @param userId     The owner's username.
   * @param playlistId The playlist's ID.
   * @param trackUris  URIs of the tracks to add.
   * @return A builder object that can e used to build a request to add tracks to a playlist.
   */
  public ReplacePlaylistsTracksRequest.Builder replacePlaylistsTracks(String userId, String playlistId, String[] trackUris) {
    final ReplacePlaylistsTracksRequest.Builder builder = new ReplacePlaylistsTracksRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.user_id(userId);
    builder.playlist_id(playlistId);
    builder.uris(Joiner.on(",").join(trackUris));
    return builder;
  }

  /**
   * delete tracks from a playlist
   *
   * @param userId     The owner's username.
   * @param playlistId The playlist's ID.
   * @param trackUris  URIs of the tracks to remove.
   * @return A builder object that can be used to build a request to remove tracks from a playlist.
   */
  public RemoveTracksFromPlaylistRequest.Builder removeTrackFromPlaylist(String userId, String playlistId, String[] trackUris) {
    final RemoveTracksFromPlaylistRequest.Builder builder = new RemoveTracksFromPlaylistRequest.Builder(accessToken);

    builder.setDefaults(httpManager, scheme, host, port);
    builder.setQueryParameter("uris", Joiner.on(",").join(trackUris));
    builder.setPath("/v1/users/" + userId + "/playlists/" + playlistId + "/tracks");

    return builder;
  }

  /**
   * Update a playlist's properties.
   *
   * @param userId     The owner's username.
   * @param playlistId The playlist's ID.
   * @return A builder object that can be used to build a request to change a playlist's details.
   */
  public ChangePlaylistsDetailsRequest.Builder changePlaylistDetails(String userId, String playlistId) {
    final ChangePlaylistsDetailsRequest.Builder builder = new ChangePlaylistsDetailsRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.user_id(userId);
    builder.playlist_id(playlistId);
    return builder;
  }

  public RemoveTracksFromPlaylistRequest.Builder removeTrackFromPlaylist(String userId, String playlistId, PlaylistTrackPosition[] playlistTrackPositions) {
    final RemoveTracksFromPlaylistRequest.Builder builder = new RemoveTracksFromPlaylistRequest.Builder(accessToken);

    builder.setDefaults(httpManager, scheme, host, port);

    JsonArray playlistTrackPositionJsonArray = new JsonArray();

    for (PlaylistTrackPosition playlistTrackPosition : playlistTrackPositions) {
      JsonObject playlistTrackPositionJsonObject = new JsonObject();

      playlistTrackPositionJsonObject.addProperty("uri", playlistTrackPosition.getUri());

      if (playlistTrackPosition.getPositions() != null) {
        JsonArray positionArray = new JsonArray();

        for (int position : playlistTrackPosition.getPositions()) {
          positionArray.add(position);
        }

        playlistTrackPositionJsonObject.add("positions", positionArray);
      }

      playlistTrackPositionJsonArray.add(playlistTrackPositionJsonObject);
    }

    JsonObject tracks = new JsonObject();
    tracks.add("tracks", playlistTrackPositionJsonArray);

    builder.setFormParameter("tracks", tracks.toString());
    builder.user_id(userId);
    builder.playlist_id(playlistId);
    return builder;
  }

  public ReorderPlaylistsTracksRequest.Builder reorderTracksInPlaylist(String userId, String playlistId, int rangeStart, int insertBefore) {
    final ReorderPlaylistsTracksRequest.Builder builder = new ReorderPlaylistsTracksRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.range_start(rangeStart);
    builder.insert_before(insertBefore);
    builder.user_id(userId);
    builder.playlist_id(playlistId);
    return builder;
  }

  /**
   * Remove the current user as a follower of a playlist.
   *
   * @param owner_id    The owner's username.
   * @param playlist_id The playlist's ID.
   * @return A builder object that can be used to build a request
   * to remove the current user as a follower of a playlist.
   */
  public UnfollowPlaylistRequest.Builder unfollowPlaylist(String owner_id, String playlist_id) {
    final UnfollowPlaylistRequest.Builder builder = new UnfollowPlaylistRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.owner_id(owner_id).playlist_id(playlist_id);
    return builder;
  }

  /**
   * Get a users Your Music tracks.
   *
   * @return A builder object that can be used to build a request to get the user's Your Music library.
   */
  public GetUsersSavedTracksRequest.Builder getMySavedTracks() {
    final GetUsersSavedTracksRequest.Builder builder = new GetUsersSavedTracksRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.setPath("/v1/me/tracks");
    return builder;
  }

  /**
   * Check if a track is saved in the user's Your Music library.
   *
   * @param trackIds The tracks ids to check for in the user's Your Music library.
   * @return A builder object that can be used to check if a user has saved a track.
   */
  public CheckUsersSavedTracksRequest.Builder containsMySavedTracks(String... trackIds) {
    final CheckUsersSavedTracksRequest.Builder builder = new CheckUsersSavedTracksRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.ids(Joiner.on(",").join(trackIds));
    builder.setPath("/v1/me/tracks/contains");
    return builder;
  }

  /**
   * Remove a track if saved to the user's Your Music library.
   *
   * @param trackIds The track ids to remove from the user's Your Music library.
   * @return A builder object that can be used to remove tracks from the user's library.
   */
  public RemoveUsersSavedTracksRequest.Builder removeFromMySavedTracks(String... trackIds) {
    final RemoveUsersSavedTracksRequest.Builder builder = new RemoveUsersSavedTracksRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.ids(Joiner.on(",").join(trackIds));
    builder.setPath("/v1/me/tracks");
    return builder;
  }

  /**
   * Save tracks in the user's Your Music library.
   *
   * @param trackIds The track ids to add to the user's library.
   * @return A builder object that can be used to add tracks to the user's library.
   */
  public SaveTracksForUserRequest.Builder addToMySavedTracks(String... trackIds) {
    final SaveTracksForUserRequest.Builder builder = new SaveTracksForUserRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.ids(Joiner.on(",").join(trackIds));
    builder.setPath("/v1/me/tracks");
    return builder;
  }

  /**
   * Retrieve a URL where the user can give the application permissions.
   *
   * @param scopes     The scopes corresponding to the permissions the application needs
   * @param state      state A parameter that you can use to maintain a value between the request
   *                   and the callback to redirect_uri.It is useful to prevent CSRF exploits.
   * @param showDialog - (optional) whether or not to force the user to login
   * @return The URL where the user can give application permissions.
   */
  public URI createAuthorizeUri(String[] scopes, String state, boolean showDialog) {
    final AuthorizationCodeUriRequest.Builder builder = new AuthorizationCodeUriRequest.Builder();

    builder.setDefaults(httpManager, scheme, host, port);

    builder.setClientId(clientId);
    builder.setResponseType("code");
    builder.setRedirectUri(redirectUri);

    if (scopes != null) {
      builder.setScope(Joiner.on(" ").join(scopes));
    }

    if (state != null) {
      builder.setState(state);
    }

    builder.setShowDialog(showDialog);

    return builder.build().getUri();
  }

  /**
   * Retrieve a URL where the user can give the application permissions.
   *
   * @param scopes The scopes corresponding to the permissions the application needs
   * @param state  state A parameter that you can use to maintain a value between the request
   *               and the callback to redirect_uri.It is useful to prevent CSRF exploits.
   * @return The URL where the user can give application permissions.
   */
  public URI createAuthorizeUri(String[] scopes, String state) {
    final AuthorizationCodeUriRequest.Builder builder = new AuthorizationCodeUriRequest.Builder();

    builder.setDefaults(httpManager, scheme, host, port);

    builder.setClientId(clientId);
    builder.setResponseType("code");
    builder.setRedirectUri(redirectUri);

    if (scopes != null) {
      builder.setScope(Joiner.on(" ").join(scopes));
    }

    if (state != null) {
      builder.setState(state);
    }

    return builder.build().getUri();
  }

  /**
   * Retrieve a URL where the user can give the application permissions.
   * This method returns a builder instead, so that any optional parameters can be added.
   *
   * @param scopes The scopes corresponding to the permissions the application needs.
   * @return The URL where the user can give application permissions.
   */
  public URI createAuthorizeUri(String... scopes) {
    final AuthorizationCodeUriRequest.Builder builder = new AuthorizationCodeUriRequest.Builder();

    builder.setDefaults(httpManager, scheme, host, port);

    builder.setClientId(clientId);
    builder.setResponseType("code");
    builder.setRedirectUri(redirectUri);

    if (scopes != null) {
      builder.setScope(Joiner.on(" ").join(scopes));
    }

    return builder.build().getUri();
  }

  public GetRecommendationsRequest.Builder getRecommendations(String... ids) {
    GetRecommendationsRequest.Builder builder = new GetRecommendationsRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    builder.seed_genres(Joiner.on(",").join(ids));
    return builder;
  }

  public GetAvailableGenreSeedsRequest.Builder getAvailableGenreSeeds() {
    GetAvailableGenreSeedsRequest.Builder builder = new GetAvailableGenreSeedsRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    return builder;
  }

  public GetListOfCategoriesRequest.Builder getCategories() {
    GetListOfCategoriesRequest.Builder builder = new GetListOfCategoriesRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    return builder;
  }

  public GetCategoryRequest.Builder getCategory(String categoryId) {
    GetCategoryRequest.Builder builder = new GetCategoryRequest.Builder(accessToken).category_id(categoryId);
    builder.setDefaults(httpManager, scheme, host, port);
    return builder;
  }

  public GetCategorysPlaylistsRequest.Builder getPlaylistsForCategory(String categoryId) {
    GetCategorysPlaylistsRequest.Builder builder = new GetCategorysPlaylistsRequest.Builder(accessToken);
    builder.setDefaults(httpManager, scheme, host, port);
    return builder
            .category_id(categoryId);
  }

  public static class Builder {

    private IHttpManager httpManager = DEFAULT_HTTP_MANAGER;
    private String scheme = DEFAULT_SCHEME;
    private String host = DEFAULT_HOST;
    private int port = DEFAULT_PORT;
    private String proxyUrl;
    private int proxyPort;
    private int proxyUsername;
    private int proxyPassword;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String accessToken;
    private String refreshToken;

    public Builder setHttpManager(IHttpManager httpManager) {
      this.httpManager = httpManager;
      return this;
    }

    public Builder setScheme(String scheme) {
      this.scheme = scheme;
      return this;
    }

    public Builder setHost(String host) {
      this.host = host;
      return this;
    }

    public Builder setPort(int port) {
      this.port = port;
      return this;
    }

    public Builder setProxyUrl(String proxyUrl) {
      this.proxyUrl = proxyUrl;
      return this;
    }

    public Builder setProxyPort(int proxyPort) {
      this.proxyPort = proxyPort;
      return this;
    }

    public Builder setProxyUsername(int proxyUsername) {
      this.proxyUsername = proxyUsername;
      return this;
    }

    public Builder setProxyPassword(int proxyPassword) {
      this.proxyPassword = proxyPassword;
      return this;
    }

    public Builder setClientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    public Builder setClientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
      return this;
    }

    public Builder setRedirectUri(String redirectUri) {
      this.redirectUri = redirectUri;
      return this;
    }

    public Builder setAccessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public Builder setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    public Api build() {
      return new Api(this);
    }
  }
}

