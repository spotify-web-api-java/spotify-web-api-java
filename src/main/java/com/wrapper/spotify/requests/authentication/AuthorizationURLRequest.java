package com.wrapper.spotify.requests.authentication;

import com.google.common.base.Joiner;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.requests.AbstractRequest;

public class AuthorizationURLRequest extends AbstractRequest {

  public AuthorizationURLRequest(Builder builder) {
    super(builder);
  }

  public static AuthorizationURLRequest.Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder scopes(String[] scopes) {
      return setParameter("scope", Joiner.on(" ").join(scopes));
    }

    public Builder state(String state) {
      return setParameter("state", state);
    }

    public Builder responseType(String responseType) {
      return setParameter("response_type", responseType);
    }

    public Builder clientId(String clientId) {
      return setParameter("client_id", clientId);
    }

    public Builder redirectURI(String redirectURI) {
      return setParameter("redirect_uri", redirectURI);
    }

    public Builder showDialog(boolean showDialog) {
      return setParameter("show_dialog", String.valueOf(showDialog));
    }

    public AuthorizationURLRequest build() {
      setHost(Api.DEFAULT_AUTHENTICATION_HOST);
      setPort(Api.DEFAULT_AUTHENTICATION_PORT);
      setScheme(Api.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/authorize");

      return new AuthorizationURLRequest(this);
    }
  }

}
