package com.wrapper.spotify.requests.data.users_profile;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.User;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersProfileRequest extends AbstractDataRequest {

  private GetUsersProfileRequest(final Builder builder) {
    super(builder);
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
    return executeAsync(new User.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder username(final String username) {
      assert (username != null);
      return setPath(String.format("/v1/users/%s", username));
    }

    @Override
    public GetUsersProfileRequest build() {
      return new GetUsersProfileRequest(this);
    }

  }
}
