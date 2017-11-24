package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.User;
import net.sf.json.JSONObject;

import java.io.IOException;

public class CurrentUserRequest extends AbstractRequest {

  public CurrentUserRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<User> getAsync() {
    final SettableFuture<User> userFuture = SettableFuture.create();

    try {
      final JSONObject jsonObject = JSONObject.fromObject(getJson());
      userFuture.set(JsonUtil.createUser(jsonObject));
    } catch (Exception e) {
      userFuture.setException(e);
    }

    return userFuture;
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
    JSONObject jsonObject = JSONObject.fromObject(getJson());
    return JsonUtil.createUser(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder accessToken(String accessToken) {
      return setHeaderParameter("Authorization", "Bearer " + accessToken);
    }

    public CurrentUserRequest build() {
      setPath("/v1/me");
      return new CurrentUserRequest(this);
    }
  }

}
