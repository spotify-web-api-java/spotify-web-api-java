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

import java.net.URI;
import java.net.URISyntaxException;
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
  private final URI redirectUri;
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

  /**
   * Get the {@link IHttpManager} used for API calls.
   *
   * @return An {@link IHttpManager}.
   */
  public IHttpManager getHttpManager() {
    return httpManager;
  }

  /**
   * Get the scheme used for API calls. Default: {@code https}
   *
   * @return A scheme.
   */
  public String getScheme() {
    return scheme;
  }

  /**
   * Get the API host used for API calls. Default: {@code "api.spotify.com"}
   *
   * @return The host address.
   */
  public String getHost() {
    return host;
  }

  /**
   * Get the port used for API calls. Default: {@code 443}
   *
   * @return A port.
   */
  public Integer getPort() {
    return port;
  }

  /**
   * Get the proxy URL used for API calls.
   *
   * @return The proxy URL.
   */
  public String getProxyUrl() {
    return proxyUrl;
  }

  /**
   * Get the proxy port used for API calls.
   *
   * @return The proxy port.
   */
  public Integer getProxyPort() {
    return proxyPort;
  }

  /**
   * Get the proxy username used for API calls.
   *
   * @return The proxy username.
   */
  public Integer getProxyUsername() {
    return proxyUsername;
  }

  /**
   * Get the proxy password used for API calls.
   *
   * @return The proxy password.
   */
  public Integer getProxyPassword() {
    return proxyPassword;
  }

  /**
   * Get the application client ID specified in this API object.
   *
   * @return Application client ID.
   */
  public String getClientId() {
    return clientId;
  }

  /**
   * Get the application client secret specified in this API object.
   *
   * @return Application client secret.
   */
  public String getClientSecret() {
    return clientSecret;
  }

  /**
   * Get the redirect URI of the application specified in this API object.
   *
   * @return Application redirect URI.
   */
  public URI getRedirectURI() {
    return redirectUri;
  }

  /**
   * Get the access token specified in the API object, which is used for API calls.
   *
   * @return A Spotify Web API access token.
   */
  public String getAccessToken() {
    return accessToken;
  }

  /**
   * Set the access token of the API object.
   *
   * @param accessToken A Spotify Web API access token.
   */
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  /**
   * Get the refresh token specified in the API object.
   *
   * @return A Spotify Web API refresh token.
   */
  public String getRefreshToken() {
    return refreshToken;
  }

  /**
   * Set the refresh token of the API object.
   *
   * @param refreshToken A Spotify Web API refresh token.
   */
  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  /**
   * Request a new access token by creating a new
   * {@link com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials} object.
   * Requires client ID, client secret, and refresh token to be set.
   *
   * @param client_id     Required. When you register your application, Spotify provides you a Client ID.
   * @param client_secret Required. When you register your application, Spotify provides you a Client Secret.
   * @param refresh_token Required. The refresh token returned from the authorization code exchange.
   * @return An {@link AuthorizationCodeRequest.Builder}.
   */
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

  /**
   * Request a new access token by creating a new
   * {@link com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials} object.
   *
   * @return An {@link AuthorizationCodeRequest.Builder}.
   */
  public AuthorizationCodeRefreshRequest.Builder authorizationCodeRefresh() {
    return new AuthorizationCodeRefreshRequest.Builder(clientId, clientSecret)
            .setDefaults(httpManager, scheme, host, port)
            .grant_type("refresh_token")
            .refresh_token(refreshToken);
  }

  /**
   * Returns a builder that can be used to build requests for authorization code grants.
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
  public AuthorizationCodeRequest.Builder authorizationCode(String client_id, String client_secret, String code, URI redirect_uri) {
    assert (client_id != null);
    assert (!client_id.equals(""));
    assert (client_secret != null);
    assert (!client_secret.equals(""));
    assert (code != null);
    assert (!code.equals(""));
    assert (redirect_uri != null);
    return new AuthorizationCodeRequest.Builder(clientId, clientSecret)
            .setDefaults(httpManager, scheme, host, port)
            .grant_type("authorization_code")
            .code(code)
            .redirect_uri(redirect_uri);
  }

  /**
   * Returns a builder that can be used to build requests for authorization code grants.
   * Requires authorization code to be set.
   *
   * @param code Required. The authorization code returned from the initial request to the Account /authorize endpoint.
   * @return A builder that builds authorization code grant requests.
   */
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
  public AuthorizationCodeUriRequest.Builder authorizationCodeUri(String client_id, URI redirect_uri) {
    return new AuthorizationCodeUriRequest.Builder()
            .setDefaults(httpManager, scheme, host, port)
            .client_id(client_id)
            .response_type("code")
            .redirect_uri(redirect_uri);
  }

  /**
   * Retrieve a URL where the user can give the application permissions.
   *
   * @return The URL where the user can give application permissions.
   */
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
   * Returns an album with the ID given below.
   *
   * @param id The base62 ID of the album you're trying to retrieve.
   * @return A {@link GetAlbumRequest.Builder}.
   */
  public GetAlbumRequest.Builder getAlbum(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetAlbumRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  /**
   * Returns the tracks of the album with the ID given below.
   *
   * @param id The base62 ID of the album you're trying to retrieve.
   * @return A {@link GetAlbumsTracksRequest.Builder}.
   */
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
   * @param ids The base62 IDs of all albums you're trying to retrieve.
   * @return A {@link GetSeveralAlbumsRequest.Builder}.
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
   * @param id The base62 ID of the artist.
   * @return A {@link GetArtistRequest.Builder}.
   */
  public GetArtistRequest.Builder getArtist(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetArtistRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  /**
   * Get the albums of a specific artist.
   *
   * @param id The base62 ID of the artist.
   * @return A {@link GetArtistsAlbumsRequest.Builder}.
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
   * @param id      A base62 ID of the artist.
   * @param country The <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code </a>
   *                of the specific country.
   * @return A {@link GetArtistsTopTracksRequest.Builder}.
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
   * @param id The artist's ID.
   * @return A {@link GetArtistsRelatedArtistsRequest.Builder}.
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
   * @param ids The base62 IDs of all artists you're trying to retrieve.
   * @return A {@link GetSeveralArtistsRequest.Builder}.
   */
  public GetSeveralArtistsRequest.Builder getSeveralArtists(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new GetSeveralArtistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  /**
   * Get a category.
   *
   * @param category_id The Spotify category ID for the category.
   * @return A {@link GetCategoryRequest.Builder}.
   */
  public GetCategoryRequest.Builder getCategory(String category_id) {
    assert (category_id != null);
    assert (!category_id.equals(""));
    return new GetCategoryRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .category_id(category_id);
  }

  /**
   * Get the playlists from a specific category.
   *
   * @param category_id The Spotify category ID for the category.
   * @return A {@link GetCategorysPlaylistsRequest.Builder}.
   */
  public GetCategorysPlaylistsRequest.Builder getCategorysPlaylists(String category_id) {
    assert (category_id != null);
    assert (!category_id.equals(""));
    return new GetCategorysPlaylistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .category_id(category_id);
  }

  /**
   * Get a list of categories.
   *
   * @return A {@link GetListOfCategoriesRequest.Builder}.
   */
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

  /**
   * Create a playlist-style listening experience based on seed artists, tracks and genres.
   *
   * @return A {@link GetRecommendationsRequest.Builder}.
   */
  public GetRecommendationsRequest.Builder getRecommendations() {
    return new GetRecommendationsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Retrieve a list of available genres seed parameter values for recommendations.
   *
   * @return Recommendation seeds genres.
   */
  public GetAvailableGenreSeedsRequest.Builder getAvailableGenreSeeds() {
    return new GetAvailableGenreSeedsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Check to see if the current user is following one or more artists or other Spotify users.
   *
   * @param type Required. The ID type: either artist or user.
   * @param ids  Required. A list of the artist or the user Spotify IDs to check.
   * @return A {@link CheckCurrentUserFollowsArtistsOrUsersRequest.Builder}.
   */
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

  /**
   * Check to see if one or more Spotify users are following a specified playlist.
   *
   * @param owner_id    The Spotify user ID of the person who owns the playlist.
   * @param playlist_id The Spotify ID of the playlist.
   * @param ids         Required. A list of Spotify User IDs; the ids of the users that you want to check to see if they
   *                    follow the playlist. Maximum: 5 ids.
   * @return A {@link CheckUsersFollowPlaylistRequest.Builder}.
   */
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

  /**
   * Add the current user as a follower of one or more artists or other Spotify users.
   *
   * @param type Required. The ID type: either artist or user.
   * @param ids  A list of the artist or the user Spotify IDs.
   * @return A {@link FollowArtistsOrUsersRequest.Builder}.
   */
  public FollowArtistsOrUsersRequest.Builder followArtistsOrUsers(ModelObjectType type, String[] ids) {
    assert (type != null);
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new FollowArtistsOrUsersRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .type(type)
            .ids(concat(ids, ','));
  }

  /**
   * Add the current user as a follower of one or more artists or other Spotify users.
   *
   * @param type Required. The ID type: either artist or user.
   * @param ids  A list of the artist or the user Spotify IDs.
   * @return A {@link FollowArtistsOrUsersRequest.Builder}.
   */
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

  /**
   * Add the current user as a follower of a playlist.
   *
   * @param owner_id 	The Spotify user ID of the person who owns the playlist.
   * @param playlist_id The Spotify ID of the playlist. Any playlist can be followed, regardless of its
   *                    public/private status, as long as you know its playlist ID.
   * @param public_     Optional, default true. If true the playlist will be included in user's public playlists, if
   *                    false it will remain private. To be able to follow playlists privately, the user must have
   *                    granted the playlist-modify-private scope.
   * @return A {@link FollowPlaylistRequest.Builder}.
   */
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

  /**
   * Get the current user’s followed artists.
   *
   * @param type Required. The ID type: currently only artist is supported.
   * @return A {@link GetUsersFollowedArtistsRequest.Builder}.
   */
  public GetUsersFollowedArtistsRequest.Builder getUsersFollowedArtists(ModelObjectType type) {
    assert (type != null);
    return new GetUsersFollowedArtistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .type(type);
  }

  /**
   * Remove the current user as a follower of one or more artists or other Spotify users.
   *
   * @param type Required. The ID type: either artist or user.
   * @param ids  A list of the artist or the user Spotify IDs.
   * @return A {@link UnfollowArtistsOrUsersRequest.Builder}.
   */
  public UnfollowArtistsOrUsersRequest.Builder unfollowArtistsOrUsers(ModelObjectType type, String[] ids) {
    assert (type != null);
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new UnfollowArtistsOrUsersRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .type(type)
            .ids(concat(ids, ','));
  }

  /**
   * Remove the current user as a follower of one or more artists or other Spotify users.
   *
   * @param type Required. The ID type: either artist or user.
   * @param ids  A list of the artist or the user Spotify IDs.
   * @return A {@link UnfollowArtistsOrUsersRequest.Builder}.
   */
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
   * @param ids The tracks IDs to check for in the user's Your Music library.
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
   * @param ids The tracks IDs to check for in the user's Your Music library.
   * @return A builder object that can be used to check if a user has saved a track.
   */
  public CheckUsersSavedTracksRequest.Builder checkUsersSavedTracks(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new CheckUsersSavedTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  /**
   * Get a list of the albums saved in the current Spotify user’s "Your Music" library.
   *
   * @return A {@link GetCurrentUsersSavedAlbumsRequest.Builder}.
   */
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

  /**
   * Remove one or more albums from the current user’s "Your Music" library.
   *
   * @param ids A list of the Spotify IDs.
   * @return A {@link RemoveAlbumsForCurrentUserRequest.Builder}.
   */
  public RemoveAlbumsForCurrentUserRequest.Builder removeAlbumsForCurrentUser(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new RemoveAlbumsForCurrentUserRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  /**
   * Remove one or more albums from the current user's "Your Music" library.
   *
   * @param ids A list of the Spotify IDs.
   * @return A {@link RemoveAlbumsForCurrentUserRequest.Builder}.
   */
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
   * @param ids The track IDs to remove from the user's Your Music library.
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
   * @param ids The track IDs to remove from the user's Your Music library.
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
   * @param ids The track IDs to add to the user's library.
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
   * @param ids The track IDs to add to the user's library.
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
   * @param ids The track IDs to add to the user's library.
   * @return A builder object that can be used to add tracks to the user's library.
   */
  public SaveTracksForUserRequest.Builder saveTracksForUser(String... ids) {
    assert (ids != null);
    assert (1 <= ids.length && ids.length <= 50);
    return new SaveTracksForUserRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(ids, ','));
  }

  /**
   * Save tracks in the user's Your Music library.
   *
   * @param ids The track IDs to add to the user's library.
   * @return A builder object that can be used to add tracks to the user's library.
   */
  public SaveTracksForUserRequest.Builder saveTracksForUser(JsonArray ids) {
    assert (ids != null);
    assert (ids.size() <= 50);
    return new SaveTracksForUserRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .ids(concat(new Gson().fromJson(ids, String[].class), ','));
  }

  /**
   * Get the current user’s top artists or tracks based on calculated affinity. <br><br>
   *
   * Affinity is a measure of the expected preference a user has for a particular track or artist.  It is based on user
   * behavior, including play history, but does not include actions made while in incognito mode. Light or infrequent
   * users of Spotify may not have sufficient play history to generate a full affinity data set.
   *
   * @param type The type of entity to return. Valid values: artists or tracks.
   * @param <T>  Either {@link com.wrapper.spotify.model_objects.specification.Artist} or
   *             {@link com.wrapper.spotify.model_objects.specification.Track}
   * @return A {@link GetUsersTopArtistsAndTracksRequest.Builder}.
   */
  @SuppressWarnings("unchecked")
  public <T extends IArtistTrackModelObject> GetUsersTopArtistsAndTracksRequest.Builder<T> getUsersTopArtistsAndTracks(ModelObjectType type) {
    assert (type != null);
    return new GetUsersTopArtistsAndTracksRequest.Builder<T>(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .type(type);
  }

  /**
   * Get the current user’s top artists based on calculated affinity.
   *
   * @return A {@link GetUsersTopArtistsRequest.Builder}.
   * @see #getUsersTopArtistsAndTracks(ModelObjectType)
   */
  public GetUsersTopArtistsRequest.Builder getUsersTopArtists() {
    return new GetUsersTopArtistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get the current user’s top tracks based on calculated affinity.
   *
   * @return A {@link GetUsersTopTracksRequest.Builder}.
   * @see #getUsersTopArtistsAndTracks(ModelObjectType)
   */
  public GetUsersTopTracksRequest.Builder getUsersTopTracks() {
    return new GetUsersTopTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get information about the user’s current playback state, including track, track progress, and active device.
   *
   * @return A {@link GetInformationAboutUsersCurrentPlaybackRequest.Builder}.
   */
  public GetInformationAboutUsersCurrentPlaybackRequest.Builder getInformationAboutUsersCurrentPlayback() {
    return new GetInformationAboutUsersCurrentPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get tracks from the current user's recently played tracks. <br><br>
   *
   * Returns the most recent 50 tracks played by a user. Note that a track currently playing will not be visible in play
   * history until it has completed. A track must be played for more than 30 seconds to be included in play history.
   *
   * Any tracks listened to while the user had "Private Session" enabled in their client will not be returned in the
   * list of recently played tracks.
   *
   * @return A {@link GetCurrentUsersRecentlyPlayedTracksRequest.Builder}.
   */
  public GetCurrentUsersRecentlyPlayedTracksRequest.Builder getCurrentUsersRecentlyPlayedTracks() {
    return new GetCurrentUsersRecentlyPlayedTracksRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get information about a user's available devices.
   *
   * @return A {@link GetUsersAvailableDevices.Builder}.
   */
  public GetUsersAvailableDevicesRequest.Builder getUsersAvailableDevices() {
    return new GetUsersAvailableDevicesRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get the object currently being played on the user’s Spotify account.
   *
   * @return A {@link GetUsersCurrentlyPlayingTrackRequest.Builder}.
   */
  public GetUsersCurrentlyPlayingTrackRequest.Builder getUsersCurrentlyPlayingTrack() {
    return new GetUsersCurrentlyPlayingTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Pause playback on the user’s account.
   *
   * @return A {@link PauseUsersPlaybackRequest.Builder}.
   */
  public PauseUsersPlaybackRequest.Builder pauseUsersPlayback() {
    return new PauseUsersPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Seeks to the given position in the user’s currently playing track.
   *
   * @param position_ms Required. The position in milliseconds to seek to. Must be a positive number. Passing in a
   *                    position that is greater than the length of the track will cause the player to start playing the
   *                    next song.
   * @return A {@link SeekToPositionInCurrentlyPlayingTrackRequest.Builder}.
   */
  public SeekToPositionInCurrentlyPlayingTrackRequest.Builder seekToPositionInCurrentlyPlayingTrack(int position_ms) {
    assert (position_ms >= 0);
    return new SeekToPositionInCurrentlyPlayingTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .position_ms(position_ms);
  }

  /**
   * Set the repeat mode for the user’s playback. Options are repeat-track, repeat-context, and off.
   *
   * @param state Required. track, context or off. track will repeat the current track. context will repeat the current
   *              context. off will turn repeat off.
   * @return A {@link SetRepeatModeOnUsersPlaybackRequest.Builder}.
   */
  public SetRepeatModeOnUsersPlaybackRequest.Builder setRepeatModeOnUsersPlayback(String state) {
    assert (state != null);
    assert (!state.equals(""));
    return new SetRepeatModeOnUsersPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .state(state);
  }

  /**
   * Set the volume for the user’s current playback device.
   *
   * @param volume_percent Required. Integer. The volume to set. Must be a value from 0 to 100 inclusive.
   * @return A {@link SetVolumeForUsersPlaybackRequest.Builder}.
   */
  public SetVolumeForUsersPlaybackRequest.Builder setVolumeForUsersPlayback(int volume_percent) {
    assert (0 <= volume_percent && volume_percent <= 100);
    return new SetVolumeForUsersPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .volume_percent(volume_percent);
  }

  /**
   * Skips to next track in the user’s queue.
   *
   * @return A {@link SkipUsersPlaybackToNextTrackRequest.Builder}.
   */
  public SkipUsersPlaybackToNextTrackRequest.Builder skipUsersPlaybackToNextTrack() {
    return new SkipUsersPlaybackToNextTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Skips to previous track in the user’s queue.
   *
   * <b>Note:</b> This will ALWAYS skip to the previous track, regardless of the current track’s progress. Returning to
   * the start of the current track should be performed using the {@link #seekToPositionInCurrentlyPlayingTrack(int)}
   * method.
   *
   * @return A {@link SkipUsersPlaybackToPreviousTrackRequest.Builder}.
   */
  public SkipUsersPlaybackToPreviousTrackRequest.Builder skipUsersPlaybackToPreviousTrack() {
    return new SkipUsersPlaybackToPreviousTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Start a new context or resume current playback on the user’s active device.
   *
   * @return A {@link StartResumeUsersPlaybackRequest.Builder}.
   */
  public StartResumeUsersPlaybackRequest.Builder startResumeUsersPlayback() {
    return new StartResumeUsersPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Toggle shuffle on or off for user’s playback.
   *
   * @param state Required. true: Shuffle user's playback. false: Do not shuffle user's playback.
   * @return A {@link ToggleShuffleForUsersPlaybackRequest.Builder}.
   */
  public ToggleShuffleForUsersPlaybackRequest.Builder toggleShuffleForUsersPlayback(boolean state) {
    return new ToggleShuffleForUsersPlaybackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .state(state);
  }

  /**
   * Transfer playback to a new device and determine if it should start playing.
   *
   * @param device_ids Required. A JSON array containing the ID of the device on which playback should be
   *                   started/transferred. Note: Although an array is accepted, only a single device_id is currently
   *                   supported.
   * @return A {@link TransferUsersPlaybackRequest.Builder}.
   */
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

  /**
   * Add tracks to a playlist.
   *
   * @param user_id     The owner's username.
   * @param playlist_id The playlist's ID.
   * @param uris        URIs of the tracks to add.
   * @return A builder object that can be used to build a request to add tracks to a playlist.
   */
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

  /**
   * Get a list of the playlists owned or followed by the current Spotify user.
   *
   * @return A {@link GetListOfCurrentUsersPlaylistsRequest.Builder}.
   */
  public GetListOfCurrentUsersPlaylistsRequest.Builder getListOfCurrentUsersPlaylists() {
    return new GetListOfCurrentUsersPlaylistsRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get a user's playlists.
   *
   * @param user_id A base62 ID of the user.
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

  /**
   * Get the image used to represent a specific playlist.
   *
   * @param user_id     The user's Spotify user ID.
   * @param playlist_id The Spotify ID for the playlist.
   * @return A {@link GetPlaylistCoverImageRequest.Builder}.
   */
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
   * @param playlist_id The playlist's ID.
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
   * Delete tracks from a playlist
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

  /**
   * Reorder a track or a group of tracks in a playlist. <br><br>
   *
   * When reordering tracks, the timestamp indicating when they were added and the user who added them will be kept
   * untouched. In addition, the users following the playlists won’t be notified about changes in the playlists when the
   * tracks are reordered.
   *
   * @param user_id       The user's Spotify user ID.
   * @param playlist_id   The Spotify ID for the playlist.
   * @param range_start   Required. The position of the first track to be reordered.
   * @param insert_before Required. The position where the tracks should be inserted. To reorder the tracks to the end
   *                      of the playlist, simply set insert_before to the position after the last track.
   * @return A {@link ReorderPlaylistsTracksRequest.Builder}.
   */
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

  /**
   * Replace tracks in a playlist.
   *
   * @param user_id     The owner's username.
   * @param playlist_id The playlist's ID.
   * @param uris        URIs of the tracks to add.
   * @return A builder object that can e used to build a request to add tracks to a playlist.
   */
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

  /**
   * Replace the image used to represent a specific playlist.
   *
   * @param user_id     The user's Spotify user ID.
   * @param playlist_id The Spotify ID for the playlist.
   * @return An {@link UploadCustomPlaylistCoverImageRequest.Builder}.
   */
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

  /**
   * Get Spotify catalog information about artists, albums, tracks or playlists that match a keyword string.
   *
   * @param q    Required. The search query's keywords (and optional field filters and operators).
   * @param type Required. A comma-separated list of item types to search across. Valid types are: album, artist,
   *             playlist, and track.
   * @return A {@link SearchItemRequest.Builder}.
   */
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

  /**
   * Get Spotify catalog information about playlists that match a keyword string.
   *
   * @param q Required. The search query's keywords (and optional field filters and operators).
   * @return A {@link SearchPlaylistsRequest.Builder}.
   */
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

  /**
   * Get a detailed audio analysis for a single track identified by its unique Spotify ID.
   *
   * @param id Required. The Spotify ID for the track.
   * @return A {@link GetAudioAnalysisForTrackRequest.Builder}.
   */
  public GetAudioAnalysisForTrackRequest.Builder getAudioAnalysisForTrack(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetAudioAnalysisForTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  /**
   * Get audio features from a track. <br>
   * Examples: Tempo, danceability, accousticness...
   *
   * @param id The base62 ID of the track.
   * @return A builder that builds a request to retrieve the audio features from a track.
   */
  public GetAudioFeaturesForTrackRequest.Builder getAudioFeaturesForTrack(String id) {
    assert (id != null);
    assert (!id.equals(""));
    return new GetAudioFeaturesForTrackRequest.Builder(accessToken)
            .setDefaults(httpManager, scheme, host, port)
            .id(id);
  }

  /**
   * Get audio features for multiple tracks based on their Spotify IDs.
   *
   * @param ids Required. A comma-separated list of the Spotify IDs for the tracks. Maximum: 100 IDs.
   * @return A {@link GetAudioFeaturesForSeveralTracksRequest.Builder}.
   */
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
   * @param ids The base62 IDs of all tracks you're trying to retrieve.
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
    private URI redirectUri;
    private String accessToken;
    private String refreshToken;

    /**
     * Set the HttpManager in a builder object.
     *
     * @param httpManager A Spotify HttpManager.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setHttpManager(IHttpManager httpManager) {
      this.httpManager = httpManager;
      return this;
    }

    /**
     * Set the scheme in a builder object.
     *
     * @param scheme A HTTP-scheme.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setScheme(String scheme) {
      this.scheme = scheme;
      return this;
    }

    /**
     * Set the Spotify API host in a builder object.
     *
     * @param host A Spotify API host.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setHost(String host) {
      this.host = host;
      return this;
    }

    /**
     * Set the port in a builder object.
     *
     * @param port A Spotify API port.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setPort(Integer port) {
      this.port = port;
      return this;
    }

    /**
     * The proxy URL setter.
     *
     * @param proxyUrl A proxy URL.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setProxyUrl(String proxyUrl) {
      this.proxyUrl = proxyUrl;
      return this;
    }

    /**
     * The proxy port setter.
     *
     * @param proxyPort A proxy port.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setProxyPort(Integer proxyPort) {
      this.proxyPort = proxyPort;
      return this;
    }

    /**
     * The proxy username setter.
     *
     * @param proxyUsername A proxy username.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setProxyUsername(Integer proxyUsername) {
      this.proxyUsername = proxyUsername;
      return this;
    }

    /**
     * The proxy password setter.
     *
     * @param proxyPassword A proxy password.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setProxyPassword(Integer proxyPassword) {
      this.proxyPassword = proxyPassword;
      return this;
    }

    /**
     * Set the client ID in a builder object.
     *
     * @param clientId A client id of your application.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setClientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    /**
     * Set the client secret in a builder object.
     *
     * @param clientSecret A client secret of your application.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setClientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
      return this;
    }

    /**
     * Set the redirect uri in a builder object.
     *
     * @param redirectUri A redirect URI of your application.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setRedirectUri(URI redirectUri) {
      this.redirectUri = redirectUri;
      return this;
    }

    /**
     * Set the redirect uri in a builder object.
     *
     * @param redirectUri A redirect URI of your application.
     * @return A builder object.
     */
    public Builder setRedirectUri(String redirectUri) {
      try {
        return setRedirectUri(new URI(redirectUri));
      } catch (URISyntaxException e) {
        e.printStackTrace();
        return null;
      }
    }

    /**
     * Set the acces token in a builder object.
     *
     * @param accessToken A Spotify API access token.
     * @return A {@link SpotifyApi.Builder}.
     */
    public Builder setAccessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    /**
     * Set the refresh token in a builder object.
     *
     * @param refreshToken A Spotify API refresh token.
     * @return A {@link SpotifyApi.Builder}.
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

