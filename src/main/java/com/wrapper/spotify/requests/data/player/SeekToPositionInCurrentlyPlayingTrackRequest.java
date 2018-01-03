package com.wrapper.spotify.requests.data.player;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class SeekToPositionInCurrentlyPlayingTrackRequest extends AbstractDataRequest {

  private SeekToPositionInCurrentlyPlayingTrackRequest(final Builder builder) {
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
    return executeAsync(getJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder position_ms(final Integer position_ms) {
      assert (position_ms != null);
      assert (position_ms >= 0);
      return setQueryParameter("position_ms", position_ms);
    }

    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.equals(""));
      return setQueryParameter("device_id", device_id);
    }

    @Override
    public SeekToPositionInCurrentlyPlayingTrackRequest build() {
      setPath("/v1/me/player/seek");
      return new SeekToPositionInCurrentlyPlayingTrackRequest(this);
    }
  }
}
