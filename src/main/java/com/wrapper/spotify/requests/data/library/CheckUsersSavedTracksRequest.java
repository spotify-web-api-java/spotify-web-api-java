package com.wrapper.spotify.requests.data.library;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class CheckUsersSavedTracksRequest extends AbstractDataRequest {

  private CheckUsersSavedTracksRequest(final Builder builder) {
    super(builder);
  }

  public Boolean[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new Gson().fromJson(new JsonParser().parse(getJson()).getAsJsonArray(), Boolean[].class);
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setFormParameter("ids", ids);
    }

    @Override
    public CheckUsersSavedTracksRequest build() {
      setPath("/v1/me/tracks/contains");
      return new CheckUsersSavedTracksRequest(this);
    }
  }
}
