package com.wrapper.spotify.requests.authentication;

import com.google.common.base.Joiner;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.requests.AbstractRequest;

public class AuthorizationUriRequest extends AbstractRequest {

  public AuthorizationUriRequest(Builder builder) {
    super(builder);
  }

  public static AuthorizationUriRequest.Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder scopes(String... scopes) {
      return setQueryParameter("scope", Joiner.on(" ").join(scopes));
    }

    public Builder state(String state) {
      return setQueryParameter("state", state);
    }

    public Builder responseType(String responseType) {
      return setQueryParameter("response_type", responseType);
    }

    public Builder clientId(String clientId) {
      return setQueryParameter("client_id", clientId);
    }

    public Builder redirectURI(String redirectURI) {
      return setQueryParameter("redirect_uri", redirectURI);
    }

    public Builder showDialog(boolean showDialog) {
      return setQueryParameter("show_dialog", String.valueOf(showDialog));
    }

    public AuthorizationUriRequest build() {
      setHost(Api.DEFAULT_AUTHENTICATION_HOST);
      setPort(Api.DEFAULT_AUTHENTICATION_PORT);
      setScheme(Api.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/authorize");

      return new AuthorizationUriRequest(this);
    }
  }
}
