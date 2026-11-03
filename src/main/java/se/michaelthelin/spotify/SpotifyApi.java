package se.michaelthelin.spotify;

import com.google.gson.JsonArray;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.pkce.AuthorizationCodePKCERefreshRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.pkce.AuthorizationCodePKCERequest;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.artists.*;
import se.michaelthelin.spotify.requests.data.categories.*;
import se.michaelthelin.spotify.requests.data.users.interfaces.IArtistTrackModelObject;
import se.michaelthelin.spotify.requests.data.player.*;
import se.michaelthelin.spotify.requests.data.playlists.*;
import se.michaelthelin.spotify.requests.data.search.simplified.*;
import se.michaelthelin.spotify.requests.data.tracks.*;
import se.michaelthelin.spotify.requests.data.users.GetUsersTopArtistsAndTracksRequest;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
// <generated-imports>
import java.util.List;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.requests.data.albums.CheckUsersSavedAlbumsRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumTracksRequest;
import se.michaelthelin.spotify.requests.data.albums.GetNewReleasesRequest;
import se.michaelthelin.spotify.requests.data.albums.GetSeveralAlbumsRequest;
import se.michaelthelin.spotify.requests.data.albums.GetUsersSavedAlbumsRequest;
import se.michaelthelin.spotify.requests.data.albums.RemoveAlbumsForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.albums.SaveAlbumsForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsRelatedArtistsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import se.michaelthelin.spotify.requests.data.artists.GetSeveralArtistsRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.CheckUsersSavedAudiobooksRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.GetAudiobookChaptersRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.GetAudiobookRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.GetSeveralAudiobooksRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.GetUsersSavedAudiobooksRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.RemoveAudiobooksForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.SaveAudiobooksForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.categories.GetSeveralBrowseCategoriesRequest;
import se.michaelthelin.spotify.requests.data.categories.GetSingleBrowseCategoryRequest;
import se.michaelthelin.spotify.requests.data.chapters.GetChapterRequest;
import se.michaelthelin.spotify.requests.data.chapters.GetSeveralChaptersRequest;
import se.michaelthelin.spotify.requests.data.episodes.CheckUsersSavedEpisodesRequest;
import se.michaelthelin.spotify.requests.data.episodes.GetEpisodeRequest;
import se.michaelthelin.spotify.requests.data.episodes.GetSeveralEpisodesRequest;
import se.michaelthelin.spotify.requests.data.episodes.GetUsersSavedEpisodesRequest;
import se.michaelthelin.spotify.requests.data.episodes.RemoveEpisodesForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.episodes.SaveEpisodesForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.genres.GetRecommendationGenresRequest;
import se.michaelthelin.spotify.requests.data.library.CheckUsersSavedItemsRequest;
import se.michaelthelin.spotify.requests.data.library.RemoveItemsFromLibraryRequest;
import se.michaelthelin.spotify.requests.data.library.SaveItemsToLibraryRequest;
import se.michaelthelin.spotify.requests.data.markets.GetAvailableMarketsRequest;
import se.michaelthelin.spotify.requests.data.player.AddItemToPlaybackQueueRequest;
import se.michaelthelin.spotify.requests.data.player.GetAvailableDevicesRequest;
import se.michaelthelin.spotify.requests.data.player.GetCurrentlyPlayingTrackRequest;
import se.michaelthelin.spotify.requests.data.player.GetPlaybackStateRequest;
import se.michaelthelin.spotify.requests.data.player.GetRecentlyPlayedTracksRequest;
import se.michaelthelin.spotify.requests.data.player.GetUsersQueueRequest;
import se.michaelthelin.spotify.requests.data.player.PausePlaybackRequest;
import se.michaelthelin.spotify.requests.data.player.SeekToPositionRequest;
import se.michaelthelin.spotify.requests.data.player.SetPlaybackVolumeRequest;
import se.michaelthelin.spotify.requests.data.player.SetRepeatModeRequest;
import se.michaelthelin.spotify.requests.data.player.SkipToNextRequest;
import se.michaelthelin.spotify.requests.data.player.SkipToPreviousRequest;
import se.michaelthelin.spotify.requests.data.player.StartResumePlaybackRequest;
import se.michaelthelin.spotify.requests.data.player.TogglePlaybackShuffleRequest;
import se.michaelthelin.spotify.requests.data.player.TransferPlaybackRequest;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistDeprecatedRequest;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.ChangePlaylistDetailsRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistForUserRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetCategoryPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetCurrentUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetFeaturedPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistCoverImageRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistItemsDeprecatedRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistItemsRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.playlists.RemovePlaylistItemsDeprecatedRequest;
import se.michaelthelin.spotify.requests.data.playlists.RemovePlaylistItemsRequest;
import se.michaelthelin.spotify.requests.data.playlists.UpdatePlaylistsItemsReorderDeprecatedRequest;
import se.michaelthelin.spotify.requests.data.playlists.UpdatePlaylistsItemsReorderRequest;
import se.michaelthelin.spotify.requests.data.playlists.UpdatePlaylistsItemsReplaceDeprecatedRequest;
import se.michaelthelin.spotify.requests.data.playlists.UpdatePlaylistsItemsReplaceRequest;
import se.michaelthelin.spotify.requests.data.search.SearchForItemRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAudiobooksRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchEpisodesRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchShowsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.special.SearchAlbumsSpecialRequest;
import se.michaelthelin.spotify.requests.data.shows.CheckUsersSavedShowsRequest;
import se.michaelthelin.spotify.requests.data.shows.GetSeveralShowsRequest;
import se.michaelthelin.spotify.requests.data.shows.GetShowEpisodesRequest;
import se.michaelthelin.spotify.requests.data.shows.GetShowRequest;
import se.michaelthelin.spotify.requests.data.shows.GetUsersSavedShowsRequest;
import se.michaelthelin.spotify.requests.data.shows.RemoveShowsForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.shows.SaveShowsForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.tracks.CheckUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetSeveralTracksAudioFeaturesRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetSeveralTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTracksAudioAnalysisRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTracksAudioFeaturesRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.RemoveUsersSavedTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.SaveTracksForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.users.CheckCurrentUserFollowsArtistsOrUsersRequest;
import se.michaelthelin.spotify.requests.data.users.CheckIfUserFollowsPlaylistRequest;
import se.michaelthelin.spotify.requests.data.users.FollowArtistsOrUsersRequest;
import se.michaelthelin.spotify.requests.data.users.FollowPlaylistRequest;
import se.michaelthelin.spotify.requests.data.users.GetCurrentUsersProfileRequest;
import se.michaelthelin.spotify.requests.data.users.GetFollowedArtistsRequest;
import se.michaelthelin.spotify.requests.data.users.GetUsersProfileRequest;
import se.michaelthelin.spotify.requests.data.users.UnfollowArtistsOrUsersRequest;
import se.michaelthelin.spotify.requests.data.users.UnfollowPlaylistRequest;
import se.michaelthelin.spotify.requests.data.users.simplified.GetUsersTopArtistsRequest;
import se.michaelthelin.spotify.requests.data.users.simplified.GetUsersTopTracksRequest;
// </generated-imports>

/**
 * Instances of the SpotifyApi class provide access to the Spotify Web API.
 */
public class SpotifyApi {

  /**
   * The default authentication host of Spotify API calls.
   */
  public static final String DEFAULT_AUTHENTICATION_HOST = "accounts.spotify.com";

  /**
   * The default authentication port of Spotify API calls.
   */
  public static final int DEFAULT_AUTHENTICATION_PORT = 443;

  /**
   * The default authentication http scheme of Spotify API calls.
   */
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

  /** Logger instance for this class. */
  public static final Logger LOGGER = Logger.getLogger(SpotifyApi.class.getName());

  /**
   * The date format used by the Spotify Web API. It uses the {@code GMT}  timezone and the following pattern:
   * {@code yyyy-MM-dd'T'HH:mm:ss}
   */
  private static final DateTimeFormatter SIMPLE_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    .withZone(ZoneId.of("GMT"));

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
   * @return A {@link Builder}.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * String concatenation helper method.
   *
   * @param parts     String parts.
   * @param character Separation character.
   * @return A string.
   */
  public static String concat(String[] parts, char character) {
    StringBuilder stringBuilder = new StringBuilder();

    for (String part : parts) {
      stringBuilder.append(part).append(character);
    }

    stringBuilder.deleteCharAt(stringBuilder.length() - 1);

    return stringBuilder.toString();
  }

  /**
   * Parses a date in the default spotify format.
   *
   * @param date the input date to parse
   * @return the pared {@link Date}
   * @throws ParseException if the date is not in a valid format
   */
  public static Date parseDefaultDate(String date) throws ParseException {
    if (date == null) {
      throw new ParseException("Date string is null", 0);
    }

    String parsedDate = date.length() > 19 ? date.substring(0, 19) : date;

    try {
      return Date.from(LocalDateTime.parse(parsedDate, SIMPLE_DATE_FORMATTER)
          .atZone(SIMPLE_DATE_FORMATTER.getZone())
          .toInstant());
    } catch (DateTimeParseException e) {
      int errorIndex = e.getErrorIndex();
      if (errorIndex < 0) {
        errorIndex = 0;
      }
      throw new ParseException(e.getMessage(), errorIndex);
    }
  }

  /**
   * Formats a date, using the default spotify format.
   *
   * @param date the date to format
   * @return the formatted date
   */
  public static String formatDefaultDate(Date date) {
    return SIMPLE_DATE_FORMATTER.format(date.toInstant());
  }

  /**
   * Creates a SimpleDateFormat with the specified pattern and timezone.
   *
   * @param pattern the date pattern
   * @param id the timezone ID
   * @return a configured SimpleDateFormat instance
   * @deprecated {@link SimpleDateFormat} is mutable and not thread-safe, so instances returned by this
   * method must not be shared across threads or cached in a static field. Use a {@link DateTimeFormatter}
   * instead.
   */
  @Deprecated
  public static SimpleDateFormat makeSimpleDateFormat(String pattern, String id) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone(id));

    return simpleDateFormat;
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
   * Refresh the access token by using authorization code grant. <br>
   * Requires client ID, client secret, and refresh token to be set.
   *
   * @param client_id     When you register your application, Spotify provides you a Client ID.
   * @param client_secret When you register your application, Spotify provides you a Client Secret.
   * @param refresh_token The refresh token returned from the authorization code exchange.
   * @return An {@link AuthorizationCodeRequest.Builder}.
   */
  public AuthorizationCodeRefreshRequest.Builder authorizationCodeRefresh(String client_id, String client_secret, String refresh_token) {
    return new AuthorizationCodeRefreshRequest.Builder(client_id, client_secret)
      .setDefaults(httpManager, scheme, host, port)
      .grant_type("refresh_token")
      .refresh_token(refresh_token);
  }

  /**
   * Refresh the access token by using authorization code grant.
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
   * Refresh the access token by using the authorization code flow with Proof Key for Code Exchange (PKCE). <br>
   * Requires client ID and refresh token to be set.
   *
   * @param client_id     When you register your application, Spotify provides you a Client ID.
   * @param refresh_token The refresh token returned from the authorization code exchange or the last access token refresh.
   * @return An {@link AuthorizationCodePKCERefreshRequest.Builder}.
   */
  public AuthorizationCodePKCERefreshRequest.Builder authorizationCodePKCERefresh(String client_id, String refresh_token) {
    return new AuthorizationCodePKCERefreshRequest.Builder()
      .setDefaults(httpManager, scheme, host, port)
      .client_id(client_id)
      .grant_type("refresh_token")
      .refresh_token(refresh_token);
  }

  /**
   * Refresh the access token by using the authorization code flow with Proof Key for Code Exchange (PKCE).
   *
   * @return An {@link AuthorizationCodePKCERefreshRequest.Builder}.
   */
  public AuthorizationCodePKCERefreshRequest.Builder authorizationCodePKCERefresh() {
    return new AuthorizationCodePKCERefreshRequest.Builder()
      .setDefaults(httpManager, scheme, host, port)
      .client_id(clientId)
      .grant_type("refresh_token")
      .refresh_token(refreshToken);
  }

  /**
   * Returns a builder that can be used to build requests for authorization code grants. <br>
   * Requires client ID, client secret, authorization code and redirect URI to be set.
   *
   * @param client_id     When you register your application, Spotify provides you a Client ID.
   * @param client_secret When you register your application, Spotify provides you a Client Secret.
   * @param code          The authorization code returned from the initial request to the Account /authorize endpoint.
   * @param redirect_uri  This parameter is used for validation only (there is no actual redirection). The
   *                      value of this parameter must exactly match the value of redirect_uri supplied when requesting
   *                      the authorization code.
   * @return An {@link AuthorizationCodeRequest.Builder}.
   */
  public AuthorizationCodeRequest.Builder authorizationCode(String client_id, String client_secret, String code, URI redirect_uri) {
    return new AuthorizationCodeRequest.Builder(client_id, client_secret)
      .setDefaults(httpManager, scheme, host, port)
      .grant_type("authorization_code")
      .code(code)
      .redirect_uri(redirect_uri);
  }

  /**
   * Returns a builder that can be used to build requests for authorization code grants. <br>
   * Requires authorization code to be set.
   *
   * @param code The authorization code returned from the initial request to the Account /authorize endpoint.
   * @return An {@link AuthorizationCodeRequest.Builder}.
   */
  public AuthorizationCodeRequest.Builder authorizationCode(String code) {
    return new AuthorizationCodeRequest.Builder(clientId, clientSecret)
      .setDefaults(httpManager, scheme, host, port)
      .grant_type("authorization_code")
      .code(code)
      .redirect_uri(redirectUri);
  }
  /**
   * Returns a builder that can be used to build requests for authorization code grants using the Proof Key for Code Exchange (PKCE) flow. <br>
   * Requires client ID, authorization code, code verifier and redirect URI to be set.
   *
   * @param client_id     When you register your application, Spotify provides you a Client ID.
   * @param code          The authorization code returned from the initial request to the Account /authorize endpoint.
   * @param code_verifier The value of this parameter must match the value of the code_verifier that your app generated beforehand.
   * @param redirect_uri  This parameter is used for validation only (there is no actual redirection). The
   *                      value of this parameter must exactly match the value of redirect_uri supplied when requesting
   *                      the authorization code.
   * @return An {@link AuthorizationCodePKCERequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#authorization-code-flow-with-proof-key-for-code-exchange-pkce">
   *      Authorization Code Flow with Proof Key for Code Exchange (PKCE)</a>
   */
  public AuthorizationCodePKCERequest.Builder authorizationCodePKCE(String client_id, String code, String code_verifier, URI redirect_uri) {
    return new AuthorizationCodePKCERequest.Builder()
      .setDefaults(httpManager, scheme, host, port)
      .client_id(client_id)
      .code_verifier(code_verifier)
      .grant_type("authorization_code")
      .code(code)
      .redirect_uri(redirect_uri);
  }

  /**
   * Returns a builder that can be used to build requests for authorization code grants using the Proof Key for Code Exchange (PKCE) flow. <br>
   * Requires authorization code and code verifier to be set.
   *
   * @param code The authorization code returned from the initial request to the Account /authorize endpoint.
   * @param code_verifier The value of this parameter must match the value of the code_verifier that your app generated beforehand.
   * @return An {@link AuthorizationCodePKCERequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#authorization-code-flow-with-proof-key-for-code-exchange-pkce">
   *      Authorization Code Flow with Proof Key for Code Exchange (PKCE)</a>
   */
  public AuthorizationCodePKCERequest.Builder authorizationCodePKCE(String code, String code_verifier) {
    return new AuthorizationCodePKCERequest.Builder()
      .setDefaults(httpManager, scheme, host, port)
      .client_id(clientId)
      .code_verifier(code_verifier)
      .grant_type("authorization_code")
      .code(code)
      .redirect_uri(redirectUri);
  }

  /**
   * Retrieve an URL where the user can give the application permissions.
   *
   * @param client_id    When you register your application, Spotify provides you a Client ID.
   * @param redirect_uri This parameter is used for validation only (there is no actual redirection). The
   *                     value of this parameter must exactly match the value of redirect_uri supplied when requesting
   *                     the authorization code.
   * @return An {@link AuthorizationCodeUriRequest.Builder}.
   */
  public AuthorizationCodeUriRequest.Builder authorizationCodeUri(String client_id, URI redirect_uri) {
    return new AuthorizationCodeUriRequest.Builder()
      .setDefaults(httpManager, scheme, host, port)
      .client_id(client_id)
      .response_type("code")
      .redirect_uri(redirect_uri);
  }

  /**
   * Retrieve an URL where the user can give the application permissions.
   *
   * @return An {@link AuthorizationCodeUriRequest.Builder}.
   */
  public AuthorizationCodeUriRequest.Builder authorizationCodeUri() {
    return new AuthorizationCodeUriRequest.Builder()
      .setDefaults(httpManager, scheme, host, port)
      .client_id(clientId)
      .response_type("code")
      .redirect_uri(redirectUri);
  }

  /**
   * Retrieve an URL where the user can give the application permissions using the Proof Key for Code Exchange (PKCE) flow.
   *
   * @param client_id    When you register your application, Spotify provides you a Client ID.
   * @param code_challenge  The code challenge that your app calculated beforehand.
   *                        The code challenge is the base64url encoded sha256-hash of the code verifier,
   *                        which is a cryptographically random string between 43 and 128 characters in length.
   *                        It can contain letters, digits, underscores, periods, hyphens, or tildes and is generated
   *                        by your app before each authentication request.
   * @param redirect_uri This parameter is used for validation only (there is no actual redirection). The
   *                     value of this parameter must exactly match the value of redirect_uri supplied when requesting
   *                     the authorization code.
   * @return An {@link AuthorizationCodeUriRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#authorization-code-flow-with-proof-key-for-code-exchange-pkce">
   *      Authorization Code Flow with Proof Key for Code Exchange (PKCE)</a>
   */
  public AuthorizationCodeUriRequest.Builder authorizationCodePKCEUri(String client_id, String code_challenge, URI redirect_uri) {
    return new AuthorizationCodeUriRequest.Builder()
      .setDefaults(httpManager, scheme, host, port)
      .client_id(client_id)
      .response_type("code")
      .code_challenge_method("S256")
      .code_challenge(code_challenge)
      .redirect_uri(redirect_uri);
  }

  /**
   * Retrieve an URL where the user can give the application permissions using the Proof Key for Code Exchange (PKCE) flow.
   *
   * @param code_challenge  The code challenge that your app calculated beforehand.
   *                        The code challenge is the base64url encoded sha256-hash of the code verifier,
   *                        which is a cryptographically random string between 43 and 128 characters in length.
   *                        It can contain letters, digits, underscores, periods, hyphens, or tildes and is generated
   *
   * @return An {@link AuthorizationCodeUriRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#authorization-code-flow-with-proof-key-for-code-exchange-pkce">
   *      Authorization Code Flow with Proof Key for Code Exchange (PKCE)</a>
   */
  public AuthorizationCodeUriRequest.Builder authorizationCodePKCEUri(String code_challenge) {
    return new AuthorizationCodeUriRequest.Builder()
      .setDefaults(httpManager, scheme, host, port)
      .client_id(clientId)
      .response_type("code")
      .code_challenge_method("S256")
      .code_challenge(code_challenge)
      .redirect_uri(redirectUri);
  }

  /**
   * Returns a builder that can be used to build requests for client credential grants. <br>
   * Requires client ID and client secret to be set.
   *
   * @return A {@link ClientCredentialsRequest.Builder}.
   */
  public ClientCredentialsRequest.Builder clientCredentials() {
    return new ClientCredentialsRequest.Builder(clientId, clientSecret)
      .setDefaults(httpManager, scheme, host, port)
      .grant_type("client_credentials");
  }

  // <generated-requests>
  /**
   * Check if one or more albums is already saved in the current Spotify user's 'Your Music' library. <b>Note:</b> This
   * endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/check-library-contains">Check User's Saved
   * Items</a> instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the albums. Maximum: 20 IDs.
   * @return A {@link CheckUsersSavedAlbumsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/check-users-saved-albums">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public CheckUsersSavedAlbumsRequest.Builder checkUsersSavedAlbums(String ids) {
    return new CheckUsersSavedAlbumsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get Spotify catalog information for a single album.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> of the album.
   * @return A {@link GetAlbumRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-an-album">Spotify Web API reference</a>
   */
  public GetAlbumRequest.Builder getAlbum(String id) {
    return new GetAlbumRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information about an album’s tracks. Optional parameters can be used to limit the number of
   * tracks returned.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> of the album.
   * @return A {@link GetAlbumTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-an-albums-tracks">Spotify Web API reference</a>
   */
  public GetAlbumTracksRequest.Builder getAlbumTracks(String id) {
    return new GetAlbumTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player’s “Browse” tab).
   *
   * @return A {@link GetNewReleasesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-new-releases">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetNewReleasesRequest.Builder getNewReleases() {
    return new GetNewReleasesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get Spotify catalog information for multiple albums identified by their Spotify IDs.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the albums. Maximum: 20 IDs.
   * @return A {@link GetSeveralAlbumsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-multiple-albums">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetSeveralAlbumsRequest.Builder getSeveralAlbums(String ids) {
    return new GetSeveralAlbumsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get a list of the albums saved in the current Spotify user's 'Your Music' library.
   *
   * @return A {@link GetUsersSavedAlbumsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-users-saved-albums">Spotify Web API reference</a>
   */
  public GetUsersSavedAlbumsRequest.Builder getUsersSavedAlbums() {
    return new GetUsersSavedAlbumsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Remove one or more albums from the current user's 'Your Music' library. <b>Note:</b> This endpoint is deprecated.
   * Use <a href="https://developer.spotify.com/documentation/web-api/reference/remove-library-items">Remove Items from
   * Library</a> instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the albums. Maximum: 20 IDs.
   * @return A {@link RemoveAlbumsForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/remove-albums-user">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public RemoveAlbumsForCurrentUserRequest.Builder removeAlbumsForCurrentUser(String ids) {
    return new RemoveAlbumsForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Save one or more albums to the current user's 'Your Music' library. <b>Note:</b> This endpoint is deprecated. Use
   * <a href="https://developer.spotify.com/documentation/web-api/reference/save-library-items">Save Items to
   * Library</a> instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the albums. Maximum: 20 IDs.
   * @return A {@link SaveAlbumsForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/save-albums-user">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public SaveAlbumsForCurrentUserRequest.Builder saveAlbumsForCurrentUser(String ids) {
    return new SaveAlbumsForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get Spotify catalog information for a single artist identified by their unique Spotify ID.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> of the artist.
   * @return A {@link GetArtistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-an-artist">Spotify Web API reference</a>
   */
  public GetArtistRequest.Builder getArtist(String id) {
    return new GetArtistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information about an artist's albums.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> of the artist.
   * @return A {@link GetArtistsAlbumsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-an-artists-albums">Spotify Web API reference</a>
   */
  public GetArtistsAlbumsRequest.Builder getArtistsAlbums(String id) {
    return new GetArtistsAlbumsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information about artists similar to a given artist. Similarity is based on analysis of the
   * Spotify community's listening history.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> of the artist.
   * @return A {@link GetArtistsRelatedArtistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-an-artists-related-artists">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetArtistsRelatedArtistsRequest.Builder getArtistsRelatedArtists(String id) {
    return new GetArtistsRelatedArtistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information about an artist's top tracks by country.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> of the artist.
   * @return A {@link GetArtistsTopTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-an-artists-top-tracks">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetArtistsTopTracksRequest.Builder getArtistsTopTracks(String id) {
    return new GetArtistsTopTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for several artists based on their Spotify IDs.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the artists. Maximum: 50 IDs.
   * @return A {@link GetSeveralArtistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-multiple-artists">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetSeveralArtistsRequest.Builder getSeveralArtists(String ids) {
    return new GetSeveralArtistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Check if one or more audiobooks are already saved in the current Spotify user's library. <b>Note:</b> This endpoint
   * is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/check-library-contains">Check User's Saved
   * Items</a> instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>.
   *            For example: {@code ids=18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ}. Maximum: 50 IDs.
   * @return A {@link CheckUsersSavedAudiobooksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/check-users-saved-audiobooks">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public CheckUsersSavedAudiobooksRequest.Builder checkUsersSavedAudiobooks(String ids) {
    return new CheckUsersSavedAudiobooksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get Spotify catalog information about an audiobook's chapters. Audiobooks are only available within the US, UK,
   * Canada, Ireland, New Zealand and Australia markets.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> for the audiobook.
   * @return A {@link GetAudiobookChaptersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-audiobook-chapters">Spotify Web API reference</a>
   */
  public GetAudiobookChaptersRequest.Builder getAudiobookChapters(String id) {
    return new GetAudiobookChaptersRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for a single audiobook. Audiobooks are only available within the US, UK, Canada,
   * Ireland, New Zealand and Australia markets.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> for the audiobook.
   * @return A {@link GetAudiobookRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-an-audiobook">Spotify Web API reference</a>
   */
  public GetAudiobookRequest.Builder getAudiobook(String id) {
    return new GetAudiobookRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for several audiobooks identified by their Spotify IDs. Audiobooks are only
   * available within the US, UK, Canada, Ireland, New Zealand and Australia markets.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>.
   *            For example: {@code ids=18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ}. Maximum: 50 IDs.
   * @return A {@link GetSeveralAudiobooksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-multiple-audiobooks">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetSeveralAudiobooksRequest.Builder getSeveralAudiobooks(String ids) {
    return new GetSeveralAudiobooksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get a list of the audiobooks saved in the current Spotify user's 'Your Music' library.
   *
   * @return A {@link GetUsersSavedAudiobooksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-users-saved-audiobooks">Spotify Web API reference</a>
   */
  public GetUsersSavedAudiobooksRequest.Builder getUsersSavedAudiobooks() {
    return new GetUsersSavedAudiobooksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Remove one or more audiobooks from the Spotify user's library. <b>Note:</b> This endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/remove-library-items">Remove Items from
   * Library</a> instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>.
   *            For example: {@code ids=18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ}. Maximum: 50 IDs.
   * @return A {@link RemoveAudiobooksForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/remove-audiobooks-user">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public RemoveAudiobooksForCurrentUserRequest.Builder removeAudiobooksForCurrentUser(String ids) {
    return new RemoveAudiobooksForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Save one or more audiobooks to the current Spotify user's library. <b>Note:</b> This endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/save-library-items">Save Items to Library</a>
   * instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>.
   *            For example: {@code ids=18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ}. Maximum: 50 IDs.
   * @return A {@link SaveAudiobooksForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/save-audiobooks-user">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public SaveAudiobooksForCurrentUserRequest.Builder saveAudiobooksForCurrentUser(String ids) {
    return new SaveAudiobooksForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get a list of categories used to tag items in Spotify (on, for example, the Spotify player’s “Browse” tab).
   *
   * @return A {@link GetSeveralBrowseCategoriesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-categories">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetSeveralBrowseCategoriesRequest.Builder getSeveralBrowseCategories() {
    return new GetSeveralBrowseCategoriesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get a single category used to tag items in Spotify (on, for example, the Spotify player’s “Browse” tab).
   *
   * @param category_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    category ID</a> for the category.
   * @return A {@link GetSingleBrowseCategoryRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-a-category">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetSingleBrowseCategoryRequest.Builder getSingleBrowseCategory(String category_id) {
    return new GetSingleBrowseCategoryRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .category_id(category_id);
  }

  /**
   * Get Spotify catalog information for a single audiobook chapter. Chapters are only available within the US, UK,
   * Canada, Ireland, New Zealand and Australia markets.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> for the chapter.
   * @return A {@link GetChapterRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-a-chapter">Spotify Web API reference</a>
   */
  public GetChapterRequest.Builder getChapter(String id) {
    return new GetChapterRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for several audiobook chapters identified by their Spotify IDs. Chapters are only
   * available within the US, UK, Canada, Ireland, New Zealand and Australia markets.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>.
   *            For example: {@code ids=0IsXVP0JmcB2adSE338GkK,3ZXb8FKZGU0EHALYX6uCzU}. Maximum: 50 IDs.
   * @return A {@link GetSeveralChaptersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-several-chapters">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetSeveralChaptersRequest.Builder getSeveralChapters(String ids) {
    return new GetSeveralChaptersRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Check if one or more episodes is already saved in the current Spotify user's 'Your Episodes' library. <b>Note:</b>
   * This endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/check-library-contains">Check User's Saved
   * Items</a> instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the episodes. Maximum: 50 IDs.
   * @return A {@link CheckUsersSavedEpisodesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/check-users-saved-episodes">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public CheckUsersSavedEpisodesRequest.Builder checkUsersSavedEpisodes(String ids) {
    return new CheckUsersSavedEpisodesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get Spotify catalog information for a single episode identified by its unique Spotify ID.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> for the episode.
   * @return A {@link GetEpisodeRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-an-episode">Spotify Web API reference</a>
   */
  public GetEpisodeRequest.Builder getEpisode(String id) {
    return new GetEpisodeRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for several episodes based on their Spotify IDs.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the episodes. Maximum: 50 IDs.
   * @return A {@link GetSeveralEpisodesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-multiple-episodes">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetSeveralEpisodesRequest.Builder getSeveralEpisodes(String ids) {
    return new GetSeveralEpisodesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get a list of the episodes saved in the current Spotify user's library.
   *
   * @return A {@link GetUsersSavedEpisodesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-users-saved-episodes">Spotify Web API reference</a>
   */
  public GetUsersSavedEpisodesRequest.Builder getUsersSavedEpisodes() {
    return new GetUsersSavedEpisodesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Remove one or more episodes from the current user's library. <b>Note:</b> This endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/remove-library-items">Remove Items from
   * Library</a> instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>.
   *            For example: {@code ids=4iV5W9uYEdYUVa79Axb7Rh,1301WleyT98MSxVHPZCA6M}. Maximum: 50 IDs.
   * @return A {@link RemoveEpisodesForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/remove-episodes-user">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public RemoveEpisodesForCurrentUserRequest.Builder removeEpisodesForCurrentUser(String ids) {
    return new RemoveEpisodesForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Save one or more episodes to the current user's library. <b>Note:</b> This endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/save-library-items">Save Items to Library</a>
   * instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>.
   *            Maximum: 50 IDs.
   * @return A {@link SaveEpisodesForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/save-episodes-user">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public SaveEpisodesForCurrentUserRequest.Builder saveEpisodesForCurrentUser(String ids) {
    return new SaveEpisodesForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Retrieve a list of available genres seed parameter values for <a
   * href="https://developer.spotify.com/documentation/web-api/reference/get-recommendations">recommendations</a>.
   *
   * @return A {@link GetRecommendationGenresRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-recommendation-genres">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetRecommendationGenresRequest.Builder getRecommendationGenres() {
    return new GetRecommendationGenresRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Check if one or more items are already saved in the current user's library. Accepts Spotify URIs for tracks,
   * albums, episodes, shows, audiobooks, artists, users, and playlists.
   *
   * @param uris A comma-separated list of <a
   *             href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URIs</a>.
   *             Maximum: 40 URIs. Supported URI types: - {@code spotify:track:{id}} - {@code spotify:album:{id}} -
   *             {@code spotify:episode:{id}} - {@code spotify:show:{id}} - {@code spotify:audiobook:{id}} - {@code
   *             spotify:artist:{id}} - {@code spotify:user:{id}} - {@code spotify:playlist:{id}}
   * @return A {@link CheckUsersSavedItemsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/check-library-contains">Spotify Web API reference</a>
   */
  public CheckUsersSavedItemsRequest.Builder checkUsersSavedItems(String uris) {
    return new CheckUsersSavedItemsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .uris(uris);
  }

  /**
   * Remove one or more items from the current user's library. Accepts Spotify URIs for tracks, albums, episodes, shows,
   * audiobooks, users, and playlists.
   *
   * @param uris A comma-separated list of <a
   *             href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URIs</a>.
   *             Maximum: 40 URIs. Supported URI types: - {@code spotify:track:{id}} - {@code spotify:album:{id}} -
   *             {@code spotify:episode:{id}} - {@code spotify:show:{id}} - {@code spotify:audiobook:{id}} - {@code
   *             spotify:user:{id}} - {@code spotify:playlist:{id}}
   * @return A {@link RemoveItemsFromLibraryRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/remove-library-items">Spotify Web API reference</a>
   */
  public RemoveItemsFromLibraryRequest.Builder removeItemsFromLibrary(String uris) {
    return new RemoveItemsFromLibraryRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .uris(uris);
  }

  /**
   * Add one or more items to the current user's library. Accepts Spotify URIs for tracks, albums, episodes, shows,
   * audiobooks, users, and playlists.
   *
   * @param uris A comma-separated list of <a
   *             href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URIs</a>.
   *             Maximum: 40 URIs. Supported URI types: - {@code spotify:track:{id}} - {@code spotify:album:{id}} -
   *             {@code spotify:episode:{id}} - {@code spotify:show:{id}} - {@code spotify:audiobook:{id}} - {@code
   *             spotify:user:{id}} - {@code spotify:playlist:{id}}
   * @return A {@link SaveItemsToLibraryRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/save-library-items">Spotify Web API reference</a>
   */
  public SaveItemsToLibraryRequest.Builder saveItemsToLibrary(String uris) {
    return new SaveItemsToLibraryRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .uris(uris);
  }

  /**
   * Get the list of markets where Spotify is available.
   *
   * @return A {@link GetAvailableMarketsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-available-markets">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetAvailableMarketsRequest.Builder getAvailableMarkets() {
    return new GetAvailableMarketsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Add an item to be played next in the user's current playback queue. This API only works for users who have Spotify
   * Premium. The order of execution is not guaranteed when you use this API with other Player API endpoints.
   *
   * @param uri The uri of the item to add to the queue. Must be a track or an episode uri.
   * @return A {@link AddItemToPlaybackQueueRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/add-to-queue">Spotify Web API reference</a>
   */
  public AddItemToPlaybackQueueRequest.Builder addItemToPlaybackQueue(String uri) {
    return new AddItemToPlaybackQueueRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .uri(uri);
  }

  /**
   * Get information about a user’s available Spotify Connect devices. Some device models are not supported and will not
   * be listed in the API response.
   *
   * @return A {@link GetAvailableDevicesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-a-users-available-devices">Spotify Web API reference</a>
   */
  public GetAvailableDevicesRequest.Builder getAvailableDevices() {
    return new GetAvailableDevicesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get the object currently being played on the user's Spotify account.
   *
   * @return A {@link GetCurrentlyPlayingTrackRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-the-users-currently-playing-track">Spotify Web API reference</a>
   */
  public GetCurrentlyPlayingTrackRequest.Builder getCurrentlyPlayingTrack() {
    return new GetCurrentlyPlayingTrackRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get information about the user’s current playback state, including track or episode, progress, and active device.
   *
   * @return A {@link GetPlaybackStateRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-information-about-the-users-current-playback">Spotify Web API reference</a>
   */
  public GetPlaybackStateRequest.Builder getPlaybackState() {
    return new GetPlaybackStateRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get tracks from the current user's recently played tracks. <i><b>Note</b>: Currently doesn't support podcast
   * episodes.</i>
   *
   * @return A {@link GetRecentlyPlayedTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-recently-played">Spotify Web API reference</a>
   */
  public GetRecentlyPlayedTracksRequest.Builder getRecentlyPlayedTracks() {
    return new GetRecentlyPlayedTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get the list of objects that make up the user's queue.
   *
   * @return A {@link GetUsersQueueRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-queue">Spotify Web API reference</a>
   */
  public GetUsersQueueRequest.Builder getUsersQueue() {
    return new GetUsersQueueRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Pause playback on the user's account. This API only works for users who have Spotify Premium. The order of
   * execution is not guaranteed when you use this API with other Player API endpoints.
   *
   * @return A {@link PausePlaybackRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/pause-a-users-playback">Spotify Web API reference</a>
   */
  public PausePlaybackRequest.Builder pausePlayback() {
    return new PausePlaybackRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Seeks to the given position in the user’s currently playing track. This API only works for users who have Spotify
   * Premium. The order of execution is not guaranteed when you use this API with other Player API endpoints.
   *
   * @param position_ms The position in milliseconds to seek to. Must be a positive number. Passing in a position that
   *                    is greater than the length of the track will cause the player to start playing the next song.
   * @return A {@link SeekToPositionRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/seek-to-position-in-currently-playing-track">Spotify Web API reference</a>
   */
  public SeekToPositionRequest.Builder seekToPosition(Integer position_ms) {
    return new SeekToPositionRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .position_ms(position_ms);
  }

  /**
   * Set the volume for the user’s current playback device. This API only works for users who have Spotify Premium. The
   * order of execution is not guaranteed when you use this API with other Player API endpoints.
   *
   * @param volume_percent The volume to set. Must be a value from 0 to 100 inclusive.
   * @return A {@link SetPlaybackVolumeRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/set-volume-for-users-playback">Spotify Web API reference</a>
   */
  public SetPlaybackVolumeRequest.Builder setPlaybackVolume(Integer volume_percent) {
    return new SetPlaybackVolumeRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .volume_percent(volume_percent);
  }

  /**
   * Set the repeat mode for the user's playback. This API only works for users who have Spotify Premium. The order of
   * execution is not guaranteed when you use this API with other Player API endpoints.
   *
   * @param state <b>track</b>, <b>context</b> or <b>off</b>.<br> <b>track</b> will repeat the current track.<br>
   *              <b>context</b> will repeat the current context.<br> <b>off</b> will turn repeat off.
   * @return A {@link SetRepeatModeRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/set-repeat-mode-on-users-playback">Spotify Web API reference</a>
   */
  public SetRepeatModeRequest.Builder setRepeatMode(String state) {
    return new SetRepeatModeRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .state(state);
  }

  /**
   * Skips to next track in the user’s queue. This API only works for users who have Spotify Premium. The order of
   * execution is not guaranteed when you use this API with other Player API endpoints.
   *
   * @return A {@link SkipToNextRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/skip-users-playback-to-next-track">Spotify Web API reference</a>
   */
  public SkipToNextRequest.Builder skipToNext() {
    return new SkipToNextRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Skips to previous track in the user’s queue. This API only works for users who have Spotify Premium. The order of
   * execution is not guaranteed when you use this API with other Player API endpoints.
   *
   * @return A {@link SkipToPreviousRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/skip-users-playback-to-previous-track">Spotify Web API reference</a>
   */
  public SkipToPreviousRequest.Builder skipToPrevious() {
    return new SkipToPreviousRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Start a new context or resume current playback on the user's active device. This API only works for users who have
   * Spotify Premium. The order of execution is not guaranteed when you use this API with other Player API endpoints.
   *
   * @return A {@link StartResumePlaybackRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/start-a-users-playback">Spotify Web API reference</a>
   */
  public StartResumePlaybackRequest.Builder startResumePlayback() {
    return new StartResumePlaybackRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Toggle shuffle on or off for user’s playback. This API only works for users who have Spotify Premium. The order of
   * execution is not guaranteed when you use this API with other Player API endpoints.
   *
   * @param state <b>true</b> : Shuffle user's playback.<br> <b>false</b> : Do not shuffle user's playback.
   * @return A {@link TogglePlaybackShuffleRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/toggle-shuffle-for-users-playback">Spotify Web API reference</a>
   */
  public TogglePlaybackShuffleRequest.Builder togglePlaybackShuffle(Boolean state) {
    return new TogglePlaybackShuffleRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .state(state);
  }

  /**
   * Transfer playback to a new device and optionally begin playback. This API only works for users who have Spotify
   * Premium. The order of execution is not guaranteed when you use this API with other Player API endpoints.
   *
   * @param device_ids A JSON array containing the ID of the device on which playback should be
   *                   started/transferred.<br>For example:{@code
   *                   {device_ids:["74ASZWbe4lXaubB36ztrGX"]}}<br>_<b>Note</b>: Although an array is accepted, only a
   *                   single device_id is currently supported. Supplying more than one will return {@code 400 Bad
   *                   Request}_
   * @return A {@link TransferPlaybackRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/transfer-a-users-playback">Spotify Web API reference</a>
   */
  public TransferPlaybackRequest.Builder transferPlayback(List<String> device_ids) {
    return new TransferPlaybackRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .device_ids(device_ids);
  }

  /**
   * <b>Deprecated:</b> Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/add-items-to-playlist">Add Items to
   * Playlist</a> instead. Add one or more items to a user's playlist.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link AddItemsToPlaylistDeprecatedRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/add-tracks-to-playlist">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public AddItemsToPlaylistDeprecatedRequest.Builder addItemsToPlaylistDeprecated(String playlist_id) {
    return new AddItemsToPlaylistDeprecatedRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Add one or more items to a user's playlist.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link AddItemsToPlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/add-items-to-playlist">Spotify Web API reference</a>
   */
  public AddItemsToPlaylistRequest.Builder addItemsToPlaylist(String playlist_id) {
    return new AddItemsToPlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Change a playlist's name and public/private state. (The user must, of course, own the playlist.)
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link ChangePlaylistDetailsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/change-playlist-details">Spotify Web API reference</a>
   */
  public ChangePlaylistDetailsRequest.Builder changePlaylistDetails(String playlist_id) {
    return new ChangePlaylistDetailsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * <b>Deprecated</b>: Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/create-playlist">Create Playlist</a> instead.
   * Create a playlist for a Spotify user. (The playlist will be empty until you <a
   * href="https://developer.spotify.com/documentation/web-api/reference/add-tracks-to-playlist">add tracks</a>.) Each
   * user is generally limited to a maximum of 11000 playlists.
   *
   * @param user_id The user's <a
   *                href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify user
   *                ID</a>.
   * @param name The name for the new playlist, for example {@code "Your Coolest Playlist"}. This name does not need to
   *             be unique; a user may have several playlists with the same name.
   * @return A {@link CreatePlaylistForUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/create-playlist-for-user">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public CreatePlaylistForUserRequest.Builder createPlaylistForUser(String user_id, String name) {
    return new CreatePlaylistForUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .user_id(user_id)
      .name(name);
  }

  /**
   * Create a playlist for the current Spotify user. (The playlist will be empty until you <a
   * href="https://developer.spotify.com/documentation/web-api/reference/add-tracks-to-playlist">add tracks</a>.) Each
   * user is generally limited to a maximum of 11000 playlists.
   *
   * @param name The name for the new playlist, for example {@code "Your Coolest Playlist"}. This name does not need to
   *             be unique; a user may have several playlists with the same name.
   * @return A {@link CreatePlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/create-playlist">Spotify Web API reference</a>
   */
  public CreatePlaylistRequest.Builder createPlaylist(String name) {
    return new CreatePlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .name(name);
  }

  /**
   * Get a list of Spotify playlists tagged with a particular category.
   *
   * @param category_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    category ID</a> for the category.
   * @return A {@link GetCategoryPlaylistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-a-categories-playlists">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetCategoryPlaylistsRequest.Builder getCategoryPlaylists(String category_id) {
    return new GetCategoryPlaylistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .category_id(category_id);
  }

  /**
   * Get a list of the playlists owned or followed by the current Spotify user.
   *
   * @return A {@link GetCurrentUsersPlaylistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-a-list-of-current-users-playlists">Spotify Web API reference</a>
   */
  public GetCurrentUsersPlaylistsRequest.Builder getCurrentUsersPlaylists() {
    return new GetCurrentUsersPlaylistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get a list of Spotify featured playlists (shown, for example, on a Spotify player's 'Browse' tab).
   *
   * @return A {@link GetFeaturedPlaylistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-featured-playlists">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetFeaturedPlaylistsRequest.Builder getFeaturedPlaylists() {
    return new GetFeaturedPlaylistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get the current image associated with a specific playlist.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link GetPlaylistCoverImageRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-playlist-cover">Spotify Web API reference</a>
   */
  public GetPlaylistCoverImageRequest.Builder getPlaylistCoverImage(String playlist_id) {
    return new GetPlaylistCoverImageRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * <b>Deprecated:</b> Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/get-playlists-items">Get Playlist Items</a>
   * instead. Get full details of the items of a playlist owned by a Spotify user.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link GetPlaylistItemsDeprecatedRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-playlists-tracks">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetPlaylistItemsDeprecatedRequest.Builder getPlaylistItemsDeprecated(String playlist_id) {
    return new GetPlaylistItemsDeprecatedRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Get full details of the items of a playlist owned by a Spotify user. <b>Note</b>: This endpoint is only accessible
   * for playlists owned by the current user or playlists the user is a collaborator of. A {@code 403 Forbidden} status
   * code will be returned if the user is neither the owner nor a collaborator of the playlist.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link GetPlaylistItemsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-playlists-items">Spotify Web API reference</a>
   */
  public GetPlaylistItemsRequest.Builder getPlaylistItems(String playlist_id) {
    return new GetPlaylistItemsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Get a playlist owned by a Spotify user.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link GetPlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-playlist">Spotify Web API reference</a>
   */
  public GetPlaylistRequest.Builder getPlaylist(String playlist_id) {
    return new GetPlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Get a list of the playlists owned or followed by a Spotify user.
   *
   * @param user_id The user's <a
   *                href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify user
   *                ID</a>.
   * @return A {@link GetUsersPlaylistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-list-users-playlists">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetUsersPlaylistsRequest.Builder getUsersPlaylists(String user_id) {
    return new GetUsersPlaylistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .user_id(user_id);
  }

  /**
   * <b>Deprecated:</b> Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/remove-items-playlist">Remove Playlist
   * Items</a> instead. Remove one or more items from a user's playlist.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @param tracks An array of objects containing <a
   *               href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URIs</a>
   *               of the tracks or episodes to remove. For example: {@code { "tracks": [{ "uri":
   *               "spotify:track:4iV5W9uYEdYUVa79Axb7Rh" },{ "uri": "spotify:track:1301WleyT98MSxVHPZCA6M" }] }}. A
   *               maximum of 100 objects can be sent at once. Pass the uri values; each is sent as {{@literal @}code
   *               {"uri": value}}.
   * @return A {@link RemovePlaylistItemsDeprecatedRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/remove-tracks-playlist">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public RemovePlaylistItemsDeprecatedRequest.Builder removePlaylistItemsDeprecated(String playlist_id, List<String> tracks) {
    return new RemovePlaylistItemsDeprecatedRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .tracks(tracks);
  }

  /**
   * Remove one or more items from a user's playlist.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @param items An array of objects containing <a
   *              href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URIs</a>
   *              of the tracks or episodes to remove. For example: {@code { "items": [{ "uri":
   *              "spotify:track:4iV5W9uYEdYUVa79Axb7Rh" },{ "uri": "spotify:track:1301WleyT98MSxVHPZCA6M" }] }}. A
   *              maximum of 100 objects can be sent at once. Pass the uri values; each is sent as {{@literal @}code
   *              {"uri": value}}.
   * @return A {@link RemovePlaylistItemsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/remove-items-playlist">Spotify Web API reference</a>
   */
  public RemovePlaylistItemsRequest.Builder removePlaylistItems(String playlist_id, List<String> items) {
    return new RemovePlaylistItemsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .items(items);
  }

  /**
   * <b>Deprecated:</b> Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/reorder-or-replace-playlists-items">Update
   * Playlist Items</a> instead. Either reorder or replace items in a playlist depending on the request's parameters. To
   * reorder items, include {@code range_start}, {@code insert_before}, {@code range_length} and {@code snapshot_id} in
   * the request's body. To replace items, include {@code uris} as either a query parameter or in the request's body.
   * Replacing items in a playlist will overwrite its existing items. This operation can be used for replacing or
   * clearing items in a playlist. <br> <b>Note</b>: Replace and reorder are mutually exclusive operations which share
   * the same endpoint, but have different parameters. These operations can't be applied together in a single request.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link UpdatePlaylistsItemsReorderDeprecatedRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/reorder-or-replace-playlists-tracks">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public UpdatePlaylistsItemsReorderDeprecatedRequest.Builder updatePlaylistsItemsReorderDeprecated(String playlist_id) {
    return new UpdatePlaylistsItemsReorderDeprecatedRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Either reorder or replace items in a playlist depending on the request's parameters. To reorder items, include
   * {@code range_start}, {@code insert_before}, {@code range_length} and {@code snapshot_id} in the request's body. To
   * replace items, include {@code uris} as either a query parameter or in the request's body. Replacing items in a
   * playlist will overwrite its existing items. This operation can be used for replacing or clearing items in a
   * playlist. <br> <b>Note</b>: Replace and reorder are mutually exclusive operations which share the same endpoint,
   * but have different parameters. These operations can't be applied together in a single request.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link UpdatePlaylistsItemsReorderRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/reorder-or-replace-playlists-items">Spotify Web API reference</a>
   */
  public UpdatePlaylistsItemsReorderRequest.Builder updatePlaylistsItemsReorder(String playlist_id) {
    return new UpdatePlaylistsItemsReorderRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * <b>Deprecated:</b> Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/reorder-or-replace-playlists-items">Update
   * Playlist Items</a> instead. Either reorder or replace items in a playlist depending on the request's parameters. To
   * reorder items, include {@code range_start}, {@code insert_before}, {@code range_length} and {@code snapshot_id} in
   * the request's body. To replace items, include {@code uris} as either a query parameter or in the request's body.
   * Replacing items in a playlist will overwrite its existing items. This operation can be used for replacing or
   * clearing items in a playlist. <br> <b>Note</b>: Replace and reorder are mutually exclusive operations which share
   * the same endpoint, but have different parameters. These operations can't be applied together in a single request.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link UpdatePlaylistsItemsReplaceDeprecatedRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/reorder-or-replace-playlists-tracks">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public UpdatePlaylistsItemsReplaceDeprecatedRequest.Builder updatePlaylistsItemsReplaceDeprecated(String playlist_id) {
    return new UpdatePlaylistsItemsReplaceDeprecatedRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Either reorder or replace items in a playlist depending on the request's parameters. To reorder items, include
   * {@code range_start}, {@code insert_before}, {@code range_length} and {@code snapshot_id} in the request's body. To
   * replace items, include {@code uris} as either a query parameter or in the request's body. Replacing items in a
   * playlist will overwrite its existing items. This operation can be used for replacing or clearing items in a
   * playlist. <br> <b>Note</b>: Replace and reorder are mutually exclusive operations which share the same endpoint,
   * but have different parameters. These operations can't be applied together in a single request.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link UpdatePlaylistsItemsReplaceRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/reorder-or-replace-playlists-items">Spotify Web API reference</a>
   */
  public UpdatePlaylistsItemsReplaceRequest.Builder updatePlaylistsItemsReplace(String playlist_id) {
    return new UpdatePlaylistsItemsReplaceRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Get Spotify catalog information about albums, artists, playlists, tracks, shows, episodes or audiobooks that match
   * a keyword string. Audiobooks are only available within the US, UK, Canada, Ireland, New Zealand and Australia
   * markets.
   *
   * @param q Your search query. You can narrow down your search using field filters. The available filters are {@code
   *          album}, {@code artist}, {@code track}, {@code year}, {@code upc}, {@code tag:hipster}, {@code tag:new},
   *          {@code isrc}, and {@code genre}. Each field filter only applies to certain result types. The {@code
   *          artist} and {@code year} filters can be used while searching albums, artists and tracks. You can filter on
   *          a single {@code year} or a range (e.g. 1955-1960).<br> The {@code album} filter can be used while
   *          searching albums and tracks.<br> The {@code genre} filter can be used while searching artists and
   *          tracks.<br> The {@code isrc} and {@code track} filters can be used while searching tracks.<br> The {@code
   *          upc}, {@code tag:new} and {@code tag:hipster} filters can only be used while searching albums. The {@code
   *          tag:new} filter will return albums released in the past two weeks and {@code tag:hipster} can be used to
   *          return only albums with the lowest 10% popularity.<br>
   * @param type A comma-separated list of item types to search across. Search results include hits from all the
   *             specified item types. For example: {@code q=abacab&amp;type=album,track} returns both albums and tracks
   *             matching "abacab".
   * @return A {@link SearchForItemRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/search">Spotify Web API reference</a>
   */
  public SearchForItemRequest.Builder searchForItem(String q, String type) {
    return new SearchForItemRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q)
      .type(type);
  }

  /**
   * Get Spotify catalog information about albums, artists, playlists, tracks, shows, episodes or audiobooks that match
   * a keyword string. Audiobooks are only available within the US, UK, Canada, Ireland, New Zealand and Australia
   * markets.
   *
   * @param q Your search query. You can narrow down your search using field filters. The available filters are {@code
   *          album}, {@code artist}, {@code track}, {@code year}, {@code upc}, {@code tag:hipster}, {@code tag:new},
   *          {@code isrc}, and {@code genre}. Each field filter only applies to certain result types. The {@code
   *          artist} and {@code year} filters can be used while searching albums, artists and tracks. You can filter on
   *          a single {@code year} or a range (e.g. 1955-1960).<br> The {@code album} filter can be used while
   *          searching albums and tracks.<br> The {@code genre} filter can be used while searching artists and
   *          tracks.<br> The {@code isrc} and {@code track} filters can be used while searching tracks.<br> The {@code
   *          upc}, {@code tag:new} and {@code tag:hipster} filters can only be used while searching albums. The {@code
   *          tag:new} filter will return albums released in the past two weeks and {@code tag:hipster} can be used to
   *          return only albums with the lowest 10% popularity.<br>
   * @return A {@link SearchAlbumsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/search">Spotify Web API reference</a>
   */
  public SearchAlbumsRequest.Builder searchAlbums(String q) {
    return new SearchAlbumsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about albums, artists, playlists, tracks, shows, episodes or audiobooks that match
   * a keyword string. Audiobooks are only available within the US, UK, Canada, Ireland, New Zealand and Australia
   * markets.
   *
   * @param q Your search query. You can narrow down your search using field filters. The available filters are {@code
   *          album}, {@code artist}, {@code track}, {@code year}, {@code upc}, {@code tag:hipster}, {@code tag:new},
   *          {@code isrc}, and {@code genre}. Each field filter only applies to certain result types. The {@code
   *          artist} and {@code year} filters can be used while searching albums, artists and tracks. You can filter on
   *          a single {@code year} or a range (e.g. 1955-1960).<br> The {@code album} filter can be used while
   *          searching albums and tracks.<br> The {@code genre} filter can be used while searching artists and
   *          tracks.<br> The {@code isrc} and {@code track} filters can be used while searching tracks.<br> The {@code
   *          upc}, {@code tag:new} and {@code tag:hipster} filters can only be used while searching albums. The {@code
   *          tag:new} filter will return albums released in the past two weeks and {@code tag:hipster} can be used to
   *          return only albums with the lowest 10% popularity.<br>
   * @return A {@link SearchArtistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/search">Spotify Web API reference</a>
   */
  public SearchArtistsRequest.Builder searchArtists(String q) {
    return new SearchArtistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about albums, artists, playlists, tracks, shows, episodes or audiobooks that match
   * a keyword string. Audiobooks are only available within the US, UK, Canada, Ireland, New Zealand and Australia
   * markets.
   *
   * @param q Your search query. You can narrow down your search using field filters. The available filters are {@code
   *          album}, {@code artist}, {@code track}, {@code year}, {@code upc}, {@code tag:hipster}, {@code tag:new},
   *          {@code isrc}, and {@code genre}. Each field filter only applies to certain result types. The {@code
   *          artist} and {@code year} filters can be used while searching albums, artists and tracks. You can filter on
   *          a single {@code year} or a range (e.g. 1955-1960).<br> The {@code album} filter can be used while
   *          searching albums and tracks.<br> The {@code genre} filter can be used while searching artists and
   *          tracks.<br> The {@code isrc} and {@code track} filters can be used while searching tracks.<br> The {@code
   *          upc}, {@code tag:new} and {@code tag:hipster} filters can only be used while searching albums. The {@code
   *          tag:new} filter will return albums released in the past two weeks and {@code tag:hipster} can be used to
   *          return only albums with the lowest 10% popularity.<br>
   * @return A {@link SearchAudiobooksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/search">Spotify Web API reference</a>
   */
  public SearchAudiobooksRequest.Builder searchAudiobooks(String q) {
    return new SearchAudiobooksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about albums, artists, playlists, tracks, shows, episodes or audiobooks that match
   * a keyword string. Audiobooks are only available within the US, UK, Canada, Ireland, New Zealand and Australia
   * markets.
   *
   * @param q Your search query. You can narrow down your search using field filters. The available filters are {@code
   *          album}, {@code artist}, {@code track}, {@code year}, {@code upc}, {@code tag:hipster}, {@code tag:new},
   *          {@code isrc}, and {@code genre}. Each field filter only applies to certain result types. The {@code
   *          artist} and {@code year} filters can be used while searching albums, artists and tracks. You can filter on
   *          a single {@code year} or a range (e.g. 1955-1960).<br> The {@code album} filter can be used while
   *          searching albums and tracks.<br> The {@code genre} filter can be used while searching artists and
   *          tracks.<br> The {@code isrc} and {@code track} filters can be used while searching tracks.<br> The {@code
   *          upc}, {@code tag:new} and {@code tag:hipster} filters can only be used while searching albums. The {@code
   *          tag:new} filter will return albums released in the past two weeks and {@code tag:hipster} can be used to
   *          return only albums with the lowest 10% popularity.<br>
   * @return A {@link SearchEpisodesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/search">Spotify Web API reference</a>
   */
  public SearchEpisodesRequest.Builder searchEpisodes(String q) {
    return new SearchEpisodesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about albums, artists, playlists, tracks, shows, episodes or audiobooks that match
   * a keyword string. Audiobooks are only available within the US, UK, Canada, Ireland, New Zealand and Australia
   * markets.
   *
   * @param q Your search query. You can narrow down your search using field filters. The available filters are {@code
   *          album}, {@code artist}, {@code track}, {@code year}, {@code upc}, {@code tag:hipster}, {@code tag:new},
   *          {@code isrc}, and {@code genre}. Each field filter only applies to certain result types. The {@code
   *          artist} and {@code year} filters can be used while searching albums, artists and tracks. You can filter on
   *          a single {@code year} or a range (e.g. 1955-1960).<br> The {@code album} filter can be used while
   *          searching albums and tracks.<br> The {@code genre} filter can be used while searching artists and
   *          tracks.<br> The {@code isrc} and {@code track} filters can be used while searching tracks.<br> The {@code
   *          upc}, {@code tag:new} and {@code tag:hipster} filters can only be used while searching albums. The {@code
   *          tag:new} filter will return albums released in the past two weeks and {@code tag:hipster} can be used to
   *          return only albums with the lowest 10% popularity.<br>
   * @return A {@link SearchPlaylistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/search">Spotify Web API reference</a>
   */
  public SearchPlaylistsRequest.Builder searchPlaylists(String q) {
    return new SearchPlaylistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about albums, artists, playlists, tracks, shows, episodes or audiobooks that match
   * a keyword string. Audiobooks are only available within the US, UK, Canada, Ireland, New Zealand and Australia
   * markets.
   *
   * @param q Your search query. You can narrow down your search using field filters. The available filters are {@code
   *          album}, {@code artist}, {@code track}, {@code year}, {@code upc}, {@code tag:hipster}, {@code tag:new},
   *          {@code isrc}, and {@code genre}. Each field filter only applies to certain result types. The {@code
   *          artist} and {@code year} filters can be used while searching albums, artists and tracks. You can filter on
   *          a single {@code year} or a range (e.g. 1955-1960).<br> The {@code album} filter can be used while
   *          searching albums and tracks.<br> The {@code genre} filter can be used while searching artists and
   *          tracks.<br> The {@code isrc} and {@code track} filters can be used while searching tracks.<br> The {@code
   *          upc}, {@code tag:new} and {@code tag:hipster} filters can only be used while searching albums. The {@code
   *          tag:new} filter will return albums released in the past two weeks and {@code tag:hipster} can be used to
   *          return only albums with the lowest 10% popularity.<br>
   * @return A {@link SearchShowsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/search">Spotify Web API reference</a>
   */
  public SearchShowsRequest.Builder searchShows(String q) {
    return new SearchShowsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about albums, artists, playlists, tracks, shows, episodes or audiobooks that match
   * a keyword string. Audiobooks are only available within the US, UK, Canada, Ireland, New Zealand and Australia
   * markets.
   *
   * @param q Your search query. You can narrow down your search using field filters. The available filters are {@code
   *          album}, {@code artist}, {@code track}, {@code year}, {@code upc}, {@code tag:hipster}, {@code tag:new},
   *          {@code isrc}, and {@code genre}. Each field filter only applies to certain result types. The {@code
   *          artist} and {@code year} filters can be used while searching albums, artists and tracks. You can filter on
   *          a single {@code year} or a range (e.g. 1955-1960).<br> The {@code album} filter can be used while
   *          searching albums and tracks.<br> The {@code genre} filter can be used while searching artists and
   *          tracks.<br> The {@code isrc} and {@code track} filters can be used while searching tracks.<br> The {@code
   *          upc}, {@code tag:new} and {@code tag:hipster} filters can only be used while searching albums. The {@code
   *          tag:new} filter will return albums released in the past two weeks and {@code tag:hipster} can be used to
   *          return only albums with the lowest 10% popularity.<br>
   * @return A {@link SearchTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/search">Spotify Web API reference</a>
   */
  public SearchTracksRequest.Builder searchTracks(String q) {
    return new SearchTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about albums, artists, playlists, tracks, shows, episodes or audiobooks that match
   * a keyword string. Audiobooks are only available within the US, UK, Canada, Ireland, New Zealand and Australia
   * markets.
   *
   * @param q Your search query. You can narrow down your search using field filters. The available filters are {@code
   *          album}, {@code artist}, {@code track}, {@code year}, {@code upc}, {@code tag:hipster}, {@code tag:new},
   *          {@code isrc}, and {@code genre}. Each field filter only applies to certain result types. The {@code
   *          artist} and {@code year} filters can be used while searching albums, artists and tracks. You can filter on
   *          a single {@code year} or a range (e.g. 1955-1960).<br> The {@code album} filter can be used while
   *          searching albums and tracks.<br> The {@code genre} filter can be used while searching artists and
   *          tracks.<br> The {@code isrc} and {@code track} filters can be used while searching tracks.<br> The {@code
   *          upc}, {@code tag:new} and {@code tag:hipster} filters can only be used while searching albums. The {@code
   *          tag:new} filter will return albums released in the past two weeks and {@code tag:hipster} can be used to
   *          return only albums with the lowest 10% popularity.<br>
   * @return A {@link SearchAlbumsSpecialRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/search">Spotify Web API reference</a>
   */
  public SearchAlbumsSpecialRequest.Builder searchAlbumsSpecial(String q) {
    return new SearchAlbumsSpecialRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Check if one or more shows is already saved in the current Spotify user's library. <b>Note:</b> This endpoint is
   * deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/check-library-contains">Check User's Saved
   * Items</a> instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the shows. Maximum: 50 IDs.
   * @return A {@link CheckUsersSavedShowsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/check-users-saved-shows">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public CheckUsersSavedShowsRequest.Builder checkUsersSavedShows(String ids) {
    return new CheckUsersSavedShowsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get Spotify catalog information for several shows based on their Spotify IDs.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the shows. Maximum: 50 IDs.
   * @return A {@link GetSeveralShowsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-multiple-shows">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetSeveralShowsRequest.Builder getSeveralShows(String ids) {
    return new GetSeveralShowsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get Spotify catalog information about an show’s episodes. Optional parameters can be used to limit the number of
   * episodes returned.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> for the show.
   * @return A {@link GetShowEpisodesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-a-shows-episodes">Spotify Web API reference</a>
   */
  public GetShowEpisodesRequest.Builder getShowEpisodes(String id) {
    return new GetShowEpisodesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for a single show identified by its unique Spotify ID.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> for the show.
   * @return A {@link GetShowRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-a-show">Spotify Web API reference</a>
   */
  public GetShowRequest.Builder getShow(String id) {
    return new GetShowRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get a list of shows saved in the current Spotify user's library. Optional parameters can be used to limit the
   * number of shows returned.
   *
   * @return A {@link GetUsersSavedShowsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-users-saved-shows">Spotify Web API reference</a>
   */
  public GetUsersSavedShowsRequest.Builder getUsersSavedShows() {
    return new GetUsersSavedShowsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Delete one or more shows from current Spotify user's library. <b>Note:</b> This endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/remove-library-items">Remove Items from
   * Library</a> instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the shows. Maximum: 50 IDs.
   * @return A {@link RemoveShowsForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/remove-shows-user">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public RemoveShowsForCurrentUserRequest.Builder removeShowsForCurrentUser(String ids) {
    return new RemoveShowsForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Save one or more shows to current Spotify user's library. <b>Note:</b> This endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/save-library-items">Save Items to Library</a>
   * instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the shows. Maximum: 50 IDs.
   * @return A {@link SaveShowsForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/save-shows-user">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public SaveShowsForCurrentUserRequest.Builder saveShowsForCurrentUser(String ids) {
    return new SaveShowsForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Check if one or more tracks is already saved in the current Spotify user's 'Your Music' library. <b>Note:</b> This
   * endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/check-library-contains">Check User's Saved
   * Items</a> instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>.
   *            For example: {@code ids=4iV5W9uYEdYUVa79Axb7Rh,1301WleyT98MSxVHPZCA6M}. Maximum: 50 IDs.
   * @return A {@link CheckUsersSavedTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/check-users-saved-tracks">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public CheckUsersSavedTracksRequest.Builder checkUsersSavedTracks(String ids) {
    return new CheckUsersSavedTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Recommendations are generated based on the available information for a given seed entity and matched against
   * similar artists and tracks. If there is sufficient information about the provided seeds, a list of tracks will be
   * returned together with pool size details. For artists and tracks that are very new or obscure there might not be
   * enough data to generate a list of tracks.
   *
   * @return A {@link GetRecommendationsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-recommendations">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetRecommendationsRequest.Builder getRecommendations() {
    return new GetRecommendationsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get audio features for multiple tracks based on their Spotify IDs.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> for
   *            the tracks. Maximum: 100 IDs.
   * @return A {@link GetSeveralTracksAudioFeaturesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-several-audio-features">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetSeveralTracksAudioFeaturesRequest.Builder getSeveralTracksAudioFeatures(String ids) {
    return new GetSeveralTracksAudioFeaturesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get Spotify catalog information for multiple tracks based on their Spotify IDs.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>.
   *            For example: {@code ids=4iV5W9uYEdYUVa79Axb7Rh,1301WleyT98MSxVHPZCA6M}. Maximum: 50 IDs.
   * @return A {@link GetSeveralTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-several-tracks">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetSeveralTracksRequest.Builder getSeveralTracks(String ids) {
    return new GetSeveralTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get Spotify catalog information for a single track identified by its unique Spotify ID.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> for the track.
   * @return A {@link GetTrackRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-track">Spotify Web API reference</a>
   */
  public GetTrackRequest.Builder getTrack(String id) {
    return new GetTrackRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get a low-level audio analysis for a track in the Spotify catalog. The audio analysis describes the track’s
   * structure and musical content, including rhythm, pitch, and timbre.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> for the track.
   * @return A {@link GetTracksAudioAnalysisRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-audio-analysis">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetTracksAudioAnalysisRequest.Builder getTracksAudioAnalysis(String id) {
    return new GetTracksAudioAnalysisRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get audio feature information for a single track identified by its unique Spotify ID.
   *
   * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *           ID</a> for the track.
   * @return A {@link GetTracksAudioFeaturesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-audio-features">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetTracksAudioFeaturesRequest.Builder getTracksAudioFeatures(String id) {
    return new GetTracksAudioFeaturesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get a list of the songs saved in the current Spotify user's 'Your Music' library.
   *
   * @return A {@link GetUsersSavedTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-users-saved-tracks">Spotify Web API reference</a>
   */
  public GetUsersSavedTracksRequest.Builder getUsersSavedTracks() {
    return new GetUsersSavedTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Remove one or more tracks from the current user's 'Your Music' library. <b>Note:</b> This endpoint is deprecated.
   * Use <a href="https://developer.spotify.com/documentation/web-api/reference/remove-library-items">Remove Items from
   * Library</a> instead.
   *
   * @param ids A comma-separated list of the <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>.
   *            For example: {@code ids=4iV5W9uYEdYUVa79Axb7Rh,1301WleyT98MSxVHPZCA6M}. Maximum: 50 IDs.
   * @return A {@link RemoveUsersSavedTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/remove-tracks-user">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public RemoveUsersSavedTracksRequest.Builder removeUsersSavedTracks(String ids) {
    return new RemoveUsersSavedTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Save one or more tracks to the current user's 'Your Music' library. <b>Note:</b> This endpoint is deprecated. Use
   * <a href="https://developer.spotify.com/documentation/web-api/reference/save-library-items">Save Items to
   * Library</a> instead.
   *
   * @return A {@link SaveTracksForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/save-tracks-user">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public SaveTracksForCurrentUserRequest.Builder saveTracksForCurrentUser() {
    return new SaveTracksForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Check to see if the current user is following one or more artists or other Spotify users. <b>Note:</b> This
   * endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/check-library-contains">Check User's Saved
   * Items</a> instead.
   *
   * @param type The ID type: either {@code artist} or {@code user}.
   * @param ids A comma-separated list of the artist or the user <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a> to
   *            check. For example: {@code ids=74ASZWbe4lXaubB36ztrGX,08td7MxkoHQkXnWAYD8d6Q}. A maximum of 50 IDs can
   *            be sent in one request.
   * @return A {@link CheckCurrentUserFollowsArtistsOrUsersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/check-current-user-follows">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public CheckCurrentUserFollowsArtistsOrUsersRequest.Builder checkCurrentUserFollowsArtistsOrUsers(ModelObjectType type, String ids) {
    return new CheckCurrentUserFollowsArtistsOrUsersRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .type(type)
      .ids(ids);
  }

  /**
   * Check to see if the current user is following a specified playlist. <b>Note:</b> This endpoint is deprecated. Use
   * <a href="https://developer.spotify.com/documentation/web-api/reference/check-library-contains">Check User's Saved
   * Items</a> instead.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link CheckIfUserFollowsPlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/check-if-user-follows-playlist">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public CheckIfUserFollowsPlaylistRequest.Builder checkIfUserFollowsPlaylist(String playlist_id) {
    return new CheckIfUserFollowsPlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Add the current user as a follower of one or more artists or other Spotify users. <b>Note:</b> This endpoint is
   * deprecated. Use <a href="https://developer.spotify.com/documentation/web-api/reference/save-library-items">Save
   * Items to Library</a> instead.
   *
   * @param type The ID type.
   * @param ids A comma-separated list of the artist or the user <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>. A
   *            maximum of 50 IDs can be sent in one request.
   * @return A {@link FollowArtistsOrUsersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/follow-artists-users">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public FollowArtistsOrUsersRequest.Builder followArtistsOrUsers(ModelObjectType type, String ids) {
    return new FollowArtistsOrUsersRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .type(type)
      .ids(ids);
  }

  /**
   * Add the current user as a follower of a playlist. <b>Note:</b> This endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/save-library-items">Save Items to Library</a>
   * instead.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link FollowPlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/follow-playlist">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public FollowPlaylistRequest.Builder followPlaylist(String playlist_id) {
    return new FollowPlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Get detailed profile information about the current user (including the current user's username).
   *
   * @return A {@link GetCurrentUsersProfileRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-current-users-profile">Spotify Web API reference</a>
   */
  public GetCurrentUsersProfileRequest.Builder getCurrentUsersProfile() {
    return new GetCurrentUsersProfileRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get the current user's followed artists.
   *
   * @param type The ID type: currently only {@code artist} is supported.
   * @return A {@link GetFollowedArtistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-followed">Spotify Web API reference</a>
   */
  public GetFollowedArtistsRequest.Builder getFollowedArtists(ModelObjectType type) {
    return new GetFollowedArtistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .type(type);
  }

  /**
   * Get public profile information about a Spotify user.
   *
   * @param user_id The user's <a
   *                href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify user
   *                ID</a>.
   * @return A {@link GetUsersProfileRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-users-profile">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetUsersProfileRequest.Builder getUsersProfile(String user_id) {
    return new GetUsersProfileRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .user_id(user_id);
  }

  /**
   * Remove the current user as a follower of one or more artists or other Spotify users. <b>Note:</b> This endpoint is
   * deprecated. Use <a href="https://developer.spotify.com/documentation/web-api/reference/remove-library-items">Remove
   * Items from Library</a> instead.
   *
   * @param type The ID type: either {@code artist} or {@code user}.
   * @param ids A comma-separated list of the artist or the user <a
   *            href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify IDs</a>.
   *            For example: {@code ids=74ASZWbe4lXaubB36ztrGX,08td7MxkoHQkXnWAYD8d6Q}. A maximum of 50 IDs can be sent
   *            in one request.
   * @return A {@link UnfollowArtistsOrUsersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/unfollow-artists-users">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public UnfollowArtistsOrUsersRequest.Builder unfollowArtistsOrUsers(ModelObjectType type, String ids) {
    return new UnfollowArtistsOrUsersRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .type(type)
      .ids(ids);
  }

  /**
   * Remove the current user as a follower of a playlist. <b>Note:</b> This endpoint is deprecated. Use <a
   * href="https://developer.spotify.com/documentation/web-api/reference/remove-library-items">Remove Items from
   * Library</a> instead.
   *
   * @param playlist_id The <a
   *                    href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify
   *                    ID</a> of the playlist.
   * @return A {@link UnfollowPlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/unfollow-playlist">Spotify Web API reference</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public UnfollowPlaylistRequest.Builder unfollowPlaylist(String playlist_id) {
    return new UnfollowPlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Get the current user's top artists or tracks based on calculated affinity.
   *
   * @return A {@link GetUsersTopArtistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-users-top-artists-and-tracks">Spotify Web API reference</a>
   */
  public GetUsersTopArtistsRequest.Builder getUsersTopArtists() {
    return new GetUsersTopArtistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get the current user's top artists or tracks based on calculated affinity.
   *
   * @return A {@link GetUsersTopTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/reference/get-users-top-artists-and-tracks">Spotify Web API reference</a>
   */
  public GetUsersTopTracksRequest.Builder getUsersTopTracks() {
    return new GetUsersTopTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }
  // </generated-requests>


  /**
   * Get the current user's top artists or tracks based on calculated affinity. <br><br>
   * <p>
   * Affinity is a measure of the expected preference an user has for a particular track or artist. It is based on user
   * behavior, including play history, but does not include actions made while in incognito mode. Light or infrequent
   * users of Spotify may not have sufficient play history to generate a full affinity data set.
   *
   * @param type The type of entity to return. Valid values: artists or tracks.
   * @param <T>  Either {@link Artist} or
   *             {@link Track}
   * @return A {@link GetUsersTopArtistsAndTracksRequest.Builder}.
   */
  public <T extends IArtistTrackModelObject> GetUsersTopArtistsAndTracksRequest.Builder<T> getUsersTopArtistsAndTracks(ModelObjectType type) {
    return new GetUsersTopArtistsAndTracksRequest.Builder<T>(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .type(type);
  }

  /**
   * Replace the image used to represent a specific playlist.
   *
   * @param playlist_id The Spotify ID for the playlist.
   * @return An {@link AddCustomPlaylistCoverImageRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public AddCustomPlaylistCoverImageRequest.Builder addCustomPlaylistCoverImage(String playlist_id) {
    return new AddCustomPlaylistCoverImageRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Builder class for building {@link SpotifyApi} instances.
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
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * The HttpManager setter.
     *
     * @param httpManager A Spotify HttpManager.
     * @return A {@link Builder}.
     */
    public Builder setHttpManager(IHttpManager httpManager) {
      this.httpManager = httpManager;
      return this;
    }

    /**
     * The scheme setter.
     *
     * @param scheme A HTTP-scheme.
     * @return A {@link Builder}.
     */
    public Builder setScheme(String scheme) {
      this.scheme = scheme;
      return this;
    }

    /**
     * The Spotify API host setter.
     *
     * @param host A Spotify API host.
     * @return A {@link Builder}.
     */
    public Builder setHost(String host) {
      this.host = host;
      return this;
    }

    /**
     * The Spotify API port setter.
     *
     * @param port A Spotify API port.
     * @return A {@link Builder}.
     */
    public Builder setPort(Integer port) {
      this.port = port;
      return this;
    }

    /**
     * The proxy URL setter.
     *
     * @param proxyUrl A proxy URL.
     * @return A {@link Builder}.
     */
    public Builder setProxyUrl(String proxyUrl) {
      this.proxyUrl = proxyUrl;
      return this;
    }

    /**
     * The proxy port setter.
     *
     * @param proxyPort A proxy port.
     * @return A {@link Builder}.
     */
    public Builder setProxyPort(Integer proxyPort) {
      this.proxyPort = proxyPort;
      return this;
    }

    /**
     * The proxy username setter.
     *
     * @param proxyUsername A proxy username.
     * @return A {@link Builder}.
     */
    public Builder setProxyUsername(Integer proxyUsername) {
      this.proxyUsername = proxyUsername;
      return this;
    }

    /**
     * The proxy password setter.
     *
     * @param proxyPassword A proxy password.
     * @return A {@link Builder}.
     */
    public Builder setProxyPassword(Integer proxyPassword) {
      this.proxyPassword = proxyPassword;
      return this;
    }

    /**
     * The client ID setter.
     *
     * @param clientId A client ID of your application.
     * @return A {@link Builder}.
     */
    public Builder setClientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    /**
     * The client secret setter.
     *
     * @param clientSecret A client secret of your application.
     * @return A {@link Builder}.
     */
    public Builder setClientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
      return this;
    }

    /**
     * The redirect URI setter.
     *
     * @param redirectUri A redirect URI of your application.
     * @return A {@link Builder}.
     */
    public Builder setRedirectUri(URI redirectUri) {
      this.redirectUri = redirectUri;
      return this;
    }

    /**
     * The access token setter.
     *
     * @param accessToken A Spotify API access token.
     * @return A {@link Builder}.
     */
    public Builder setAccessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    /**
     * The refresh token setter.
     *
     * @param refreshToken A Spotify API refresh token.
     * @return A {@link Builder}.
     */
    public Builder setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    /**
     * Build a {@link SpotifyApi} instance with the information given to the builder.
     *
     * @return A {@link SpotifyApi} instance.
     */
    public SpotifyApi build() {
      return new SpotifyApi(this);
    }
  }
}
