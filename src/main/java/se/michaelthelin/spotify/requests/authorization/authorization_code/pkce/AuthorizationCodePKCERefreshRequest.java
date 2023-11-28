package se.michaelthelin.spotify.requests.authorization.authorization_code.pkce;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.AbstractRequest;

import java.io.IOException;

/**
 * Refresh your access token by creating an
 * <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#6-requesting-a-refreshed-access-token">
 * Authorization Code Refresh</a> request.
 */
@JsonDeserialize(builder = AuthorizationCodePKCERefreshRequest.Builder.class)
public class AuthorizationCodePKCERefreshRequest extends AbstractRequest<AuthorizationCodeCredentials> {

  private AuthorizationCodePKCERefreshRequest(Builder builder) {
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
   * Builder class for building an {@link AuthorizationCodePKCERefreshRequest}.
   */
  public static final class Builder extends AbstractRequest.Builder<AuthorizationCodeCredentials, Builder> {

    public Builder() {
      super();
    }

    /**
     * The grant type setter.
     *
     * @param grant_type Required. Set it to {@code "refresh_token"}
     * @return An {@link AuthorizationCodePKCERefreshRequest.Builder}.
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
     * @return An {@link AuthorizationCodePKCERefreshRequest.Builder}.
     */
    public Builder refresh_token(final String refresh_token) {
      assert (refresh_token != null);
      assert (!refresh_token.isEmpty());
      return setBodyParameter("refresh_token", refresh_token);
    }

    /**
     * The client ID setter.
     *
     * @param client_id Required. The client ID for your app, available from the developer dashboard.
     * @return An {@link AuthorizationCodePKCERefreshRequest.Builder}.
     */
    public Builder client_id(final String client_id) {
      assert (client_id != null);
      assert (!client_id.isEmpty());
      return setBodyParameter("client_id", client_id);
    }

    /**
     * The request build method.
     *
     * @return An {@link AuthorizationCodePKCERefreshRequest}.
     */
    public AuthorizationCodePKCERefreshRequest build() {
      setContentType(ContentType.APPLICATION_FORM_URLENCODED);
      setHost(SpotifyApi.DEFAULT_AUTHENTICATION_HOST);
      setPort(SpotifyApi.DEFAULT_AUTHENTICATION_PORT);
      setScheme(SpotifyApi.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/api/token");

      return new AuthorizationCodePKCERefreshRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
