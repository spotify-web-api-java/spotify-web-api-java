package com.wrapper.spotify.requests.authorization.client_credentials;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.AbstractAthorizationRequest;

import java.io.IOException;

public class ClientCredentialsRequest extends AbstractAthorizationRequest {

  public ClientCredentialsRequest(Builder builder) {
    super(builder);
  }

  public ClientCredentials execute() throws
          IOException,
          SpotifyWebApiException {
    return new ClientCredentials.JsonUtil().createModelObject(postJson());
  }

  public static final class Builder extends AbstractAthorizationRequest.Builder<Builder> {

    public Builder(final String clientId, final String clientSecret) {
      super(clientId, clientSecret);
    }

    public Builder grant_type(final String grant_type) {
      assert (grant_type != null);
      assert (grant_type.equals("client_credentials"));
      return setBodyParameter("grant_type", grant_type);
    }

    public ClientCredentialsRequest build() {
      setHeader("Content-Type", "application/x-www-form-urlencoded");
      setHost(SpotifyApi.DEFAULT_AUTHENTICATION_HOST);
      setPort(SpotifyApi.DEFAULT_AUTHENTICATION_PORT);
      setScheme(SpotifyApi.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/api/token");

      return new ClientCredentialsRequest(this);
    }
  }
}
