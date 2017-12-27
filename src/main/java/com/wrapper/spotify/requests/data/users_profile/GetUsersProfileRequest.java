package com.wrapper.spotify.requests.data.users_profile;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.User;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetUsersProfileRequest extends AbstractRequest {

  private GetUsersProfileRequest(final Builder builder) {
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
    return executeAsync(new User.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

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
