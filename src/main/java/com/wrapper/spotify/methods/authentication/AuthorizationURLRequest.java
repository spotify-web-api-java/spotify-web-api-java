package com.wrapper.spotify.methods.authentication;

import com.google.common.base.Joiner;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.AbstractRequest;

import java.util.List;

public class AuthorizationURLRequest extends AbstractRequest {

  public AuthorizationURLRequest(Builder builder) {
    super(builder);
  }

  public static AuthorizationURLRequest.Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder scopes(List<String> scopes) {
      return parameter("scope", Joiner.on(" ").join(scopes).toString());
    }

    public Builder state(String state) {
      return parameter("state", state);
    }

    public Builder responseType(String responseType) {
      return parameter("response_type", responseType);
    }

    public Builder clientId(String clientId) {
      return parameter("client_id", clientId);
    }

    public Builder redirectURI(String redirectURI) {
      return parameter("redirect_uri", redirectURI);
    }

    public Builder showDialog(boolean showDialog) {
      return parameter("show_dialog", String.valueOf(showDialog));
    }

    public AuthorizationURLRequest build() {
      host(Api.DEFAULT_AUTHENTICATION_HOST);
      port(Api.DEFAULT_AUTHENTICATION_PORT);
      scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);

      path("/authorize");
      return new AuthorizationURLRequest(this);
    }
  }

}
