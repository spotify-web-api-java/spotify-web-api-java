package com.wrapper.spotify.requests.data.follow;

import com.google.gson.JsonArray;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class FollowArtistsOrUsersRequest extends AbstractDataRequest {

  private FollowArtistsOrUsersRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return putJson();
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder type(final ModelObjectType type) {
      assert (type != null);
      assert (type.getType().equals("artist") || type.getType().equals("user"));
      return setQueryParameter("type", type);
    }

    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    public Builder ids(final JsonArray ids) {
      assert (ids != null);
      assert (!ids.isJsonNull());
      return setBodyParameter("ids", ids);
    }

    @Override
    public FollowArtistsOrUsersRequest build() {
      setPath("/v1/me/following");
      return new FollowArtistsOrUsersRequest(this);
    }
  }
}
