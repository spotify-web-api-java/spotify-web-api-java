package com.wrapper.spotify;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.albums.GetAlbumsTracksRequest;
import com.wrapper.spotify.requests.data.albums.GetSeveralAlbumsRequest;
import com.wrapper.spotify.requests.data.artists.*;
import com.wrapper.spotify.requests.data.browse.*;
import com.wrapper.spotify.requests.data.browse.miscellaneous.GetAvailableGenreSeedsRequest;
import com.wrapper.spotify.requests.data.follow.*;
import com.wrapper.spotify.requests.data.library.*;
import com.wrapper.spotify.requests.data.personalization.GetUsersTopArtistsAndTracksRequest;
import com.wrapper.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import com.wrapper.spotify.requests.data.player.*;
import com.wrapper.spotify.requests.data.playlists.*;
import com.wrapper.spotify.requests.data.search.SearchItemRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.*;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import com.wrapper.spotify.requests.data.users_profile.GetUsersProfileRequest;

import java.util.logging.Logger;

/**
 * Instances of the Api class provide access to the Spotify Web API.
 */
public class SpotifyApi {

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

  public static final Logger LOGGER = Logger.getLogger(SpotifyApi.class.getName());

  private final IHttpManager httpManager;
  private final String scheme;
  private final String host;
  private final Integer port;
  private final String proxyUrl;
  private final Integer proxyPort;
  private final Integer proxyUsername;
  private final Integer proxyPassword;
  private final String clientId;
  private final String clientSecret;
  private final String redirectUri;
  private String accessToken;
  private String refreshToken;

  private SpotifyApi(Builder builder) {
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

  /**
   * Create a builder for building a new Spotify API instance.
   *
   * @return A builder object.
   */
  public static Builder builder() {
    return new Builder();
  }

  public static String concat(String[] parts, char character) {
    StringBuilder stringBuilder = new StringBuilder();

    for (String part : parts) {
      stringBuilder.append(part).append(character);
    }

    stringBuilder.deleteCharAt(stringBuilder.length() - 1);

    return stringBuilder.toString();
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

  public Integer getPort() {
    return port;
  }

  public String getProxyUrl() {
    return proxyUrl;
  }

  public Integer getProxyPort() {
    return proxyPort;
  }

  public Integer getProxyUsername() {
    return proxyUsername;
  }

  public Integer getProxyPassword() {
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

  /**
   * Set the acces token in a builder object.
   *
   * @param accessToken A Spotify API access token.
   */
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  /**
   * Set the refresh token in a builder object.
   *
   * @param refreshToken A Spotify API refresh token.
   */
  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public AuthorizationCodeRefreshRequest.Builder authorizationCodeRefresh(String client_id, String client_secret, String refresh_token) {
    assert (client_id != null);
    assert (!client_id.equals(""));
    assert (client_secret != null);
    assert (!client_secret.equals(""));
    assert (refresh_token != null);
    assert (!refresh_token.equals(""));
    return new AuthorizationCodeRefreshRequest.Builder(client_id, client_secret)
            .setDefaults(httpManager, scheme, host, port)
            .grant_type("refresh_token")
            .refresh_token(refresh_token);
  }

  public AuthorizationCodeRefreshRequest.Builder authorizationCodeRefresh() {
    return new AuthorizationCodeRefreshRequest.Builder(clientId, clientSecret)
            .setDefaults(httpManager, scheme, host, port)
            .grant_type("refresh_token")
            .refresh_token(refreshToken);
  }

  /**
   * Returns a builder that can be used to build requests for authorization code
   * grants.
   * Requires client ID, client secret, and redirect URI to be set.
   *
   * @param client_id     Required. When you register your application, Spotify provides you a Client ID.
   * @param client_secret Required. When you register your application, Spotify provides you a Client Secret.
   * @param code          Required. The authorization code returned from the initial request to the Account /authorize
   *                      endpoint.
   * @param redirect_uri  Required. This parameter is used for validation only (there is no actual redirection). The
   *                      value of this parameter must exactly match the value of redirect_uri supplied when requesting
   *                      the authorization code.
   * @return A builder that builds authorization code grant requests.
   */
  public AuthorizationCodeRequest.Builder authorizationCode(String client_id, String client_secret, String code, String redirect_uri) {
    assert (client_id != null);
    assert (!client_id.equals(""));
    assert (client_secret != null);
    assert (!client_secret.equals(""));
    assert (code != null);
    assert (!code.equals(""));
    assert (redirect_uri != null);
    assert (!redirect_uri.equals(""));
    return new AuthorizationCodeRequest.Builder(clientId, clientSecret)
            .setDefaults(httpManager, scheme, host, port)
            .grant_type("authorization_code")
            .code(code)
            .redirect_uri(redirect_uri);
  }

  public AuthorizationCodeRequest.Builder authorizationCode(String code) {
    assert (code != null);
    assert (!code.equals(""));
    return new AuthorizationCodeRequest.Builder(clientId, clientSecret)
            .setDefaults(httpManager, scheme, host, port)
            .grant_type("authorization_code")
            .code(code)
            .redirect_uri(redirectUri);
  }

  /**
   * Retrieve a URL where the user can give the application permissions.
   *
   * @param client_id    Required. When you register your application, Spotify provides you a Client ID.
   * @param redirect_uri Required. This parameter is used for validation only (there is no actual redirection). The
   *                     value of this parameter must exactly match the value of redirect_uri supplied when requesting
   *                     the authorization code.
   * @return The URL where the user can give application permissions.
   */
  public AuthorizationCodeUriRequest.Builder authorizationCodeUri(String client_id, String redirect_uri) {
    return new AuthorizationCodeUriRequest.Builder()
            .setDefaults(httpManager, scheme, host, port)
            .client_id(client_id)
            .response_type("code")
            .redirect_uri(redirect_uri);
  }

  public AuthorizationCodeUriRequest.Builder authorizationCodeUri() {
    return new AuthorizationCodeUriRequest.Builder()
            .setDefaults(httpManager, scheme, host, port)
            .client_id(clientId)
            .response_type("code")
            .redirect_uri(redirectUri);
  }

  /**
   * Returns a builder that can be used to build requests for client credential grants.
   * Requires client ID and client secret to be set.
   *
   * @return A builder that builds client credential grant requests.
   */
  public ClientCredentialsRequest.Builder clientCredentials() {
    return new ClientCredentialsRequest.Builder(clientId, clientSecret)
            .setDefaults(httpManager, scheme, host, port)
            .grant_type("client_credentials");
  }

  /**
   * Returns a an album with the id given below.
   *
   * @param id The base62 id of the album you're trying to retrieve.
   * @return An {AlbumRequest.Builder} instance.
   */
  public GetAlbumRequest.Builder getAlbum(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetAlbumRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  public GetAlbumsTracksRequest.Builder getAlbumsTracks(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetAlbumsTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  /**
   * Get multiple albums.
   *
   * @param ids The base62 ids of all albums you're trying to retrieve.
   * @return A builder that builds a request to retrieve multiple albums
   */
  public GetSeveralAlbumsRequest.Builder getSeveralAlbums(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 20);
    return new GetSeveralAlbumsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  /**
   * Get an artist.
   *
   * @param id The base62 id of the artist.
   * @return A builder that builds a request to retrieve an artist.
   */
  public GetArtistRequest.Builder getArtist(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetArtistRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  /**
   * Get albums of a specific artist.
   *
   * @param id The base62 id of the artist.
   * @return A builder that builds a request to retrieve the albums of an artist.
   */
  public GetArtistsAlbumsRequest.Builder getArtistsAlbums(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetArtistsAlbumsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  /**
   * Get the top tracks of an artist in a specific country.
   *
   * @param id      A base62 id of the artist.
   * @param country The ISO 3166-1 alpha-2 country code of the specific country.
   * @return A builder that builds a request to retrieve the top tracks of an artist.
   */
  public GetArtistsTopTracksRequest.Builder getArtistsTopTracks(String id, CountryCode country) {
    assert (id != null);
    assert (!id.equals(""));
    assert (country != null);
    return new GetArtistsTopTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id)
            .country(country);
  }

  /**
   * Get artists related/similar to an artist.
   *
   * @param id The artist's id.
   * @return A builder object that can be used to build a request to retrieve similar artists.
   */
  public GetArtistsRelatedArtistsRequest.Builder getArtistsRelatedArtists(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetArtistsRelatedArtistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  /**
   * Get multiple artists.
   *
   * @param ids The base62 ids of all artists you're trying to retrieve.
   * @return A builder that builds a request to retrieve multiple artists.
   */
  public GetSeveralArtistsRequest.Builder getSeveralArtists(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new GetSeveralArtistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  public GetCategoryRequest.Builder getCategory(String category_id) {
    assert (category_id != null);
    assert (!category_id.equals(""));
    return new GetCategoryRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .category_id(category_id);
  }

  public GetCategorysPlaylistsRequest.Builder getCategorysPlaylists(String category_id) {
    assert (category_id != null);
    assert (!category_id.equals(""));
    return new GetCategorysPlaylistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .category_id(category_id);
  }

  public GetListOfCategoriesRequest.Builder getListOfCategories() {
    return new GetListOfCategoriesRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get "Featured Playlists" of different countries which may match a specific language.
   *
   * @return A builder that builds a request to retrieve featured playlists.
   */
  public GetListOfFeaturedPlaylistsRequest.Builder getListOfFeaturedPlaylists() {
    return new GetListOfFeaturedPlaylistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get the newest releases from a specific country.
   *
   * @return A builder that builds a request to retrieve the newest releases.
   */
  public GetListOfNewReleasesRequest.Builder getListOfNewReleases() {
    return new GetListOfNewReleasesRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public GetRecommendationsRequest.Builder getRecommendations() {
    return new GetRecommendationsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public GetAvailableGenreSeedsRequest.Builder getAvailableGenreSeeds() {
    return new GetAvailableGenreSeedsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public CheckCurrentUserFollowsArtistsOrUsersRequest.Builder checkCurrentUserFollowsArtistsOrUsers(
          ModelObjectType type, String[] ids) {
    assert (type != null);
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new CheckCurrentUserFollowsArtistsOrUsersRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .type(type)
            .ids(concat(ids, ','));
  }

  public CheckUsersFollowPlaylistRequest.Builder checkUsersFollowPlaylist(
          String owner_id, String playlist_id, String[] ids) {
    assert (owner_id != null);
    assert (playlist_id != null);
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 5);
    return new CheckUsersFollowPlaylistRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .owner_id(owner_id)
            .playlist_id(playlist_id)
            .ids(concat(ids, ','));
  }

  public FollowArtistsOrUsersRequest.Builder followArtistsOrUsers(ModelObjectType type, String[] ids) {
    assert (type != null);
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new FollowArtistsOrUsersRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .type(type)
            .ids(concat(ids, ','));
  }

  public FollowArtistsOrUsersRequest.Builder followArtistsOrUsers(ModelObjectType type, JsonArray ids) {
    assert (type != null);
    assert (type.getType().equals("artist") || type.getType().equals("user"));
    assert (ids != null);
    assert (ids.size() <= 50);
    return new FollowArtistsOrUsersRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .type(type)
            .ids(concat(new Gson().fromJson(ids, String[].class), ','));
  }

  public FollowPlaylistRequest.Builder followPlaylist(String owner_id, String playlist_id, boolean public_) {
    assert (owner_id != null);
    assert (!owner_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    return new FollowPlaylistRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .owner_id(owner_id)
            .playlist_id(playlist_id)
            .public_(public_);
  }

  public GetUsersFollowedArtistsRequest.Builder getUsersFollowedArtists(ModelObjectType type) {
    assert (type != null);
    return new GetUsersFollowedArtistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .type(type);
  }

  public UnfollowArtistsOrUsersRequest.Builder unfollowArtistsOrUsers(ModelObjectType type, String[] ids) {
    assert (type != null);
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new UnfollowArtistsOrUsersRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .type(type)
            .ids(concat(ids, ','));
  }

  public UnfollowArtistsOrUsersRequest.Builder unfollowArtistsOrUsers(ModelObjectType type, JsonArray ids) {
    assert (type != null);
    assert (ids != null);
    assert (ids.size() <= 50);
    return new UnfollowArtistsOrUsersRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .type(type)
            .ids(concat(new Gson().fromJson(ids, String[].class), ','));
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
    assert (owner_id != null);
    assert (!owner_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    return new UnfollowPlaylistRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .owner_id(owner_id)
            .playlist_id(playlist_id);
  }

  /**
   * Check if a track is saved in the user's Your Music library.
   *
   * @param ids The tracks ids to check for in the user's Your Music library.
   * @return A builder object that can be used to check if a user has saved a track.
   */
  public CheckUsersSavedAlbumsRequest.Builder checkUsersSavedAlbums(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new CheckUsersSavedAlbumsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  /**
   * Check if a track is saved in the user's Your Music library.
   *
   * @param ids The tracks ids to check for in the user's Your Music library.
   * @return A builder object that can be used to check if a user has saved a track.
   */
  public CheckUsersSavedTracksRequest.Builder checkUsersSavedTracks(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new CheckUsersSavedTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  public GetCurrentUsersSavedAlbumsRequest.Builder getCurrentUsersSavedAlbums() {
    return new GetCurrentUsersSavedAlbumsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get a users Your Music tracks.
   *
   * @return A builder object that can be used to build a request to get the user's Your Music library.
   */
  public GetUsersSavedTracksRequest.Builder getUsersSavedTracks() {
    return new GetUsersSavedTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public RemoveAlbumsForCurrentUserRequest.Builder removeAlbumsForCurrentUser(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new RemoveAlbumsForCurrentUserRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  public RemoveAlbumsForCurrentUserRequest.Builder removeAlbumsForCurrentUser(JsonArray ids) {
    assert (ids != null);
    assert (ids.size() <= 50);
    return new RemoveAlbumsForCurrentUserRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(new Gson().fromJson(ids, String[].class), ','));
  }

  /**
   * Remove a track if saved to the user's Your Music library.
   *
   * @param ids The track ids to remove from the user's Your Music library.
   * @return A builder object that can be used to remove tracks from the user's library.
   */
  public RemoveUsersSavedTracksRequest.Builder removeUsersSavedTracks(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new RemoveUsersSavedTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  /**
   * Remove a track if saved to the user's Your Music library.
   *
   * @param ids The track ids to remove from the user's Your Music library.
   * @return A builder object that can be used to remove tracks from the user's library.
   */
  public RemoveUsersSavedTracksRequest.Builder removeUsersSavedTracks(JsonArray ids) {
    assert (ids != null);
    assert (ids.size() <= 50);
    return new RemoveUsersSavedTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(new Gson().fromJson(ids, String[].class), ','));
  }

  /**
   * Save tracks in the user's Your Music library.
   *
   * @param ids The track ids to add to the user's library.
   * @return A builder object that can be used to add tracks to the user's library.
   */
  public SaveAlbumsForCurrentUserRequest.Builder saveAlbumsForCurrentUser(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new SaveAlbumsForCurrentUserRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  /**
   * Save tracks in the user's Your Music library.
   *
   * @param ids The track ids to add to the user's library.
   * @return A builder object that can be used to add tracks to the user's library.
   */
  public SaveAlbumsForCurrentUserRequest.Builder saveAlbumsForCurrentUser(JsonArray ids) {
    assert (ids != null);
    assert (ids.size() <= 50);
    return new SaveAlbumsForCurrentUserRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(new Gson().fromJson(ids, String[].class), ','));
  }

  /**
   * Save tracks in the user's Your Music library.
   *
   * @param ids The track ids to add to the user's library.
   * @return A builder object that can be used to add tracks to the user's library.
   */
  public SaveTracksForUserRequest.Builder saveTracksForUser(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new SaveTracksForUserRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  public SaveTracksForUserRequest.Builder saveTracksForUser(JsonArray ids) {
    assert (ids != null);
    assert (ids.size() <= 50);
    return new SaveTracksForUserRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(new Gson().fromJson(ids, String[].class), ','));
  }

  public <T extends IArtistTrackModelObject> GetUsersTopArtistsAndTracksRequest.Builder getUsersTopArtistsAndTracks(ModelObjectType type) {
    assert (type != null);
    return new GetUsersTopArtistsAndTracksRequest.Builder<T>(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .type(type);
  }

  public GetUsersTopArtistsRequest.Builder getUsersTopArtists() {
    return new GetUsersTopArtistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public GetUsersTopTracksRequest.Builder getUsersTopTracks() {
    return new GetUsersTopTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public GetInformationAboutUsersCurrentPlaybackRequest.Builder getInformationAboutUsersCurrentPlayback() {
    return new GetInformationAboutUsersCurrentPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public GetCurrentUsersRecentlyPlayedTracksRequest.Builder getCurrentUsersRecentlyPlayedTracks() {
    return new GetCurrentUsersRecentlyPlayedTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public GetUsersCurrentlyPlayingTrackRequest.Builder getUsersCurrentlyPlayingTrack() {
    return new GetUsersCurrentlyPlayingTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public PauseUsersPlaybackRequest.Builder pauseUsersPlayback() {
    return new PauseUsersPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public SeekToPositionInCurrentlyPlayingTrackRequest.Builder seekToPositionInCurrentlyPlayingTrack(int position_ms) {
    assert (position_ms >= 0);
    return new SeekToPositionInCurrentlyPlayingTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .position_ms(position_ms);
  }

  public SetRepeatModeOnUsersPlaybackRequest.Builder setRepeatModeOnUsersPlayback(String state) {
    assert (state != null);
    assert (!state.equals(""));
    return new SetRepeatModeOnUsersPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .state(state);
  }

  public SetVolumeForUsersPlaybackRequest.Builder setVolumeForUsersPlayback(int volume_percent) {
    assert (0 <= volume_percent && volume_percent <= 100);
    return new SetVolumeForUsersPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .volume_percent(volume_percent);
  }

  public SkipUsersPlaybackToNextTrackRequest.Builder skipUsersPlaybackToNextTrack() {
    return new SkipUsersPlaybackToNextTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public SkipUsersPlaybackToPreviousTrackRequest.Builder skipUsersPlaybackToPreviousTrack() {
    return new SkipUsersPlaybackToPreviousTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public StartResumeUsersPlaybackRequest.Builder startResumeUsersPlayback() {
    return new StartResumeUsersPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  public ToggleShuffleForUsersPlaybackRequest.Builder toggleShuffleForUsersPlayback(boolean state) {
    return new ToggleShuffleForUsersPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .state(state);
  }

  public TransferUsersPlaybackRequest.Builder transferUsersPlayback(JsonArray device_ids) {
    assert (device_ids != null);
    assert (device_ids.size() == 1);
    return new TransferUsersPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .device_ids(device_ids);
  }

  /**
   * Add tracks to a playlist.
   *
   * @param user_id     The owner's username.
   * @param playlist_id The playlist's ID.
   * @param uris        URIs of the tracks to add.
   * @return A builder object that can be used to build a request to add tracks to a playlist.
   */
  public AddTracksToPlaylistRequest.Builder addTracksToPlaylist(String user_id, String playlist_id, String[] uris) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    assert (uris != null);
    assert (1 <= uris.length && uris.length <= 100);
    return new AddTracksToPlaylistRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .playlist_id(playlist_id)
            .uris(concat(uris, ','));
  }

  public AddTracksToPlaylistRequest.Builder addTracksToPlaylist(String user_id, String playlist_id, JsonArray uris) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    assert (uris != null);
    assert (uris.size() <= 100);
    return new AddTracksToPlaylistRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .playlist_id(playlist_id)
            .uris(uris);
  }

  /**
   * Update a playlist's properties.
   *
   * @param user_id     The owner's username.
   * @param playlist_id The playlist's ID.
   * @return A builder object that can be used to build a request to change a playlist's details.
   */
  public ChangePlaylistsDetailsRequest.Builder changePlaylistsDetails(String user_id, String playlist_id) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    return new ChangePlaylistsDetailsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .playlist_id(playlist_id);
  }

  /**
   * Create a playlist.
   *
   * @param user_id The playlist's owner.
   * @param name    The name of the playlist.
   * @return A builder object that can be used to build a request to create a playlist.
   */
  public CreatePlaylistRequest.Builder createPlaylist(String user_id, String name) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (name != null);
    assert (!name.equals(""));
    return new CreatePlaylistRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .name(name);
  }

  public GetListOfCurrentUsersPlaylistsRequest.Builder getListOfCurrentUsersPlaylists() {
    return new GetListOfCurrentUsersPlaylistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get a user's playlists.
   *
   * @param user_id A base62 id of the user.
   * @return A builder that builds a request to retrieve the playlists of an user.
   */
  public GetListOfUsersPlaylistsRequest.Builder getListOfUsersPlaylists(String user_id) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    return new GetListOfUsersPlaylistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id);
  }

  /**
   * Get a playlist.
   *
   * @param user_id     The playlist's owner's username.
   * @param playlist_id The playlist's ID.
   * @return A builder object that can be used to build a request to retrieve a playlist.
   */
  public GetPlaylistRequest.Builder getPlaylist(String user_id, String playlist_id) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    return new GetPlaylistRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .playlist_id(playlist_id);
  }

  public GetPlaylistCoverImageRequest.Builder getPlaylistCoverImage(String user_id, String playlist_id) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    return new GetPlaylistCoverImageRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .playlist_id(playlist_id);
  }

  /**
   * Get a playlist's tracks.
   *
   * @param user_id     The playlist's owner's username.
   * @param playlist_id The playlist's id.
   * @return A builder object that can be used to build a request to retrieve playlist tracks.
   */
  public GetPlaylistsTracksRequest.Builder getPlaylistsTracks(String user_id, String playlist_id) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    return new GetPlaylistsTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .playlist_id(playlist_id);
  }

  /**
   * delete tracks from a playlist
   *
   * @param user_id     The owner's username.
   * @param playlist_id The playlist's ID.
   * @param tracks      URIs of the tracks to remove.
   * @return A builder object that can be used to build a request to remove tracks from a playlist.
   */
  public RemoveTracksFromPlaylistRequest.Builder removeTracksFromPlaylist(
          String user_id, String playlist_id, JsonArray tracks) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    assert (tracks != null);
    assert (tracks.size() <= 100);
    return new RemoveTracksFromPlaylistRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .playlist_id(playlist_id)
            .tracks(tracks);
  }

  public ReorderPlaylistsTracksRequest.Builder reorderPlaylistsTracks(String user_id, String playlist_id, int range_start, int insert_before) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    return new ReorderPlaylistsTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .playlist_id(playlist_id)
            .range_start(range_start)
            .insert_before(insert_before);
  }

  /**
   * Replace tracks in a playlist.
   *
   * @param user_id     The owner's username.
   * @param playlist_id The playlist's ID.
   * @param uris        URIs of the tracks to add.
   * @return A builder object that can e used to build a request to add tracks to a playlist.
   */
  public ReplacePlaylistsTracksRequest.Builder replacePlaylistsTracks(String user_id, String playlist_id, String[] uris) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    assert (uris != null);
    assert (1 <= uris.length && uris.length <= 100);
    return new ReplacePlaylistsTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .playlist_id(playlist_id)
            .uris(concat(uris, ','));
  }

  public ReplacePlaylistsTracksRequest.Builder replacePlaylistsTracks(String user_id, String playlist_id, JsonArray uris) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    assert (uris != null);
    assert (uris.size() <= 100);
    return new ReplacePlaylistsTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .playlist_id(playlist_id)
            .uris(uris);
  }

  public UploadCustomPlaylistCoverImageRequest.Builder uploadCustomPlaylistCoverImage(String user_id, String playlist_id) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    assert (playlist_id != null);
    assert (!playlist_id.equals(""));
    return new UploadCustomPlaylistCoverImageRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id)
            .playlist_id(playlist_id);
  }

  public SearchItemRequest.Builder searchItem(String q, String type) {
    assert (q != null);
    assert (!q.equals(""));
    assert (type != null);
    assert (!type.equals(""));
    return new SearchItemRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .q(q)
            .type(type);
  }

  /**
   * Search for albums.
   *
   * @param q A search query string.
   * @return A builder that builds a request to search for an album.
   */
  public SearchAlbumsRequest.Builder searchAlbums(String q) {
    assert (q != null);
    assert (!q.equals(""));
    return new SearchAlbumsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .q(q);
  }

  /**
   * Search for artists.
   *
   * @param q A search query string.
   * @return A builder that builds a request to search for an artist.
   */
  public SearchArtistsRequest.Builder searchArtists(String q) {
    assert (q != null);
    assert (!q.equals(""));
    return new SearchArtistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .q(q);
  }

  public SearchPlaylistsRequest.Builder searchPlaylists(String q) {
    assert (q != null);
    assert (!q.equals(""));
    return new SearchPlaylistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .q(q);
  }

  /**
   * Search for tracks.
   *
   * @param q A search query string.
   * @return A builder that builds a request to search for a track.
   */
  public SearchTracksRequest.Builder searchTracks(String q) {
    assert (q != null);
    assert (!q.equals(""));
    return new SearchTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .q(q);
  }

  public GetAudioAnalysisForTrackRequest.Builder getAudioAnalysisForTrack(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetAudioAnalysisForTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  /**
   * Get audio features from a track.<br>
   * Examples: Tempo, danceability, accousticness...
   *
   * @param id The base62 id of the track.
   * @return A builder that builds a request to retrieve the audio features from a track.
   */
  public GetAudioFeaturesForTrackRequest.Builder getAudioFeaturesForTrack(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetAudioFeaturesForTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  public GetAudioFeaturesForSeveralTracksRequest.Builder getAudioFeaturesForSeveralTracks(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 100);
    return new GetAudioFeaturesForSeveralTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  /**
   * Get multiple tracks.
   *
   * @param ids The base62 ids of all tracks you're trying to retrieve.
   * @return A builder that builds a request to retrieve multiple tracks.
   */
  public GetSeveralTracksRequest.Builder getSeveralTracks(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new GetSeveralTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  /**
   * Get a track.
   *
   * @param id The base62 id of the track.
   * @return A builder that builds a request to retrieve a track.
   */
  public GetTrackRequest.Builder getTrack(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  /**
   * Get information about the user that has given authorization to the application.
   *
   * @return A builder object that can be used to build a request to retrieve information
   * about the current user.
   */
  public GetCurrentUsersProfileRequest.Builder getCurrentUsersProfile() {
    return new GetCurrentUsersProfileRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get information of an user.<br>
   * Examples: Birthdate, country, followers...
   *
   * @param user_id A base62 id of the user.
   * @return A builder that builds a request to retrieve an user.
   */
  public GetUsersProfileRequest.Builder getUsersProfile(String user_id) {
    assert (user_id != null);
    assert (!user_id.equals(""));
    return new GetUsersProfileRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .user_id(user_id);
  }

  /**
   * Builder class for building a Spotify API instance.
   */
  public static class Builder {

    private IHttpManager httpManager = DEFAULT_HTTP_MANAGER;
    private String scheme = DEFAULT_SCHEME;
    private String host = DEFAULT_HOST;
    private Integer port = DEFAULT_PORT;
    private String proxyUrl;
    private Integer proxyPort;
    private Integer proxyUsername;
    private Integer proxyPassword;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String accessToken;
    private String refreshToken;

    /**
     * Set the HttpManager in a builder object.
     *
     * @param httpManager A Spotify HttpManager.
     * @return A builder object.
     */
    public Builder setHttpManager(IHttpManager httpManager) {
      this.httpManager = httpManager;
      return this;
    }

    /**
     * Set the scheme in a builder object.
     *
     * @param scheme A HTTP-scheme.
     * @return A builder object.
     */
    public Builder setScheme(String scheme) {
      this.scheme = scheme;
      return this;
    }

    /**
     * Set the Spotify API host in a builder object.
     *
     * @param host A Spotify API host.
     * @return A builder object.
     */
    public Builder setHost(String host) {
      this.host = host;
      return this;
    }

    /**
     * Set the port in a builder object.
     *
     * @param port A Spotify API port.
     * @return A builder object.
     */
    public Builder setPort(Integer port) {
      this.port = port;
      return this;
    }

    public Builder setProxyUrl(String proxyUrl) {
      this.proxyUrl = proxyUrl;
      return this;
    }

    public Builder setProxyPort(Integer proxyPort) {
      this.proxyPort = proxyPort;
      return this;
    }

    public Builder setProxyUsername(Integer proxyUsername) {
      this.proxyUsername = proxyUsername;
      return this;
    }

    public Builder setProxyPassword(Integer proxyPassword) {
      this.proxyPassword = proxyPassword;
      return this;
    }

    /**
     * Set the client id in a builder object.
     *
     * @param clientId A client id of your application.
     * @return A builder object.
     */
    public Builder setClientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    /**
     * Set the client secret in a builder object.
     *
     * @param clientSecret A client secret of your application.
     * @return A builder object.
     */
    public Builder setClientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
      return this;
    }

    /**
     * Set the redirect uri in a builder object.
     *
     * @param redirectUri A redirect URI of your application.
     * @return A builder object.
     */
    public Builder setRedirectUri(String redirectUri) {
      this.redirectUri = redirectUri;
      return this;
    }

    /**
     * Set the acces token in a builder object.
     *
     * @param accessToken A Spotify API access token.
     * @return A builder object.
     */
    public Builder setAccessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    /**
     * Set the refresh token in a builder object.
     *
     * @param refreshToken A Spotify API refresh token.
     * @return A builder object.
     */
    public Builder setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    /**
     * Build a Spotify API instance with the information given to the builder.
     *
     * @return A Spotify API instance.
     */
    public SpotifyApi build() {
      return new SpotifyApi(this);
    }
  }
}

