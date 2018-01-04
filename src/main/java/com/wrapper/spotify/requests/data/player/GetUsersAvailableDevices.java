package com.wrapper.spotify.requests.data.player;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.Device;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersAvailableDevices extends AbstractDataRequest {

  private GetUsersAvailableDevices(final Builder builder) {
    super(builder);
  }

  public Device[] get() throws
          IOException,
          SpotifyWebApiException {
    return new Device.JsonUtil().createModelObjectArray(getJson(), "devices");
  }

  public SettableFuture<Device[]> getAsync() throws
          IOException,
          SpotifyWebApiException {
    return executeAsync(new Device.JsonUtil().createModelObjectArray(getJson(), "devices"));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    @Override
    public GetUsersAvailableDevices build() {
      setPath("/v1/me/player/devices");
      return new GetUsersAvailableDevices(this);
    }
  }
}