package com.wrapper.spotify.requests.data.follow;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class CheckCurrentUserFollowsArtistsOrUsersRequest extends AbstractDataRequest {

  private CheckCurrentUserFollowsArtistsOrUsersRequest(final Builder builder) {
    super(builder);
  }

  public Boolean[] get() throws
          IOException,
          SpotifyWebApiException {
    return new Gson().fromJson(new JsonParser().parse(getJson()).getAsJsonArray(), Boolean[].class);
  }

  public SettableFuture<Boolean[]> getAsync() throws
          IOException,
          SpotifyWebApiException {
    return executeAsync(new Gson().fromJson(new JsonParser().parse(getJson()).getAsJsonArray(), Boolean[].class));
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

    @Override
    public CheckCurrentUserFollowsArtistsOrUsersRequest build() {
      setPath("/v1/me/following/contains");
      return new CheckCurrentUserFollowsArtistsOrUsersRequest(this);
    }
  }
}
