package com.wrapper.spotify.requests.data.users_profile;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetCurrentUsersProfileRequest extends AbstractDataRequest {

  private GetCurrentUsersProfileRequest(final Builder builder) {
    super(builder);
  }

  public User execute() throws
          IOException,
          SpotifyWebApiException {
    return new User.JsonUtil().createModelObject(getJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    @Override
    public GetCurrentUsersProfileRequest build() {
      setPath("/v1/me");
      return new GetCurrentUsersProfileRequest(this);
    }
  }
}
