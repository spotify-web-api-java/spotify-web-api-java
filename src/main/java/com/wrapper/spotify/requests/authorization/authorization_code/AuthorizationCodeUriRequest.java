package com.wrapper.spotify.requests.authorization.authorization_code;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.AbstractRequest;

import java.net.URI;

/**
 * Request an authorization code by creating an
 * <a href="https://developer.spotify.com/web-api/authorization-guide/#authorization_code_flow">Authorization Code
 * URI</a> request.
 */
public class AuthorizationCodeUriRequest extends AbstractRequest {

  private AuthorizationCodeUriRequest(Builder builder) {
    super(builder);
  }

  /**
   * Request an authorization URI.
   *
   * @return An authorization URI.
   */
  @SuppressWarnings("unchecked")
  public URI execute() {
    return this.getUri();
  }

  /**
   * Builder class for building an {@link AuthorizationCodeUriRequest}.
   */
  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder() {
      super();
    }

    /**
     * The client ID setter.
     *
     * @param client_id Required. The client ID provided to you by Spotify when you register your application.
     * @return An {@link AuthorizationCodeUriRequest.Builder}.
     */
    public Builder client_id(final String client_id) {
      assert (client_id != null);
      assert (!client_id.equals(""));
      return setQueryParameter("client_id", client_id);
    }

    /**
     * The response type setter.
     *
     * @param response_type Required. Set it to {@code code}.
     * @return An {@link AuthorizationCodeUriRequest.Builder}.
     */
    public Builder response_type(final String response_type) {
      assert (response_type != null);
      assert (!response_type.equals(""));
      return setQueryParameter("response_type", response_type);
    }

    /**
     * The redirect URI setter.
     *
     * @param redirect_uri Required. The URI to redirect to after the user grants/denies permission. This URI needs to
     *                     have been entered in the Redirect URI whitelist that you specified when you registered your
     *                     application. The value of {@code redirect_uri} here must exactly match one of the values you
     *                     entered when you registered your application, including upper/lowercase, terminating slashes,
     *                     etc.
     * @return An {@link AuthorizationCodeUriRequest.Builder}.
     */
    public Builder redirect_uri(final URI redirect_uri) {
      assert (redirect_uri != null);
      return setQueryParameter("redirect_uri", redirect_uri.toString());
    }

    /**
     * The state setter.
     *
     * @param state Optional, but strongly recommended. The state can be useful for correlating requests and responses.
     *              Because your {@code redirect_uri} can be guessed, using a state value can increase your assurance
     *              that an incoming connection is the result of an authentication request. If you generate a random
     *              string or encode the hash of some client state (e.g., a cookie) in this state variable, you can
     *              validate the response to additionally ensure that the request and response originated in the same
     *              browser. This provides protection against attacks such as cross-site request forgery.
     * @return An {@link AuthorizationCodeUriRequest.Builder}.
     * @see <a href="https://tools.ietf.org/html/rfc6749#section-10.12">RFC 6749: Cross-Site Request Forgery</a>
     */
    public Builder state(final String state) {
      assert (state != null);
      assert (!state.equals(""));
      return setQueryParameter("state", state);
    }

    /**
     * The scope setter.
     *
     * @param scope Optional. A space-separated list of scopes. If no scopes are specified, authorization will be
     *              granted only to access publicly available information: that is, only information normally visible in
     *              the Spotify desktop, web and mobile players.
     * @return An {@link AuthorizationCodeUriRequest.Builder}.
     * @see <a href="https://developer.spotify.com/spotify-web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder scope(final String scope) {
      assert (scope != null);
      assert (!scope.equals(""));
      return setQueryParameter("scope", scope);
    }

    /**
     * The show dialog setter.
     *
     * @param show_dialog Optional. Whether or not to force the user to approve the app again if they’ve already done
     *                    so. If {@code false} (default), a user who has already approved the application may be
     *                    automatically redirected to the URI specified by {@code redirect_uri}. If {@code true}, the
     *                    user will not be automatically redirected and will have to approve the app again.
     * @return An {@link AuthorizationCodeUriRequest.Builder}.
     */
    public Builder show_dialog(final boolean show_dialog) {
      return setQueryParameter("show_dialog", show_dialog);
    }

    /**
     * The request build method.
     *
     * @return An {@link AuthorizationCodeUriRequest}.
     */
    public AuthorizationCodeUriRequest build() {
      setHost(SpotifyApi.DEFAULT_AUTHENTICATION_HOST);
      setPort(SpotifyApi.DEFAULT_AUTHENTICATION_PORT);
      setScheme(SpotifyApi.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/authorize");

      return new AuthorizationCodeUriRequest(this);
    }
  }
}
