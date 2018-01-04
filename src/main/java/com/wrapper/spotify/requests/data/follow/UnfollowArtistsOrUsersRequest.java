package com.wrapper.spotify.requests.data.follow;

import com.google.gson.JsonArray;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class UnfollowArtistsOrUsersRequest extends AbstractDataRequest {

  private UnfollowArtistsOrUsersRequest(final Builder builder) {
    super(builder);
  }

  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return deleteJson();
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
    public UnfollowArtistsOrUsersRequest build() {
      setPath("/v1/me/following");
      return new UnfollowArtistsOrUsersRequest(this);
    }
  }
}
