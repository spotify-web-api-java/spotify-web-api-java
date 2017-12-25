package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.User;

import java.io.IOException;

public class CurrentUserRequest extends AbstractRequest {

  private CurrentUserRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public User get() throws
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
    return new User.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<User> getAsync() throws
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
    return getAsync(new User.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder accessToken(final String accessToken) {
      return setHeaderParameter("Authorization", "Bearer " + accessToken);
    }

    @Override
    public CurrentUserRequest build() {
      setPath("/v1/me");
      return new CurrentUserRequest(this);
    }
  }

}
