package se.michaelthelin.spotify;

import se.michaelthelin.spotify.UtilProtos.Url.Scheme;
import se.michaelthelin.spotify.exceptions.ErrorResponseException;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.methods.RefreshAccessTokenRequest;
import se.michaelthelin.spotify.methods.Request;
import se.michaelthelin.spotify.methods.TokenRequest;
import se.michaelthelin.spotify.models.TokenResponse;

import java.io.IOException;

public class AuthenticationApi {

  public static final String DEFAULT_HOST = "accounts.spotify.com";

  public static final int DEFAULT_PORT = 443;

  public static final Scheme DEFAULT_SCHEME = Scheme.HTTPS;

  public static final HttpManager DEFAULT_HTTP_MANAGER = SpotifyHttpManager.builder().build();

  public static final AuthenticationApi DEFAULT_API = AuthenticationApi.builder().build();

  private HttpManager httpManager = null;
  private String host;
  private int port;
  private Scheme scheme;
  private String clientId;
  private String clientSecret;
  private String code;
  private String redirectUri;
  private String accessToken;
  private String refreshToken;

  public AuthenticationApi(Builder builder) {
    host = builder.host;
    port = builder.port;
    scheme = builder.scheme;
    httpManager = builder.httpManager;
    clientId = builder.clientId;
    clientSecret = builder.clientSecret;
    code = builder.code;
    redirectUri = builder.redirectUri;
    accessToken = builder.accessToken;
    refreshToken = builder.refreshToken;
  }

  public TokenRequest.Builder getTokens() {
    return getTokens(clientId, clientSecret, code, redirectUri);
  }

  public TokenRequest.Builder getTokens(String clientId, String clientSecret, String code, String redirectUri) {
    TokenRequest.Builder builder = TokenRequest.builder();
    setDefaults(builder);
    builder.code(code);
    builder.redirectUri(redirectUri);
    builder.grantType("authorization_code");
    builder.authorizationHeader(clientId, clientSecret);
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

  public String getAccessToken() throws UnexpectedResponseException, ErrorResponseException, IOException {
    if (noneOrInvalidAccessToken()) {
      refreshTokens();
    }
    return accessToken;
  }

  // Todo: Check if token has expired and refresh if so
  public boolean noneOrInvalidAccessToken() {
    return accessToken == null;
  }

  // Todo: Save expiry time.
  public void refreshTokens() throws UnexpectedResponseException, ErrorResponseException, IOException {
    TokenRequest request = getTokens().build();
    TokenResponse response = request.post();
    refreshToken = response.getRefreshtoken();
    accessToken = response.getAccessToken();
  }

  private void setDefaults(Request.Builder builder) {
    builder.httpManager(httpManager);
    builder.scheme(scheme);
    builder.host(host);
    builder.port(port);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String host = DEFAULT_HOST;
    private int port = DEFAULT_PORT;
    private Scheme scheme = DEFAULT_SCHEME;
    private HttpManager httpManager = DEFAULT_HTTP_MANAGER;
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String refreshToken;
    private String accessToken;

    public Builder host(String host) {
      this.host = host;
      return this;
    }

    public Builder port(int port) {
      this.port = port;
      return this;
    }

    public Builder scheme(Scheme scheme) {
      this.scheme = scheme;
      return this;
    }

    public Builder httpManager(HttpManager httpManager) {
      this.httpManager = httpManager;
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

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public Builder redirectUri(String redirectUri) {
      this.redirectUri = redirectUri;
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

    public AuthenticationApi build() {
      return new AuthenticationApi(this);
    }

  }
}
