package se.michaelthelin.spotify.model_objects.credentials;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/authorization-guide/#implicit_grant_flow">
 * Client Credentials</a> by building instances from this class.
 */
@JsonDeserialize(builder = ClientCredentials.Builder.class)
public class ClientCredentials extends AbstractModelObject {
  private final String accessToken;
  private final String tokenType;
  private final Integer expiresIn;

  private ClientCredentials(final Builder builder) {
    super(builder);

    this.accessToken = builder.accessToken;
    this.tokenType = builder.tokenType;
    this.expiresIn = builder.expiresIn;
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
   * Get the time period (in seconds) for which the access token is valid.
   *
   * @return The time period (in seconds) for which the access token is valid.
   */
  public Integer getExpiresIn() {
    return expiresIn;
  }

  @Override
  public String toString() {
    return "ClientCredentials(accessToken=" + accessToken + ", tokenType=" + tokenType + ", expiresIn=" + expiresIn
        + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link ClientCredentials} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String accessToken;
    private String tokenType;
    private Integer expiresIn;

    /**
     * The access token setter.
     *
     * @param accessToken An access token that can be provided in subsequent calls,
     *                    for example to Spotify Web API services.
     * @return A {@link ClientCredentials.Builder}.
     */
    public Builder setAccessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    /**
     * The access token type setter.
     *
     * @param tokenType How the access token may be used: always &quot;Bearer&quot;.
     * @return A {@link ClientCredentials.Builder}.
     */
    public Builder setTokenType(String tokenType) {
      this.tokenType = tokenType;
      return this;
    }

    /**
     * The expiration time setter.
     *
     * @param expiresIn The time period (in seconds) for which the access token is valid.
     * @return A {@link ClientCredentials.Builder}.
     */
    public Builder setExpiresIn(Integer expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    @Override
    public ClientCredentials build() {
      return new ClientCredentials(this);
    }
  }

  /**
   * JsonUtil class for building {@link ClientCredentials} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ClientCredentials> {
    public ClientCredentials createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new ClientCredentials.Builder()
        .setAccessToken(
          hasAndNotNull(jsonObject, "access_token")
            ? jsonObject.get("access_token").getAsString()
            : null)
        .setTokenType(
          hasAndNotNull(jsonObject, "token_type")
            ? jsonObject.get("token_type").getAsString()
            : null)
        .setExpiresIn(
          hasAndNotNull(jsonObject, "expires_in")
            ? jsonObject.get("expires_in").getAsInt()
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
    ClientCredentials that = (ClientCredentials) o;
    return Objects.equals(accessToken, that.accessToken) && Objects.equals(tokenType, that.tokenType) &&
      Objects.equals(expiresIn, that.expiresIn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken, tokenType, expiresIn);
  }
}
