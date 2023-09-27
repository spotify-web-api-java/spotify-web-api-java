package se.michaelthelin.spotify.requests.authorization.authorization_code;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.AbstractAuthorizationRequest;

import java.io.IOException;

/**
 * Refresh your access token by creating an
 * <a href="https://developer.spotify.com/documentation/web-api/tutorials/code-flow">
 * Authorization Code Refresh</a> request.
 */
@JsonDeserialize(builder = AuthorizationCodeRefreshRequest.Builder.class)
public class AuthorizationCodeRefreshRequest extends AbstractAuthorizationRequest<AuthorizationCodeCredentials> {

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
  public AuthorizationCodeCredentials execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new AuthorizationCodeCredentials.JsonUtil().createModelObject(postJson());
  }

  /**
   * Builder class for building an {@link AuthorizationCodeRefreshRequest}.
   */
  public static final class Builder extends AbstractAuthorizationRequest.Builder<AuthorizationCodeCredentials, Builder> {

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

    @Override
    protected Builder self() {
      return this;
    }
  }
}
