package com.wrapper.spotify.requests.authorization.authorization_code;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.AbstractAthorizationRequest;

import java.io.IOException;

public class AuthorizationCodeRefreshRequest extends AbstractAthorizationRequest {

  private AuthorizationCodeRefreshRequest(Builder builder) {
    super(builder);
  }

  public AuthorizationCodeCredentials post() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new AuthorizationCodeCredentials.JsonUtil().createModelObject(postJson());
  }

  public SettableFuture<AuthorizationCodeCredentials> postAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return executeAsync(new AuthorizationCodeCredentials.JsonUtil().createModelObject(postJson()));
  }

  public static final class Builder extends AbstractAthorizationRequest.Builder<Builder> {

    public Builder(final String clientId, final String clientSecret) {
      super(clientId, clientSecret);
    }

    public Builder setGrantType(final String grant_type) {
      assert (grant_type != null);
      assert (grant_type.equals("refresh_token"));
      return setBodyParameter("grant_type", grant_type);
    }

    public Builder setRefreshToken(final String refresh_token) {
      assert (refresh_token != null);
      assert (!refresh_token.equals(""));
      return setBodyParameter("refresh_token", refresh_token);
    }

    public AuthorizationCodeRefreshRequest build() {
      setHost(Api.DEFAULT_AUTHENTICATION_HOST);
      setPort(Api.DEFAULT_AUTHENTICATION_PORT);
      setScheme(Api.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/api/token");

      return new AuthorizationCodeRefreshRequest(this);
    }
  }
}
