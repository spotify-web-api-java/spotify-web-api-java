package com.wrapper.spotify.requests.data.player;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class SetRepeatModeOnUsersPlaybackRequest extends AbstractDataRequest {

  private SetRepeatModeOnUsersPlaybackRequest(final Builder builder) {
    super(builder);
  }

  public void put() throws
          IOException,
          SpotifyWebApiException {
    putJson();
  }

  public SettableFuture putAsync() throws
          IOException,
          SpotifyWebApiException {
    return executeAsync(putJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder state(final String state) {
      assert (state != null);
      assert (state.equals("track") || state.equals("context") || state.equals("off"));
      return setQueryParameter("state", state);
    }

    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.equals(""));
      return setQueryParameter("device_id", device_id);
    }

    @Override
    public SetRepeatModeOnUsersPlaybackRequest build() {
      setPath("/v1/me/player/repeat");
      return new SetRepeatModeOnUsersPlaybackRequest(this);
    }
  }
}
