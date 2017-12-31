package com.wrapper.spotify.requests.authentication.authorization_code;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authentication.AbstractAthenticationRequest;

import java.io.IOException;

public class AuthorizationCodeRequest extends AbstractAthenticationRequest {

  private AuthorizationCodeRequest(Builder builder) {
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

  public static final class Builder extends AbstractAthenticationRequest.Builder<Builder> {

    public Builder(final String clientId, final String clientSecret) {
      super(clientId, clientSecret);
    }

    public Builder setGrantType(final String grant_type) {
      assert (grant_type != null);
      assert (grant_type.equals("authorization_code"));
      return setBodyParameter("grant_type", grant_type);
    }

    public Builder setCode(final String code) {
      assert (code != null);
      assert (!code.equals(""));
      return setBodyParameter("code", code);
    }

    public Builder setRedirectUri(final String redirect_uri) {
      assert (redirect_uri != null);
      assert (!redirect_uri.equals(""));
      return setBodyParameter("redirect_uri", redirect_uri);
    }

    public AuthorizationCodeRequest build() {
      setHost(Api.DEFAULT_AUTHENTICATION_HOST);
      setPort(Api.DEFAULT_AUTHENTICATION_PORT);
      setScheme(Api.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/api/token");

      return new AuthorizationCodeRequest(this);
    }
  }
}
