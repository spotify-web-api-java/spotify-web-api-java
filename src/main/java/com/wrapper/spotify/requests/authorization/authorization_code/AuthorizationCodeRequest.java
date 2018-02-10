package com.wrapper.spotify.requests.authorization.authorization_code;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.AbstractAthorizationRequest;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.net.URI;

/**
 * Request an access token and refresh token by creating an
 * <a href="https://developer.spotify.com/web-api/authorization-guide/#authorization_code_flow">Authorization Code</a>
 * request.
 */
public class AuthorizationCodeRequest extends AbstractAthorizationRequest {

  private AuthorizationCodeRequest(Builder builder) {
    super(builder);
  }

  /**
   * Request new {@link AuthorizationCodeCredentials}.
   *
   * @return An {@link AuthorizationCodeCredentials} object containing an access token and refresh token.
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
   * Builder class for building an {@link AuthorizationCodeRequest}.
   */
  public static final class Builder extends AbstractAthorizationRequest.Builder<Builder> {

    public Builder(final String clientId, final String clientSecret) {
      super(clientId, clientSecret);
    }

    /**
     * The grant type setter.
     *
     * @param grant_type Required. As defined in the OAuth 2.0 specification, this field must contain the value
     *                   {@code "authorization_code"}
     * @return An {@link AuthorizationCodeRequest.Builder}.
     */
    public Builder grant_type(final String grant_type) {
      assert (grant_type != null);
      assert (grant_type.equals("authorization_code"));
      return setBodyParameter("grant_type", grant_type);
    }

    /**
     * The authorization code setter.
     *
     * @param code Required. The authorization code returned from the initial request to the Account's /authorize
     *             endpoint.
     * @return An {@link AuthorizationCodeRequest.Builder}.
     */
    public Builder code(final String code) {
      assert (code != null);
      assert (!code.equals(""));
      return setBodyParameter("code", code);
    }

    /**
     * The redirect URI setter.
     *
     * @param redirect_uri Required. This parameter is used for validation only (there is no actual redirection). The
     *                     value of this parameter must exactly match the value of {@code redirect_uri} supplied when
     *                     requesting the authorization code.
     * @return An {@link AuthorizationCodeRequest.Builder}.
     */
    public Builder redirect_uri(final URI redirect_uri) {
      assert (redirect_uri != null);
      return setBodyParameter("redirect_uri", redirect_uri.toString());
    }

    /**
     * The request build method.
     *
     * @return An {@link AuthorizationCodeRequest}.
     */
    public AuthorizationCodeRequest build() {
      setContentType(ContentType.APPLICATION_FORM_URLENCODED);
      setHost(SpotifyApi.DEFAULT_AUTHENTICATION_HOST);
      setPort(SpotifyApi.DEFAULT_AUTHENTICATION_PORT);
      setScheme(SpotifyApi.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/api/token");

      return new AuthorizationCodeRequest(this);
    }
  }
}
