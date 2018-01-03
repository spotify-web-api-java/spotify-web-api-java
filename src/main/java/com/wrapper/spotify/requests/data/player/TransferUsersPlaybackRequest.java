package com.wrapper.spotify.requests.data.player;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonArray;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class TransferUsersPlaybackRequest extends AbstractDataRequest {

  private TransferUsersPlaybackRequest(final Builder builder) {
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

    public Builder device_ids(final JsonArray device_ids) {
      assert (device_ids != null);
      assert (!device_ids.isJsonNull());
      return setBodyParameter("device_ids", device_ids);
    }

    public Builder play(final Boolean play) {
      return setBodyParameter("play", play);
    }

    @Override
    public TransferUsersPlaybackRequest build() {
      setPath("/v1/me/player");
      return new TransferUsersPlaybackRequest(this);
    }
  }
}
