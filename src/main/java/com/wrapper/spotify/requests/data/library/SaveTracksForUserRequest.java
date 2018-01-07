package com.wrapper.spotify.requests.data.library;

import com.google.gson.JsonArray;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class SaveTracksForUserRequest extends AbstractDataRequest {

  private SaveTracksForUserRequest(final Builder builder) {
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
    public SaveTracksForUserRequest build() {
      setHeader("Content-Type", "application/json");
      setPath("/v1/me/tracks");
      return new SaveTracksForUserRequest(this);
    }
  }
}
