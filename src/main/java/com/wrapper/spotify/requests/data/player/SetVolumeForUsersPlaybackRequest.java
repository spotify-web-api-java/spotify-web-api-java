package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class SetVolumeForUsersPlaybackRequest extends AbstractDataRequest {

  private SetVolumeForUsersPlaybackRequest(final Builder builder) {
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

    public Builder volume_percent(final Integer volume_percent) {
      assert (volume_percent != null);
      assert (0 <= volume_percent && volume_percent >= 100);
      return setQueryParameter("volume_percent", volume_percent);
    }

    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.equals(""));
      return setQueryParameter("device_id", device_id);
    }

    @Override
    public SetVolumeForUsersPlaybackRequest build() {
      setPath("/v1/me/player/volume");
      return new SetVolumeForUsersPlaybackRequest(this);
    }
  }
}
