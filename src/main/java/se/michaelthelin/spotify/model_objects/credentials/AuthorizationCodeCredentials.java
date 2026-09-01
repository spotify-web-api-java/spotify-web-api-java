package se.michaelthelin.spotify.model_objects.credentials;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Objects;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/documentation/web-api/tutorials/code-flow">Authorization Code
 * Credentials</a> by building instances from this class.
 */
public class AuthorizationCodeCredentials extends AbstractModelObject {
  /** The access token used for making authenticated requests. */
  private final String accessToken;
  /** The type of token, typically "Bearer". */
  private final String tokenType;
  /** The scopes associated with this token. */
  private final String scope;
  /** The time in seconds until the access token expires. */
  private final Integer expiresIn;
  /** The refresh token used to obtain new access tokens. */
  private final String refreshToken;

  private AuthorizationCodeCredentials(final Builder builder) {
    super(builder);

    this.accessToken = builder.accessToken;
    this.tokenType = builder.tokenType;
    this.scope = builder.scope;
    this.expiresIn = builder.expiresIn;
    this.refreshToken = builder.refreshToken;
  }

  /**
   * Get the access token. It becomes invalid after a certain period of time.
   *
   * @return An access token that can be provided in subsequent calls, for example to Spotify Web API services.
   */
  public String getAccessToken() {
    return accessToken;
  }

  /**
   * Get the type of an access token, which will always be "Bearer".
   *
   * @return How the access token may be used: always &quot;Bearer&quot;.
   */
  public String getTokenType() {
    return tokenType;
  }


  /**
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Scopes</a> specified in the authorization
   * code credentials request.
   *
   * @return The scopes specified in the credentials request.
   */
  public String getScope() {
    return scope;
  }

  /**
   * Get the time period (in seconds) for which the access token is valid.
   *
   * @return The time period (in seconds) for which the access token is valid.
   */
  public Integer getExpiresIn() {
    return expiresIn;
  }

  /**
   * Get the refresh token. This token can be sent to the Spotify Accounts service in place of an authorization code to
   * retrieve a new access token.
   *
   * @return A token that can be sent to the Spotify Accounts service in place of an access token.
   */
  public String getRefreshToken() {
    return refreshToken;
  }

  @Override
  public String toString() {
    return "AuthorizationCodeCredentials(accessToken=" + accessToken + ", tokenType=" + tokenType + ", scope=" + scope
        + ", expiresIn=" + expiresIn + ", refreshToken=" + refreshToken + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link AuthorizationCodeCredentials} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String accessToken;
    private String tokenType;
    private String scope;
    private Integer expiresIn;
    private String refreshToken;

    public Builder() {
      super();
    }

    public Builder setAccessToken(final String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public Builder setTokenType(final String tokenType) {
      this.tokenType = tokenType;
      return this;
    }

    public Builder setScope(final String scope) {
      this.scope = scope;
      return this;
    }

    public Builder setExpiresIn(final Integer expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    public Builder setRefreshToken(final String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    @Override
    public AuthorizationCodeCredentials build() {
      return new AuthorizationCodeCredentials(this);
    }
  }

  /**
   * JsonUtil class for building {@link AuthorizationCodeCredentials} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AuthorizationCodeCredentials> {

    public JsonUtil() {
      super();
    }

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorizationCodeCredentials that = (AuthorizationCodeCredentials) o;
    return Objects.equals(accessToken, that.accessToken) && Objects.equals(tokenType, that.tokenType) &&
      Objects.equals(scope, that.scope) && Objects.equals(expiresIn, that.expiresIn) &&
      Objects.equals(refreshToken, that.refreshToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken, tokenType, scope, expiresIn, refreshToken);
  }
}
