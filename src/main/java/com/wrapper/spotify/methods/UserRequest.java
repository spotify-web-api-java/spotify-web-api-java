package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.User;

import java.io.IOException;

public class UserRequest extends AbstractRequest {

  public UserRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<User> getAsync() {
    final SettableFuture<User> userFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      userFuture.set(JsonUtil.createUser(jsonString));
    } catch (Exception e) {
      userFuture.setException(e);
    }

    return userFuture;
  }

  public User get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    return JsonUtil.createUser(jsonString);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder username(String username) {
      assert (username!= null);
      return path(String.format("/v1/users/%s", username));
    }

    public UserRequest build() {
      return new UserRequest(this);
    }

  }
}
