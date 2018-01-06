package com.wrapper.spotify.requests.authorization.authorization_code;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.AbstractAthorizationRequest;

import java.io.IOException;

public class AuthorizationCodeRefreshRequest extends AbstractAthorizationRequest {

  private AuthorizationCodeRefreshRequest(Builder builder) {
    super(builder);
  }

  public AuthorizationCodeCredentials execute() throws
          IOException,
          SpotifyWebApiException {
    return new AuthorizationCodeCredentials.JsonUtil().createModelObject(postJson());
  }

  public static final class Builder extends AbstractAthorizationRequest.Builder<Builder> {

    public Builder(final String clientId, final String clientSecret) {
      super(clientId, clientSecret);
    }

    public Builder grant_type(final String grant_type) {
      assert (grant_type != null);
      assert (grant_type.equals("refresh_token"));
      return setFormParameter("grant_type", grant_type);
    }

    public Builder refresh_token(final String refresh_token) {
      assert (refresh_token != null);
      assert (!refresh_token.equals(""));
      return setFormParameter("refresh_token", refresh_token);
    }

    public AuthorizationCodeRefreshRequest build() {
      setHeader("Content-Type", "application/x-www-form-urlencoded");
      setHost(SpotifyApi.DEFAULT_AUTHENTICATION_HOST);
      setPort(SpotifyApi.DEFAULT_AUTHENTICATION_PORT);
      setScheme(SpotifyApi.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/api/token");

      return new AuthorizationCodeRefreshRequest(this);
    }
  }
}
