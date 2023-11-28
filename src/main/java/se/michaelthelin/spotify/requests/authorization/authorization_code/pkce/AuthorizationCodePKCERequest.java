package se.michaelthelin.spotify.requests.authorization.authorization_code.pkce;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.AbstractRequest;

import java.io.IOException;
import java.net.URI;

/**
 * Request an access token by exchanging the authorization code for an access token with an
 * <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#4-your-app-exchanges-the-code-for-an-access-token">Authorization Code</a>
 * request.
 */
@JsonDeserialize(builder = AuthorizationCodePKCERequest.Builder.class)
public class AuthorizationCodePKCERequest extends AbstractRequest<AuthorizationCodeCredentials> {

  private AuthorizationCodePKCERequest(Builder builder) {
    super(builder);
  }

  /**
   * Request new {@link AuthorizationCodeCredentials}.
   *
   * @return An {@link AuthorizationCodeCredentials} object containing an access token and refresh token.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public AuthorizationCodeCredentials execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new AuthorizationCodeCredentials.JsonUtil().createModelObject(postJson());
  }

  public static final class Builder extends AbstractRequest.Builder<AuthorizationCodeCredentials, Builder> {

    public Builder() {
      super();
    }

    /**
     * The client ID setter.
     *
     * @param client_id Required. The client ID for your app, available from the developer dashboard.
     * @return An {@link AuthorizationCodePKCERequest.Builder}.
     */
    public Builder client_id(final String client_id) {
      assert (client_id != null);
      assert (!client_id.isEmpty());
      return setBodyParameter("client_id", client_id);
    }

    /**
     * The grant type setter.
     *
     * @param grant_type Required. As defined in the OAuth 2.0 specification, this field must contain the value
     *                   {@code "authorization_code"}
     * @return An {@link AuthorizationCodePKCERequest.Builder}.
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
     * @return An {@link AuthorizationCodePKCERequest.Builder}.
     */
    public Builder code(final String code) {
      assert (code != null);
      assert (!code.isEmpty());
      return setBodyParameter("code", code);
    }

    /**
     * The redirect URI setter.
     *
     * @param redirect_uri Required. This parameter is used for validation only (there is no actual redirection). The
     *                     value of this parameter must exactly match the value of {@code redirect_uri} supplied when
     *                     requesting the authorization code.
     * @return An {@link AuthorizationCodePKCERequest.Builder}.
     */
    public Builder redirect_uri(final URI redirect_uri) {
      assert (redirect_uri != null);
      return setBodyParameter("redirect_uri", redirect_uri.toString());
    }

    /**
     * The code verifier setter.
     *
     * @param code_verifier Required. The value of this parameter must match the value of the code_verifier that your app generated beforehand.
     * @return An {@link AuthorizationCodePKCERequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#authorization-code-flow-with-proof-key-for-code-exchange-pkce">
     *      Authorization Code Flow with Proof Key for Code Exchange (PKCE)</a>
     */
    public Builder code_verifier(String code_verifier) {
      assert (code_verifier != null);
      assert (!code_verifier.isEmpty());
      return setBodyParameter("code_verifier", code_verifier);
    }

    /**
     * The request build method.
     *
     * @return An {@link AuthorizationCodePKCERequest}.
     */
    public AuthorizationCodePKCERequest build() {
      setContentType(ContentType.APPLICATION_FORM_URLENCODED);
      setHost(SpotifyApi.DEFAULT_AUTHENTICATION_HOST);
      setPort(SpotifyApi.DEFAULT_AUTHENTICATION_PORT);
      setScheme(SpotifyApi.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/api/token");

      return new AuthorizationCodePKCERequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }

}
