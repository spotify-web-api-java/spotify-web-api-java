package com.wrapper.spotify.requests.authentication.authorization_code;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.requests.AbstractRequest;

public class AuthorizationCodeUriRequest extends AbstractRequest {

  private AuthorizationCodeUriRequest(Builder builder) {
    super(builder);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder() {
      super();
    }

    public Builder setClientId(final String client_id) {
      assert (client_id != null);
      assert (!client_id.equals(""));
      return setQueryParameter("client_id", client_id);
    }

    public Builder setResponseType(final String response_type) {
      assert (response_type != null);
      assert (!response_type.equals(""));
      return setQueryParameter("response_type", response_type);
    }

    public Builder setRedirectUri(final String redirect_uri) {
      assert (redirect_uri != null);
      assert (!redirect_uri.equals(""));
      return setQueryParameter("redirect_uri", redirect_uri);
    }

    public Builder setState(final String state) {
      assert (state != null);
      assert (!state.equals(""));
      return setQueryParameter("state", state);
    }

    public Builder setScope(final String scope) {
      assert (scope != null);
      assert (!scope.equals(""));
      return setQueryParameter("scope", scope);
    }

    public Builder setShowDialog(final boolean show_dialog) {
      return setQueryParameter("show_dialog", show_dialog);
    }

    public AuthorizationCodeUriRequest build() {
      setHost(Api.DEFAULT_AUTHENTICATION_HOST);
      setPort(Api.DEFAULT_AUTHENTICATION_PORT);
      setScheme(Api.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/authorize");

      return new AuthorizationCodeUriRequest(this);
    }
  }
}
