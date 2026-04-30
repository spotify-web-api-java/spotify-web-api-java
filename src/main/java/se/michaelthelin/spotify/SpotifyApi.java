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
import se.michaelthelin.spotify.requests.data.albums.CheckUsersSavedAlbumsRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumTracksRequest;
import se.michaelthelin.spotify.requests.data.albums.GetNewReleasesRequest;
import se.michaelthelin.spotify.requests.data.albums.GetSeveralAlbumsRequest;
import se.michaelthelin.spotify.requests.data.albums.GetUsersSavedAlbumsRequest;
import se.michaelthelin.spotify.requests.data.albums.RemoveAlbumsForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.albums.SaveAlbumsForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.artists.*;
import se.michaelthelin.spotify.requests.data.audiobooks.CheckUsersSavedAudiobooksRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.GetAudiobookChaptersRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.GetAudiobookRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.GetSeveralAudiobooksRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.GetUsersSavedAudiobooksRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.RemoveAudiobooksForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.audiobooks.SaveAudiobooksForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.categories.*;
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
import se.michaelthelin.spotify.requests.data.users.CheckCurrentUserFollowsArtistsOrUsersRequest;
import se.michaelthelin.spotify.requests.data.users.CheckIfUserFollowsPlaylistRequest;
import se.michaelthelin.spotify.requests.data.users.FollowArtistsOrUsersRequest;
import se.michaelthelin.spotify.requests.data.users.FollowPlaylistRequest;
import se.michaelthelin.spotify.requests.data.users.GetUsersProfileRequest;
import se.michaelthelin.spotify.requests.data.users.UnfollowArtistsOrUsersRequest;
import se.michaelthelin.spotify.requests.data.users.interfaces.IArtistTrackModelObject;
import se.michaelthelin.spotify.requests.data.users.simplified.GetUsersTopArtistsRequest;
import se.michaelthelin.spotify.requests.data.users.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.player.*;
import se.michaelthelin.spotify.requests.data.playlists.*;
import se.michaelthelin.spotify.requests.data.search.SearchForItemRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.*;
import se.michaelthelin.spotify.requests.data.search.simplified.special.SearchAlbumsSpecialRequest;
import se.michaelthelin.spotify.requests.data.shows.GetShowRequest;
import se.michaelthelin.spotify.requests.data.shows.GetShowEpisodesRequest;
import se.michaelthelin.spotify.requests.data.shows.CheckUsersSavedShowsRequest;
import se.michaelthelin.spotify.requests.data.shows.GetSeveralShowsRequest;
import se.michaelthelin.spotify.requests.data.shows.GetUsersSavedShowsRequest;
import se.michaelthelin.spotify.requests.data.shows.RemoveShowsForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.shows.SaveShowsForCurrentUserRequest;
import se.michaelthelin.spotify.requests.data.tracks.*;
import se.michaelthelin.spotify.requests.data.users.GetCurrentUsersProfileRequest;
import se.michaelthelin.spotify.requests.data.users.GetFollowedArtistsRequest;
import se.michaelthelin.spotify.requests.data.users.GetUsersTopArtistsAndTracksRequest;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

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
  private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT = ThreadLocal.withInitial(() -> makeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", "GMT"));

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
    return SIMPLE_DATE_FORMAT.get().parse(date);
  }

  /**
   * Formats a date, using the default spotify format.
   *
   * @param date the date to format
   * @return the formatted date
   */
  public static String formatDefaultDate(Date date) {
    return SIMPLE_DATE_FORMAT.get().format(date);
  }

  /**
   * Creates a SimpleDateFormat with the specified pattern and timezone.
   *
   * @param pattern the date pattern
   * @param id the timezone ID
   * @return a configured SimpleDateFormat instance
   */
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

  /**
   * Get Spotify catalog information for a single album.
   *
   * @param id The Spotify ID for the album.
   * @return A {@link GetAlbumRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetAlbumRequest.Builder getAlbum(String id) {
    return new GetAlbumRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information about an album's tracks.
   * Optional parameters can be used to limit the number of tracks returned.
   *
   * @param id The Spotify ID of the album.
   * @return A {@link GetAlbumTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetAlbumTracksRequest.Builder getAlbumTracks(String id) {
    return new GetAlbumTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for several albums identified by their Spotify IDs.
   *
   * @param ids A comma-separated list of the Spotify IDs for the albums. Maximum: 20 IDs.
   * @return A {@link GetSeveralAlbumsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
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
   * Get an audiobook.
   *
   * @param id The Spotify ID for the audiobook.
   * @return A {@link GetAudiobookRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetAudiobookRequest.Builder getAudiobook(String id) {
    return new GetAudiobookRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for several audiobooks identified by their Spotify IDs.
   *
   * @param ids A comma-separated list of the Spotify IDs for the audiobooks. Maximum: 50 IDs.
   * @return A {@link GetSeveralAudiobooksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
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
   * Get Spotify catalog information about an audiobook's chapters.
   *
   * @param id The Spotify ID for the audiobook.
   * @return A {@link GetAudiobookChaptersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetAudiobookChaptersRequest.Builder getAudiobookChapters(String id) {
    return new GetAudiobookChaptersRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for a single audiobook chapter. Chapters are only available within the US, UK, Canada, Ireland, New Zealand and Australia markets.
   *
   * @param id The Spotify ID for the chapter.
   * @return A {@link GetChapterRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetChapterRequest.Builder getChapter(String id) {
    return new GetChapterRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for several chapters identified by their Spotify IDs.
   *
   * @param ids A comma-separated list of the Spotify IDs for the chapters. Maximum: 50 IDs.
   * @return A {@link GetSeveralChaptersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
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
   * Get Spotify catalog information for a single artist identified by their unique Spotify ID.
   *
   * @param id The Spotify ID of the artist.
   * @return A {@link GetArtistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetArtistRequest.Builder getArtist(String id) {
    return new GetArtistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information about an artist's albums.
   *
   * @param id The Spotify ID of the artist.
   * @return A {@link GetArtistsAlbumsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetArtistsAlbumsRequest.Builder getArtistsAlbums(String id) {
    return new GetArtistsAlbumsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information about artists similar to a given artist. Similarity is based on analysis of the Spotify community's listening history.
   *
   * @param id The Spotify ID of the artist.
   * @return A {@link GetArtistsRelatedArtistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
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
   * @param id The Spotify ID for the artist.
   * @return A {@link GetArtistsTopTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
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
   * @param ids A comma-separated list of the Spotify IDs for the artists. Maximum: 50 IDs.
   * @return A {@link GetSeveralArtistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
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
   * Get a list of categories used to tag items in Spotify.
   *
   * @return A {@link GetSeveralBrowseCategoriesRequest.Builder}.
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public GetSeveralBrowseCategoriesRequest.Builder getSeveralBrowseCategories() {
    return new GetSeveralBrowseCategoriesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get a single category used to tag items in Spotify (on, for example, the Spotify player's "Browse" tab).
   *
   * @param category_id The Spotify category ID for the category.
   * @return A {@link GetSingleBrowseCategoryRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public GetSingleBrowseCategoryRequest.Builder getSingleBrowseCategory(String category_id) {
    return new GetSingleBrowseCategoryRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .category_id(category_id);
  }

  /**
   * Get a list of new album releases featured in Spotify.
   *
   * @return A {@link GetNewReleasesRequest.Builder}.
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public GetNewReleasesRequest.Builder getNewReleases() {
    return new GetNewReleasesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get the playlists from a specific category.
   *
   * @param category_id The Spotify category ID for the category.
   * @return A {@link GetCategoryPlaylistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>

   *
   * @deprecated Use the Search API instead.
   */
  @Deprecated
  public GetCategoryPlaylistsRequest.Builder getCategoryPlaylists(String category_id) {
    return new GetCategoryPlaylistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .category_id(category_id);
  }

  /**
   * Get "Featured Playlists" of different countries which may match a specific language.
   *
   * @return A {@link GetFeaturedPlaylistsRequest.Builder}.
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetFeaturedPlaylistsRequest.Builder getFeaturedPlaylists() {
    return new GetFeaturedPlaylistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Create a playlist-style listening experience based on seed artists, tracks and genres.
   *
   * @return A {@link GetRecommendationsRequest.Builder}.
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetRecommendationsRequest.Builder getRecommendations() {
    return new GetRecommendationsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Retrieve a list of available genres seed parameter values for recommendations.
   *
   * @return A {@link GetRecommendationGenresRequest.Builder}.
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetRecommendationGenresRequest.Builder getRecommendationGenres() {
    return new GetRecommendationGenresRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get Spotify catalog information for a single episode identified by its unique Spotify ID.
   *
   * @param id The Spotify ID of the episode.
   * @return A {@link GetEpisodeRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetEpisodeRequest.Builder getEpisode(String id) {
    return new GetEpisodeRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for several episodes based on their Spotify IDs.
   *
   * @param ids A comma-separated list of the Spotify IDs for the episodes. Maximum: 50 IDs.
   * @return A {@link GetSeveralEpisodesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
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
   * Check to see if the current user is following one or more artists or other Spotify users.
   *
   * @param type The ID type: either artist or user.
   * @param ids  A list of the artist or the user Spotify IDs to check. Maximum: 50 IDs.
   * @return A {@link CheckCurrentUserFollowsArtistsOrUsersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>

   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public CheckCurrentUserFollowsArtistsOrUsersRequest.Builder checkCurrentUserFollowsArtistsOrUsers(
    ModelObjectType type, String[] ids) {
    return new CheckCurrentUserFollowsArtistsOrUsersRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .type(type)
      .ids(concat(ids, ','));
  }

  /**
   * Check to see if one or more Spotify users are following a specified playlist.
   *
   * @param playlist_id The Spotify ID of the playlist.
   * @param ids         A list of Spotify User IDs; the IDs of the users that you want to check to see if they
   *                    follow the playlist. Maximum: 5 IDs.
   * @return A {@link CheckIfUserFollowsPlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>

   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public CheckIfUserFollowsPlaylistRequest.Builder checkIfUserFollowsPlaylist(
    String playlist_id, String[] ids) {
    return new CheckIfUserFollowsPlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .ids(concat(ids, ','));
  }

  /**
   * Add the current user as a follower of one or more artists or other Spotify users.
   *
   * @param type The ID type: either artist or user.
   * @param ids  A list of the artist or the user Spotify IDs. Maximum: 50 IDs.
   * @return A {@link FollowArtistsOrUsersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>

   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public FollowArtistsOrUsersRequest.Builder followArtistsOrUsers(ModelObjectType type, String[] ids) {
    return new FollowArtistsOrUsersRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .type(type)
      .ids(concat(ids, ','));
  }

  /**
   * Add the current user as a follower of one or more artists or other Spotify users.
   *
   * @param type The ID type: either artist or user.
   * @param ids  A list of the artist or the user Spotify IDs. Maximum: 50 IDs.
   * @return A {@link FollowArtistsOrUsersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public FollowArtistsOrUsersRequest.Builder followArtistsOrUsers(ModelObjectType type, JsonArray ids) {
    return new FollowArtistsOrUsersRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .type(type)
      .ids(ids);
  }

  /**
   * Add the current user as a follower of a playlist.
   *
   * @param playlist_id The Spotify ID of the playlist. Any playlist can be followed, regardless of its
   *                    public/private status, as long as you know its playlist ID.
   * @param public_     Default: true. If true the playlist will be included in user's public playlists, if false it
   *                    will remain private. To be able to follow playlists privately, the user must have granted the
   *                    playlist-modify-private scope.
   * @return A {@link FollowPlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>

   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public se.michaelthelin.spotify.requests.data.users.FollowPlaylistRequest.Builder followPlaylist(String playlist_id, boolean public_) {
    return new se.michaelthelin.spotify.requests.data.users.FollowPlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .public_(public_);
  }


  /**
   * Get the current user’s followed artists.
   *
   * @param type The ID type: currently only artist is supported.
   * @return A {@link GetFollowedArtistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetFollowedArtistsRequest.Builder getFollowedArtists(ModelObjectType type) {
    return new GetFollowedArtistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .type(type);
  }

  /**
   * Remove the current user as a follower of one or more artists or other Spotify users.
   *
   * @param type The ID type: either artist or user.
   * @param ids  A list of the artist or the user Spotify IDs. Maximum: 50 IDs.
   * @return A {@link UnfollowArtistsOrUsersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public UnfollowArtistsOrUsersRequest.Builder unfollowArtistsOrUsers(ModelObjectType type, String[] ids) {
    return new UnfollowArtistsOrUsersRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .type(type)
      .ids(concat(ids, ','));
  }

  /**
   * Remove the current user as a follower of one or more artists or other Spotify users.
   *
   * @param type The ID type: either artist or user.
   * @param ids  A JSON array of the artist or the user Spotify IDs. Maximum: 50 IDs.
   * @return A {@link UnfollowArtistsOrUsersRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public UnfollowArtistsOrUsersRequest.Builder unfollowArtistsOrUsers(ModelObjectType type, JsonArray ids) {
    return new UnfollowArtistsOrUsersRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .type(type)
      .ids(ids);
  }

  /**
   * Remove the current user as a follower of a playlist.
   *
   * @param playlist_id The playlist's ID.
   * @return An {@link se.michaelthelin.spotify.requests.data.users.UnfollowPlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>

   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public se.michaelthelin.spotify.requests.data.users.UnfollowPlaylistRequest.Builder unfollowPlaylist(String playlist_id) {
    return new se.michaelthelin.spotify.requests.data.users.UnfollowPlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Check if one or more albums are already saved in the current Spotify user's 'Your Music' library.
   *
   * @param ids The album IDs to check for in the user's Your Music library. Maximum: 50 IDs.
   * @return A {@link CheckUsersSavedAlbumsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   *
   * @deprecated Use {@link se.michaelthelin.spotify.requests.data.library.CheckLibraryContainsRequest} instead.
   */
  @Deprecated
  public CheckUsersSavedAlbumsRequest.Builder checkUsersSavedAlbums(String... ids) {
    return new CheckUsersSavedAlbumsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(concat(ids, ','));
  }

  /**
   * Check if one or more shows are already saved in the current Spotify user's library.
   *
   * @param ids The show IDs to check for in the user's Your Music library. Maximum: 50 IDs.
   * @return A {@link CheckUsersSavedShowsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   *
   * @deprecated Use {@link se.michaelthelin.spotify.requests.data.library.CheckLibraryContainsRequest} instead.
   */
  @Deprecated
  public CheckUsersSavedShowsRequest.Builder checkUsersSavedShows(String... ids) {
    return new CheckUsersSavedShowsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(concat(ids, ','));
  }

  /**
   * Check if one or more episodes are already saved in the current Spotify user's 'Your Episodes' library.
   *
   * @param ids The episode IDs to check for in the user's 'Your Episodes' library. Maximum: 50 IDs.
   * @return A {@link CheckUsersSavedEpisodesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   *
   * @deprecated Use {@link se.michaelthelin.spotify.requests.data.library.CheckLibraryContainsRequest} instead.
   */
  @Deprecated
  public CheckUsersSavedEpisodesRequest.Builder checkUsersSavedEpisodes(String... ids) {
    return new CheckUsersSavedEpisodesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(concat(ids, ','));
  }

  /**
   * Check if one or more tracks are already saved in the current Spotify user's 'Your Music' library.
   *
   * @param ids The track IDs to check for in the user's Your Music library. Maximum: 50 IDs.
   * @return A {@link CheckUsersSavedTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   *
   * @deprecated Use {@link se.michaelthelin.spotify.requests.data.library.CheckLibraryContainsRequest} instead.
   */
  @Deprecated
  public CheckUsersSavedTracksRequest.Builder checkUsersSavedTracks(String... ids) {
    return new CheckUsersSavedTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(concat(ids, ','));
  }

  /**
   * Check if one or more audiobooks are already saved in the current Spotify user's library.
   *
   * @param ids A comma-separated list of the Spotify IDs for the audiobooks. Maximum: 50 IDs.
   * @return A {@link CheckUsersSavedAudiobooksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>

   *
   * @deprecated Use {@link se.michaelthelin.spotify.requests.data.library.CheckLibraryContainsRequest} instead.
   */
  @Deprecated
  public CheckUsersSavedAudiobooksRequest.Builder checkUsersSavedAudiobooks(String ids) {
    return new CheckUsersSavedAudiobooksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Get a list of the albums saved in the current Spotify user’s "Your Music" library.
   *
   * @return A {@link GetUsersSavedAlbumsRequest.Builder}.
   */
  public GetUsersSavedAlbumsRequest.Builder getUsersSavedAlbums() {
    return new GetUsersSavedAlbumsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get a list of shows saved in the current Spotify user’s library.
   *
   * @return A {@link GetUsersSavedShowsRequest.Builder}.
   */
  public GetUsersSavedShowsRequest.Builder getUsersSavedShows() {
    return new GetUsersSavedShowsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get a list of the episodes saved in the current Spotify user's library.
   * This endpoint is in <b>beta</b> and could change without warning.
   *
   * @return A {@link GetUsersSavedEpisodesRequest.Builder}.
   */
  public GetUsersSavedEpisodesRequest.Builder getUsersSavedEpisodes() {
    return new GetUsersSavedEpisodesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get a list of the audiobooks saved in the current Spotify user's library.
   *
   * @return A {@link GetUsersSavedAudiobooksRequest.Builder}.
   */
  public GetUsersSavedAudiobooksRequest.Builder getUsersSavedAudiobooks() {
    return new GetUsersSavedAudiobooksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get an user's "Your Music" tracks.
   *
   * @return A {@link GetUsersSavedTracksRequest.Builder}.
   */
  public GetUsersSavedTracksRequest.Builder getUsersSavedTracks() {
    return new GetUsersSavedTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Add one or more items to the current user's library. Accepts Spotify URIs for tracks, albums, episodes, shows, audiobooks, users, and playlists.
   *
   * @param uris The Spotify URIs to save. Maximum: 50 URIs.
   * @return A {@link SaveItemsToLibraryRequest.Builder}.
   */
  public SaveItemsToLibraryRequest.Builder saveItemsToLibrary(JsonArray uris) {
    return new SaveItemsToLibraryRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .uris(uris);
  }

  /**
   * Save one or more audiobooks to the current Spotify user's library.
   *
   * @param ids A comma-separated list of the Spotify IDs for the audiobooks. Maximum: 50 IDs.
   * @return A {@link SaveAudiobooksForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public SaveAudiobooksForCurrentUserRequest.Builder saveAudiobooksForCurrentUser(String ids) {
    return new SaveAudiobooksForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Remove one or more audiobooks from the current Spotify user's library.
   *
   * @param ids A comma-separated list of the Spotify IDs for the audiobooks. Maximum: 50 IDs.
   * @return A {@link RemoveAudiobooksForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public RemoveAudiobooksForCurrentUserRequest.Builder removeAudiobooksForCurrentUser(String ids) {
    return new RemoveAudiobooksForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Check if one or more items are already saved in the current Spotify user's library.
   *
   * @param uris A comma-separated list of the Spotify URIs for the items. Maximum: 40 URIs.
   * @return A {@link CheckUsersSavedItemsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public CheckUsersSavedItemsRequest.Builder checkUsersSavedItems(String uris) {
    return new CheckUsersSavedItemsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .uris(uris);
  }

  /**
   * Save one or more albums to the current user's library.
   *
   * @param ids A comma-separated list of the Spotify IDs for the albums. Maximum: 50 IDs.
   * @return A {@link SaveAlbumsForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public SaveAlbumsForCurrentUserRequest.Builder saveAlbumsForCurrentUser(String ids) {
    return new SaveAlbumsForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Remove one or more albums from the current user's library.
   *
   * @param ids A comma-separated list of the Spotify IDs for the albums. Maximum: 50 IDs.
   * @return A {@link RemoveAlbumsForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public RemoveAlbumsForCurrentUserRequest.Builder removeAlbumsForCurrentUser(String ids) {
    return new RemoveAlbumsForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Save one or more episodes to the current user's library.
   *
   * @param ids A comma-separated list of the Spotify IDs for the episodes. Maximum: 50 IDs.
   * @return A {@link SaveEpisodesForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public SaveEpisodesForCurrentUserRequest.Builder saveEpisodesForCurrentUser(String ids) {
    return new SaveEpisodesForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Remove one or more episodes from the current user's library.
   *
   * @param ids A comma-separated list of the Spotify IDs for the episodes. Maximum: 50 IDs.
   * @return A {@link RemoveEpisodesForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public RemoveEpisodesForCurrentUserRequest.Builder removeEpisodesForCurrentUser(String ids) {
    return new RemoveEpisodesForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Save one or more shows to the current user's library.
   *
   * @param ids A comma-separated list of the Spotify IDs for the shows. Maximum: 50 IDs.
   * @return A {@link SaveShowsForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public SaveShowsForCurrentUserRequest.Builder saveShowsForCurrentUser(String ids) {
    return new SaveShowsForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Remove one or more shows from the current user's library.
   *
   * @param ids A comma-separated list of the Spotify IDs for the shows. Maximum: 50 IDs.
   * @return A {@link RemoveShowsForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public RemoveShowsForCurrentUserRequest.Builder removeShowsForCurrentUser(String ids) {
    return new RemoveShowsForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Save one or more tracks to the current user's library.
   *
   * @param ids A comma-separated list of the Spotify IDs for the tracks. Maximum: 50 IDs.
   * @return A {@link SaveTracksForCurrentUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public SaveTracksForCurrentUserRequest.Builder saveTracksForCurrentUser(String ids) {
    return new SaveTracksForCurrentUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Remove one or more tracks from the current user's library.
   *
   * @param ids A comma-separated list of the Spotify IDs for the tracks. Maximum: 50 IDs.
   * @return A {@link RemoveUsersSavedTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public RemoveUsersSavedTracksRequest.Builder removeUsersSavedTracks(String ids) {
    return new RemoveUsersSavedTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(ids);
  }

  /**
   * Remove a list of Spotify URIs from the user's library.
   *
   * @param uris The Spotify URIs to remove. Maximum: 50 URIs.
   * @return A {@link RemoveItemsFromLibraryRequest.Builder}.
   */
  public RemoveItemsFromLibraryRequest.Builder removeItemsFromLibrary(JsonArray uris) {
    return new RemoveItemsFromLibraryRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .uris(uris);
  }

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
   * Get the current user's top artists based on calculated affinity.
   *
   * @return A {@link GetUsersTopArtistsRequest.Builder}.
   * @see #getUsersTopArtistsAndTracks(ModelObjectType)
   */
  public GetUsersTopArtistsRequest.Builder getUsersTopArtists() {
    return new GetUsersTopArtistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get the current user's top tracks based on calculated affinity.
   *
   * @return A {@link GetUsersTopTracksRequest.Builder}.
   * @see #getUsersTopArtistsAndTracks(ModelObjectType)
   */
  public GetUsersTopTracksRequest.Builder getUsersTopTracks() {
    return new GetUsersTopTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get information about the user's current playback state, including context, track progress, and active device.
   *
   * @return A {@link GetPlaybackStateRequest.Builder}.
   */
  public GetPlaybackStateRequest.Builder getPlaybackState() {
    return new GetPlaybackStateRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get tracks from the current user's recently played tracks. <br><br>
   * <p>
   * Returns the most recent 50 tracks played by an user. Note that a track currently playing will not be visible in play
   * history until it has completed. A track must be played for more than 30 seconds to be included in play history.
   * <p>
   * Any tracks listened to while the user had "Private Session" enabled in their client will not be returned in the
   * list of recently played tracks.
   *
   * @return A {@link GetRecentlyPlayedTracksRequest.Builder}.
   */
  public GetRecentlyPlayedTracksRequest.Builder getRecentlyPlayedTracks() {
    return new GetRecentlyPlayedTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get information about an user's available devices.
   *
   * @return A {@link GetAvailableDevicesRequest.Builder}.
   */
  public GetAvailableDevicesRequest.Builder getAvailableDevices() {
    return new GetAvailableDevicesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get the object currently being played on the user's Spotify account.
   *
   * @return A {@link GetCurrentlyPlayingTrackRequest.Builder}.
   */
  public GetCurrentlyPlayingTrackRequest.Builder getCurrentlyPlayingTrack() {
    return new GetCurrentlyPlayingTrackRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Pause playback on the user's account.
   *
   * @return A {@link PausePlaybackRequest.Builder}.
   */
  public PausePlaybackRequest.Builder pausePlayback() {
    return new PausePlaybackRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Seeks to the given position in the user's currently playing track.
   *
   * @param position_ms The position in milliseconds to seek to. Must be a positive number. Passing in a position that
   *                    is greater than the length of the track will cause the player to start playing the next song.
   * @return A {@link SeekToPositionRequest.Builder}.
   */
  public SeekToPositionRequest.Builder seekToPosition(int position_ms) {
    return new SeekToPositionRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .position_ms(position_ms);
  }

  /**
   * Set the repeat mode for the user's playback. Options are repeat-track, repeat-context, and off.
   *
   * @param state track, context or off. track will repeat the current track. context will repeat the current
   *              context. off will turn repeat off.
   * @return A {@link SetRepeatModeRequest.Builder}.
   */
  public SetRepeatModeRequest.Builder setRepeatMode(String state) {
    return new SetRepeatModeRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .state(state);
  }

  /**
   * Set the volume for the user's current playback device.
   *
   * @param volume_percent Integer. The volume to set. Must be a value from 0 to 100 inclusive.
   * @return A {@link SetPlaybackVolumeRequest.Builder}.
   */
  public SetPlaybackVolumeRequest.Builder setPlaybackVolume(int volume_percent) {
    return new SetPlaybackVolumeRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .volume_percent(volume_percent);
  }

  /**
   * Skips to next track in the user's queue.
   *
   * @return A {@link SkipToNextRequest.Builder}.
   */
  public SkipToNextRequest.Builder skipToNext() {
    return new SkipToNextRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Skips to previous track in the user's queue.
   * <p>
   * <b>Note:</b> This will ALWAYS skip to the previous track, regardless of the current track’s progress. Returning to
   * the start of the current track should be performed using the {@link #seekToPosition(int)}
   * method.
   *
   * @return A {@link SkipToPreviousRequest.Builder}.
   */
  public SkipToPreviousRequest.Builder skipToPrevious() {
    return new SkipToPreviousRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Start a new context or resume current playback on the user's active device.
   *
   * @return A {@link StartResumePlaybackRequest.Builder}.
   */
  public StartResumePlaybackRequest.Builder startResumePlayback() {
    return new StartResumePlaybackRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Toggle shuffle on or off for user's playback.
   *
   * @param state true: Shuffle user's playback. false: Do not shuffle user's playback.
   * @return A {@link TogglePlaybackShuffleRequest.Builder}.
   */
  public TogglePlaybackShuffleRequest.Builder togglePlaybackShuffle(boolean state) {
    return new TogglePlaybackShuffleRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .state(state);
  }

  /**
   * Transfer playback to a new device and determine if it should start playing.
   *
   * @param device_ids A JSON array containing the ID of the device on which playback should be started/transferred.
   *                   <br><b>Note:</b> Although an array is accepted, only a single device_id is currently supported.
   * @return A {@link TransferPlaybackRequest.Builder}.
   */
  public TransferPlaybackRequest.Builder transferPlayback(JsonArray device_ids) {
    return new TransferPlaybackRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .device_ids(device_ids);
  }

  /**
   * Add a track or an episode to the end of the user's current playback queue.
   *
   * @param uri The uri of the item to add to the queue. Must be a track or an episode uri.
   * @return A {@link AddItemToPlaybackQueueRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public AddItemToPlaybackQueueRequest.Builder addItemToPlaybackQueue(String uri) {
    return new AddItemToPlaybackQueueRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .uri(uri);
  }

  /**
   * Receive all items from the user's current playback queue.
   * @return An {@link GetUsersQueueRequest.Builder}.
   */
  public GetUsersQueueRequest.Builder getUsersQueue() {
    return new GetUsersQueueRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Add items to a playlist.
   * <p>
   * <b>Note:</b> If you want to add a large number of items (&gt;50), use {@link #addItemsToPlaylist(String, JsonArray)} to not exceed
   * the maximum URI length.
   * @param playlist_id The playlists ID.
   * @param uris        URIs of the tracks or episodes to add. Maximum: 100 item URIs.
   * @return An {@link AddItemsToPlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public AddItemsToPlaylistRequest.Builder addItemsToPlaylist(String playlist_id, String[] uris) {
    return new AddItemsToPlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .uris(concat(uris, ','));
  }

  /**
   * Add items to a playlist.
   *
   * @param playlist_id The playlists ID.
   * @param uris        URIs of the tracks or episodes to add. Maximum: 100 item URIs.
   * @return An {@link AddItemsToPlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public AddItemsToPlaylistRequest.Builder addItemsToPlaylist(String playlist_id, JsonArray uris) {
    return new AddItemsToPlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .uris(uris);
  }

  /**
   * @deprecated Use the new endpoints instead.
   * Add items to a playlist (deprecated endpoint).
   * <p>
   * <b>Note:</b> If you want to add a large number of items (&gt;50), use {@link #addItemsToPlaylistDeprecated(String, JsonArray)} to not exceed
   * the maximum URI length.
   * @param playlist_id The playlists ID.
   * @param uris        URIs of the tracks or episodes to add. Maximum: 100 item URIs.
   * @return An {@link AddItemsToPlaylistDeprecatedRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  @Deprecated
  public AddItemsToPlaylistDeprecatedRequest.Builder addItemsToPlaylistDeprecated(String playlist_id, String[] uris) {
    return new AddItemsToPlaylistDeprecatedRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .uris(concat(uris, ','));
  }

  /**
   * @deprecated Use the new endpoints instead.
   * Add items to a playlist (deprecated endpoint).
   *
   * @param playlist_id The playlists ID.
   * @param uris        URIs of the tracks or episodes to add. Maximum: 100 item URIs.
   * @return An {@link AddItemsToPlaylistDeprecatedRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  @Deprecated
  public AddItemsToPlaylistDeprecatedRequest.Builder addItemsToPlaylistDeprecated(String playlist_id, JsonArray uris) {
    return new AddItemsToPlaylistDeprecatedRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .uris(uris);
  }

  /**
   * Update a playlists properties.
   *
   * @param playlist_id The playlists ID.
   * @return A {@link ChangePlaylistDetailsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public ChangePlaylistDetailsRequest.Builder changePlaylistDetails(String playlist_id) {
    return new ChangePlaylistDetailsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Create a playlist for the current Spotify user.
   *
   * @param name The name for the new playlist.
   * @return A {@link CreatePlaylistRequest.Builder}.
   */
  public CreatePlaylistRequest.Builder createPlaylist(String name) {
    return new CreatePlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .name(name);
  }

  /**
   * Get a list of the playlists owned or followed by a Spotify user.
   *
   * @param user_id The user's Spotify user ID.
   * @return A {@link GetUsersPlaylistsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public GetUsersPlaylistsRequest.Builder getUsersPlaylists(String user_id) {
    return new GetUsersPlaylistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .user_id(user_id);
  }

  /**
   * Create a playlist for a Spotify user.
   *
   * @param user_id The user's Spotify user ID.
   * @param name    The name for the new playlist.
   * @return A {@link CreatePlaylistForUserRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public CreatePlaylistForUserRequest.Builder createPlaylistForUser(String user_id, String name) {
    return new CreatePlaylistForUserRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .user_id(user_id)
      .name(name);
  }

  /**
   * Get a list of the playlists owned or followed by the current Spotify user.
   *
   * @return A {@link GetCurrentUsersPlaylistsRequest.Builder}.
   */
  public GetCurrentUsersPlaylistsRequest.Builder getCurrentUsersPlaylists() {
    return new GetCurrentUsersPlaylistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get a playlist.
   *
   * @param playlist_id The playlists ID.
   * @return A {@link GetPlaylistRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetPlaylistRequest.Builder getPlaylist(String playlist_id) {
    return new GetPlaylistRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Get the image used to represent a specific playlist.
   *
   * @param playlist_id The Spotify ID for the playlist.
   * @return A {@link GetPlaylistCoverImageRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetPlaylistCoverImageRequest.Builder getPlaylistCoverImage(String playlist_id) {
    return new GetPlaylistCoverImageRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Get a playlist's items.
   *
   * @param playlist_id The playlists ID.
   * @return A {@link GetPlaylistItemsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetPlaylistItemsRequest.Builder getPlaylistItems(String playlist_id) {
    return new GetPlaylistItemsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id);
  }

  /**
   * Delete items from a playlist
   *
   * @param playlist_id The playlists ID.
   * @param tracks      URIs of the items to remove. Maximum: 100 track or episode URIs.
   * @return A {@link RemovePlaylistItemsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public RemovePlaylistItemsRequest.Builder removePlaylistItems(
    String playlist_id, JsonArray tracks) {
    return new RemovePlaylistItemsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .tracks(tracks);
  }

  /**
   * Reorder an item or a group of items in a playlist. <br><br>
   * <p>
   * When reordering items, the timestamp indicating when they were added and the user who added them will be kept
   * untouched. In addition, the users following the playlists won’t be notified about changes in the playlists when the
   * items are reordered.
   *
   * @param playlist_id   The Spotify ID for the playlist.
   * @param range_start   The position of the first item to be reordered.
   * @param insert_before The position where the items should be inserted. To reorder the items to the end of the
   *                      playlist, simply set insert_before to the position after the last item.
   * @return A {@link UpdatePlaylistsItemsReorderRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public UpdatePlaylistsItemsReorderRequest.Builder updatePlaylistsItemsReorder(String playlist_id, int range_start, int insert_before) {
    return new UpdatePlaylistsItemsReorderRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .range_start(range_start)
      .insert_before(insert_before);
  }

  /**
   * Replace items in a playlist.
   *
   * @param playlist_id The playlists ID.
   * @param uris        URIs of the items to set. Maximum: 100 track or episode URIs.
   * @return A {@link UpdatePlaylistsItemsReplaceRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public UpdatePlaylistsItemsReplaceRequest.Builder updatePlaylistsItemsReplace(String playlist_id, String[] uris) {
    return new UpdatePlaylistsItemsReplaceRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .uris(concat(uris, ','));
  }

  /**
   * Replace items in a playlist.
   *
   * @param playlist_id The playlists ID.
   * @param uris        URIs of the items to add. Maximum: 100 track or episode URIs.
   * @return A {@link UpdatePlaylistsItemsReplaceRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public UpdatePlaylistsItemsReplaceRequest.Builder updatePlaylistsItemsReplace(String playlist_id, JsonArray uris) {
    return new UpdatePlaylistsItemsReplaceRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .playlist_id(playlist_id)
      .uris(uris);
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
   * Get Spotify catalog information about artists, albums, episodes, shows, tracks or playlists that match a keyword string.
   *
   * @param q    The search query's keywords (and optional field filters and operators).
   * @param type A comma-separated list of item types to search across. Valid types are: album, artist, episode, show, playlist and
   *             track.
   * @return A {@link SearchForItemRequest.Builder}.
   */
  public SearchForItemRequest.Builder searchForItem(String q, String type) {
    return new SearchForItemRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q)
      .type(type);
  }

  /**
   * Get Spotify catalog information about albums that match a keyword string.
   *
   * @param q The search query's keywords (and optional field filters and operators).
   * @return A {@link SearchAlbumsRequest.Builder}.
   */
  public SearchAlbumsRequest.Builder searchAlbums(String q) {
    return new SearchAlbumsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about albums that match a keyword string.
   * <p>
   * This method exists because the searches API returns the undocumented property {@code totalTracks}, which is
   * included by this method's return type.
   *
   * @param q The search query's keywords (and optional field filters and operators).
   * @return A {@link SearchAlbumsSpecialRequest.Builder}.
   */
  public SearchAlbumsSpecialRequest.Builder searchAlbumsSpecial(String q) {
    return new SearchAlbumsSpecialRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about artists that match a keyword string.
   *
   * @param q The search query's keywords (and optional field filters and operators).
   * @return A {@link SearchArtistsRequest.Builder}.
   */
  public SearchArtistsRequest.Builder searchArtists(String q) {
    return new SearchArtistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about episodes that match a keyword string.
   *
   * @param q The search query's keywords (and optional field filters and operators).
   * @return A {@link SearchEpisodesRequest.Builder}.
   */
  public SearchEpisodesRequest.Builder searchEpisodes(String q) {
    return new SearchEpisodesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about playlists that match a keyword string.
   *
   * @param q The search query's keywords (and optional field filters and operators).
   * @return A {@link SearchPlaylistsRequest.Builder}.
   */
  public SearchPlaylistsRequest.Builder searchPlaylists(String q) {
    return new SearchPlaylistsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about shows that match a keyword string.
   *
   * @param q The search query's keywords (and optional field filters and operators).
   * @return A {@link SearchShowsRequest.Builder}.
   */
  public SearchShowsRequest.Builder searchShows(String q) {
    return new SearchShowsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about tracks that match a keyword string.
   *
   * @param q The search query's keywords (and optional field filters and operators).
   * @return A {@link SearchTracksRequest.Builder}.
   */
  public SearchTracksRequest.Builder searchTracks(String q) {
    return new SearchTracksRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .q(q);
  }

  /**
   * Get Spotify catalog information about a show. A show is either 'episodic' (containing episodes) or 'non-episodic' (containing audio only, non-episodic content).
   *
   * @param id The Spotify ID of the show.
   * @return A {@link GetShowRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetShowRequest.Builder getShow(String id) {
    return new GetShowRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information about an show’s episodes.
   *
   * @param id The Spotify ID of the show.
   * @return A {@link GetShowEpisodesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetShowEpisodesRequest.Builder getShowEpisodes(String id) {
    return new GetShowEpisodesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for several shows based on their Spotify IDs.
   *
   * @param ids A comma-separated list of the Spotify IDs for the shows. Maximum: 50 IDs.
   * @return A {@link GetSeveralShowsRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
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
   * Get a detailed audio analysis for a single track identified by its unique Spotify ID.
   *
   * @param id The Spotify ID for the track.
   * @return A {@link GetTracksAudioAnalysisRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>

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
   * Get audio features for a track based on its Spotify ID.
   *
   * @param id The Spotify ID of the track.
   * @return A {@link GetTracksAudioFeaturesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>

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
   * Get audio features for multiple tracks based on their Spotify IDs.
   *
   * @param ids A comma-separated list of the Spotify IDs for the tracks. Maximum: 100 IDs.
   * @return A {@link GetSeveralTracksAudioFeaturesRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>

   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetSeveralTracksAudioFeaturesRequest.Builder getSeveralTracksAudioFeatures(String... ids) {
    return new GetSeveralTracksAudioFeaturesRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .ids(concat(ids, ','));
  }

  /**
   * Get Spotify catalog information for a single track identified by its unique Spotify ID.
   *
   * @param id The Spotify ID of the track.
   * @return A {@link GetTrackRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   */
  public GetTrackRequest.Builder getTrack(String id) {
    return new GetTrackRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .id(id);
  }

  /**
   * Get Spotify catalog information for multiple tracks based on their Spotify IDs.
   *
   * @param ids A comma-separated list of the Spotify IDs for the tracks. Maximum: 50 IDs.
   * @return A {@link GetSeveralTracksRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
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
   * Get public profile information about a Spotify user.
   *
   * @param user_id The user's Spotify user ID.
   * @return A {@link GetUsersProfileRequest.Builder}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URLs &amp; IDs</a>
   * @deprecated This endpoint is deprecated per the Spotify API specification.
   */
  @Deprecated
  public GetUsersProfileRequest.Builder getUsersProfile(String user_id) {
    return new GetUsersProfileRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port)
      .user_id(user_id);
  }

  /**
   * Get detailed profile information about the current user (including the current user’s username).
   *
   * @return A {@link GetCurrentUsersProfileRequest.Builder}.
   */
  public GetCurrentUsersProfileRequest.Builder getCurrentUsersProfile() {
    return new GetCurrentUsersProfileRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
  }

  /**
   * Get a list of the markets where Spotify is available.
   *
   * @return A {@link GetAvailableMarketsRequest.Builder}.
   *
   * @deprecated This endpoint has been deprecated by Spotify.
   */
  @Deprecated
  public GetAvailableMarketsRequest.Builder getAvailableMarkets() {
    return new GetAvailableMarketsRequest.Builder(accessToken)
      .setDefaults(httpManager, scheme, host, port);
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
