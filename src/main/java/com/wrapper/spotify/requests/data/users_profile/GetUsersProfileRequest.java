package com.wrapper.spotify.requests.data.users_profile;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersProfileRequest extends AbstractDataRequest {

  private GetUsersProfileRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public User execute() throws
          IOException,
          SpotifyWebApiException {
    return new User.JsonUtil().createModelObject(getJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    @Override
    public GetUsersProfileRequest build() {
      setPath("/v1/users/{user_id}");
      return new GetUsersProfileRequest(this);
    }
  }
}
