package com.wrapper.spotify.requests.authorization.client_credentials;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.AbstractAthorizationRequest;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * Request an access token by creating a
 * <a href="https://developer.spotify.com/web-api/authorization-guide/#client-credentials-flow">Client Credentials</a>
 * request.
 */
public class ClientCredentialsRequest extends AbstractAthorizationRequest {

  public ClientCredentialsRequest(Builder builder) {
    super(builder);
  }

  /**
   * Request an access token.
   *
   * @return A {@link ClientCredentials} object containing an access token.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public ClientCredentials execute() throws
          IOException,
          SpotifyWebApiException {
    return new ClientCredentials.JsonUtil().createModelObject(postJson());
  }

  /**
   * Builder class for building a {@link ClientCredentialsRequest}.
   */
  public static final class Builder extends AbstractAthorizationRequest.Builder<Builder> {

    public Builder(final String clientId, final String clientSecret) {
      super(clientId, clientSecret);
    }

    /**
     * The grant type setter.
     *
     * @param grant_type Required. Set it to {@code "client_credentials"}.
     * @return A {@link ClientCredentialsRequest.Builder}.
     */
    public Builder grant_type(final String grant_type) {
      assert (grant_type != null);
      assert (grant_type.equals("client_credentials"));
      return setBodyParameter("grant_type", grant_type);
    }

    /**
     * The request build method.
     *
     * @return A {@link ClientCredentialsRequest}.
     */
    public ClientCredentialsRequest build() {
      setContentType(ContentType.APPLICATION_FORM_URLENCODED);
      setHost(SpotifyApi.DEFAULT_AUTHENTICATION_HOST);
      setPort(SpotifyApi.DEFAULT_AUTHENTICATION_PORT);
      setScheme(SpotifyApi.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/api/token");

      return new ClientCredentialsRequest(this);
    }
  }
}
