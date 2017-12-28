package com.wrapper.spotify.model_objects;

import com.google.gson.JsonObject;

/**
 * Retrieve information about client credentials by building instances from this class.
 */
public class ClientCredentials extends AbstractModelObject {
  private final String accessToken;
  private final String tokenType;
  private final int expiresIn;

  private ClientCredentials(final ClientCredentials.Builder builder) {
    super(builder);

    this.accessToken = builder.accessToken;
    this.tokenType = builder.tokenType;
    this.expiresIn = builder.expiresIn;
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
  public int getExpiresIn() {
    return expiresIn;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building client credential instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String accessToken;
    private String tokenType;
    private int expiresIn;

    /**
     * The access token setter.
     *
     * @param accessToken An access token that can be provided in subsequent calls,
     *                    for example to Spotify Web API services.
     * @return A ClientCredentials builder.
     */
    public Builder setAccessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    /**
     * The access token type setter.
     *
     * @param tokenType How the access token may be used: always &quot;Bearer&quot;.
     * @return A ClientCredentials builder.
     */
    public Builder setTokenType(String tokenType) {
      this.tokenType = tokenType;
      return this;
    }

    /**
     * The expiration time setter.
     *
     * @param expiresIn The time period (in seconds) for which the access token is valid.
     * @return A ClientCredentials builder.
     */
    public Builder setExpiresIn(int expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    @Override
    public ClientCredentials build() {
      return new ClientCredentials(this);
    }
  }

  /**
   * JsonUtil class for building client credential instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ClientCredentials> {
    public ClientCredentials createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new ClientCredentials.Builder()
              .setAccessToken(jsonObject.get("access_token").getAsString())
              .setTokenType(jsonObject.get("token_type").getAsString())
              .setExpiresIn(jsonObject.get("expires_in").getAsInt())
              .build();
    }
  }
}
