package se.michaelthelin.spotify.model_objects.credentials;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Objects;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/documentation/web-api/tutorials/code-flow">Authorization Code
 * Credentials</a> by building instances from this class.
 */
@JsonDeserialize(builder = AuthorizationCodeCredentials.Builder.class)
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

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * The access token setter.
     *
     * @param accessToken An access token that can be provided in subsequent calls,
     *                    for example to Spotify Web API services.
     * @return An {@link AuthorizationCodeCredentials.Builder}.
     */
    public Builder setAccessToken(final String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    /**
     * The access token type setter.
     *
     * @param tokenType How the access token may be used: always &quot;Bearer&quot;.
     * @return An {@link AuthorizationCodeCredentials.Builder}.
     */
    public Builder setTokenType(final String tokenType) {
      this.tokenType = tokenType;
      return this;
    }

    /**
     * The scopes setter.
     *
     * @param scope The scopes specified in the credentials request.
     * @return An {@link AuthorizationCodeCredentials.Builder}.
     */
    public Builder setScope(final String scope) {
      this.scope = scope;
      return this;
    }

    /**
     * The expiration time setter.
     *
     * @param expiresIn The time period (in seconds) for which the access token is valid.
     * @return An {@link AuthorizationCodeCredentials.Builder}.
     */
    public Builder setExpiresIn(final Integer expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    /**
     * The refresh token setter.
     *
     * @param refreshToken A token that can be sent to the Spotify Accounts service in place of an authorization code.
     * @return An {@link AuthorizationCodeCredentials.Builder}.
     */
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

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public AuthorizationCodeCredentials createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AuthorizationCodeCredentials.Builder()
        .setAccessToken(
          hasAndNotNull(jsonObject, "access_token")
            ? jsonObject.get("access_token").getAsString()
            : null)
        .setTokenType(
          hasAndNotNull(jsonObject, "token_type")
            ? jsonObject.get("token_type").getAsString()
            : null)
        .setScope(
          hasAndNotNull(jsonObject, "scope")
            ? jsonObject.get("scope").getAsString()
            : null)
        .setExpiresIn(
          hasAndNotNull(jsonObject, "expires_in")
            ? jsonObject.get("expires_in").getAsInt()
            : null)
        .setRefreshToken(
          hasAndNotNull(jsonObject, "refresh_token")
            ? jsonObject.get("refresh_token").getAsString()
            : null)
        .build();
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
