package se.michaelthelin.spotify;

import se.michaelthelin.spotify.UtilProtos.Url.Scheme;
import se.michaelthelin.spotify.methods.RefreshAccessTokenRequest;
import se.michaelthelin.spotify.methods.Request;
import se.michaelthelin.spotify.methods.TokenRequest;

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

  public AuthenticationApi(Builder builder) {
    host = builder.host;
    port = builder.port;
    scheme = builder.scheme;
    httpManager = builder.httpManager;
  }

  public static Builder builder() {
    return new Builder();
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

  private void setDefaults(Request.Builder builder) {
    builder.httpManager(httpManager);
    builder.scheme(scheme);
    builder.host(host);
    builder.port(port);
  }

  public RefreshAccessTokenRequest.Builder refreshAccessToken(String clientId, String clientSecret, String refreshToken) {
    RefreshAccessTokenRequest.Builder builder = RefreshAccessTokenRequest.builder();
    setDefaults(builder);
    builder.grantType("refresh_token");
    builder.refreshToken(refreshToken);
    builder.authorizationHeader(clientId, clientSecret);
    return builder;
  }

  public static class Builder {

    private String host = DEFAULT_HOST;
    private int port = DEFAULT_PORT;
    private Scheme scheme = DEFAULT_SCHEME;
    private HttpManager httpManager = DEFAULT_HTTP_MANAGER;

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

    public AuthenticationApi build() {
      return new AuthenticationApi(this);
    }

  }
}
