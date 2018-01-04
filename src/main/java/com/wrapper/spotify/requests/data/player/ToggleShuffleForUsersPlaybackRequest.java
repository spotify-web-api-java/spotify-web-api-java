package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class ToggleShuffleForUsersPlaybackRequest extends AbstractDataRequest {

  private ToggleShuffleForUsersPlaybackRequest(final Builder builder) {
    super(builder);
  }

  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return putJson();
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder state(final Boolean state) {
      return setQueryParameter("state", state);
    }

    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.equals(""));
      return setQueryParameter("device_id", device_id);
    }

    @Override
    public ToggleShuffleForUsersPlaybackRequest build() {
      setPath("/v1/me/player/shuffle");
      return new ToggleShuffleForUsersPlaybackRequest(this);
    }
  }
}
