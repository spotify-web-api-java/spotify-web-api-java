package com.wrapper.spotify.requests.authorization.authorization_code;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class AuthorizationCodeUriRequest extends AbstractRequest {

  private AuthorizationCodeUriRequest(Builder builder) {
    super(builder);
  }

  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return getJson();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder() {
      super();
    }

    public Builder client_id(final String client_id) {
      assert (client_id != null);
      assert (!client_id.equals(""));
      return setQueryParameter("client_id", client_id);
    }

    public Builder response_type(final String response_type) {
      assert (response_type != null);
      assert (!response_type.equals(""));
      return setQueryParameter("response_type", response_type);
    }

    public Builder redirect_uri(final String redirect_uri) {
      assert (redirect_uri != null);
      assert (!redirect_uri.equals(""));
      return setQueryParameter("redirect_uri", redirect_uri);
    }

    public Builder state(final String state) {
      assert (state != null);
      assert (!state.equals(""));
      return setQueryParameter("state", state);
    }

    public Builder scope(final String scope) {
      assert (scope != null);
      assert (!scope.equals(""));
      return setQueryParameter("scope", scope);
    }

    public Builder show_dialog(final boolean show_dialog) {
      return setQueryParameter("show_dialog", show_dialog);
    }

    public AuthorizationCodeUriRequest build() {
      setHost(SpotifyApi.DEFAULT_AUTHENTICATION_HOST);
      setPort(SpotifyApi.DEFAULT_AUTHENTICATION_PORT);
      setScheme(SpotifyApi.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/authorize");

      return new AuthorizationCodeUriRequest(this);
    }
  }
}
