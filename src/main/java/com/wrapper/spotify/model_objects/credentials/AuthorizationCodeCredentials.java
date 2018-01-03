package com.wrapper.spotify.model_objects.credentials;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about authorization code credentials by building instances from this class.
 */
public class AuthorizationCodeCredentials extends AbstractModelObject {
  private final String accessToken;
  private final String tokenType;
  private final String scope;
  private final int expiresIn;
  private final String refreshToken;

  private AuthorizationCodeCredentials(final AuthorizationCodeCredentials.Builder builder) {
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
   * @return An access token that can be provided in subsequent calls, for example
   * to Spotify Web API services.
   */
  public String getAccessToken() {
    return accessToken;
  }

  /**
   * Get the type of an access token, which will alway be "Bearer".
   *
   * @return How the access token may be used: always &quot;Bearer&quot;.
   */
  public String getTokenType() {
    return tokenType;
  }

  /**
   * Get the time period (in seconds) for which the access token is valid.
   *
   * @return The time period (in seconds) for which the access token is valid.
   */

  public String getScope() {
    return scope;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  /**
   * Get the refresh token. This token can be sent to the Spotify Accounts service in
   * place of an authorization code to retrieve a new access token.
   *
   * @return A token that can be sent to the Spotify Accounts service in place
   * of an access token.
   */
  public String getRefreshToken() {
    return refreshToken;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building authorization code credential instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String accessToken;
    private String tokenType;
    private String scope;
    private int expiresIn;
    private String refreshToken;

    /**
     * The access token setter.
     *
     * @param accessToken An access token that can be provided in subsequent calls,
     *                    for example to Spotify Web API services.
     * @return An AuthorizationCodeCredentials builder.
     */
    public Builder setAccessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    /**
     * The access token type setter.
     *
     * @param tokenType How the access token may be used: always &quot;Bearer&quot;.
     * @return An AuthorizationCodeCredentials builder.
     */
    public Builder setTokenType(String tokenType) {
      this.tokenType = tokenType;
      return this;
    }

    /**
     * The expiration time setter.
     *
     * @param expiresIn The time period (in seconds) for which the access token is valid.
     * @return An AuthorizationCodeCredentials builder.
     */

    public Builder setScope(String scope) {
      this.scope = scope;
      return this;
    }

    public Builder setExpiresIn(int expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    /**
     * The refresh token setter.
     *
     * @param refreshToken A token that can be sent to the Spotify Accounts service in
     *                     place of an authorization code.
     * @return An AuthorizationCodeCredentials builder.
     */
    public Builder setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    @Override
    public AuthorizationCodeCredentials build() {
      return new AuthorizationCodeCredentials(this);
    }
  }

  /**
   * JsonUtil class for building authorization code credential instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AuthorizationCodeCredentials> {
    public AuthorizationCodeCredentials createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AuthorizationCodeCredentials.Builder()
              .setAccessToken(jsonObject.get("access_token").getAsString())
              .setTokenType(jsonObject.get("token_type").getAsString())
              .setScope(jsonObject.get("scope").getAsString())
              .setExpiresIn(jsonObject.get("expires_in").getAsInt())
              .setRefreshToken(jsonObject.get("refresh_token").getAsString())
              .build();
    }
  }
}
