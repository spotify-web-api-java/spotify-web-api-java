package se.michaelthelin.spotify.requests.authorization.client_credentials;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.AbstractAuthorizationRequest;

import java.io.IOException;

/**
 * Request an access token by creating a
 * <a href="https://developer.spotify.com/documentation/web-api/tutorials/client-credentials-flow">Client Credentials</a>
 * request.
 */
@JsonDeserialize(builder = ClientCredentialsRequest.Builder.class)
public class ClientCredentialsRequest extends AbstractAuthorizationRequest<ClientCredentials> {

  /**
   * Creates a new client credentials request with the specified builder.
   *
   * @param builder the builder containing request configuration
   */
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
  public ClientCredentials execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new ClientCredentials.JsonUtil().createModelObject(postJson());
  }

  /**
   * Builder class for building a {@link ClientCredentialsRequest}.
   */
  public static final class Builder extends AbstractAuthorizationRequest.Builder<ClientCredentials, Builder> {

    /**
     * Creates a new builder for a client credentials request.
     *
     * @param clientId the client ID of the Spotify application
     * @param clientSecret the client secret of the Spotify application
     */
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

    @Override
    protected Builder self() {
      return this;
    }
  }
}
