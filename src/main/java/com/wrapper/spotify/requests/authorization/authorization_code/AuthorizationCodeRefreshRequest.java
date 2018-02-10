package com.wrapper.spotify.requests.authorization.authorization_code;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.AbstractAthorizationRequest;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * Refresh your access token by creating an
 * <a href="https://developer.spotify.com/web-api/authorization-guide/#request-access-token-from-refresh-token">
 * Authorization Code Refresh</a> request.
 */
public class AuthorizationCodeRefreshRequest extends AbstractAthorizationRequest {

  private AuthorizationCodeRefreshRequest(Builder builder) {
    super(builder);
  }

  /**
   * Request new {@link AuthorizationCodeCredentials}.
   *
   * @return An {@link AuthorizationCodeCredentials} object containing a new/refreshed access code.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public AuthorizationCodeCredentials execute() throws
          IOException,
          SpotifyWebApiException {
    return new AuthorizationCodeCredentials.JsonUtil().createModelObject(postJson());
  }

  /**
   * Builder class for building an {@link AuthorizationCodeRefreshRequest}.
   */
  public static final class Builder extends AbstractAthorizationRequest.Builder<Builder> {

    public Builder(final String clientId, final String clientSecret) {
      super(clientId, clientSecret);
    }

    /**
     * The grant type setter.
     *
     * @param grant_type Required. Set it to {@code "refresh_token"}
     * @return An {@link AuthorizationCodeRefreshRequest.Builder}.
     */
    public Builder grant_type(final String grant_type) {
      assert (grant_type != null);
      assert (grant_type.equals("refresh_token"));
      return setBodyParameter("grant_type", grant_type);
    }

    /**
     * The refresh token setter.
     *
     * @param refresh_token Required. The refresh token returned from the authorization code exchange.
     * @return An {@link AuthorizationCodeRefreshRequest.Builder}.
     */
    public Builder refresh_token(final String refresh_token) {
      assert (refresh_token != null);
      assert (!refresh_token.equals(""));
      return setBodyParameter("refresh_token", refresh_token);
    }

    /**
     * The request build method.
     *
     * @return An {@link AuthorizationCodeRefreshRequest}.
     */
    public AuthorizationCodeRefreshRequest build() {
      setContentType(ContentType.APPLICATION_FORM_URLENCODED);
      setHost(SpotifyApi.DEFAULT_AUTHENTICATION_HOST);
      setPort(SpotifyApi.DEFAULT_AUTHENTICATION_PORT);
      setScheme(SpotifyApi.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/api/token");

      return new AuthorizationCodeRefreshRequest(this);
    }
  }
}
