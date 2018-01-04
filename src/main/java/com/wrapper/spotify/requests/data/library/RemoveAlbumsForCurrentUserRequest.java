package com.wrapper.spotify.requests.data.library;

import com.google.gson.JsonArray;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class RemoveAlbumsForCurrentUserRequest extends AbstractDataRequest {

  private RemoveAlbumsForCurrentUserRequest(final Builder builder) {
    super(builder);
  }

  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return deleteJson();
  }

  public static class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
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
    public RemoveAlbumsForCurrentUserRequest build() {
      setPath("/v1/me/albums");
      return new RemoveAlbumsForCurrentUserRequest(this);
    }
  }
}
