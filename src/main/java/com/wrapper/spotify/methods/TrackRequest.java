package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Track;

import java.io.IOException;

public class TrackRequest extends AbstractRequest {

  public TrackRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Track> getAsync() {
    SettableFuture<Track> trackFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      trackFuture.set(JsonUtil.createTrack(jsonString));
    } catch (Exception e) {
      trackFuture.setException(e);
    }

    return trackFuture;
  }

  public Track get() throws IOException, WebApiException {
    final String jsonString = getJson();
    return JsonUtil.createTrack(jsonString);
  }

  public static Builder builder() {
    return new Builder();
  }


  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * The track with the given id.
     *
     * @param id The id for the track.
     * @return Track Request
     */
    public Builder id(String id) {
      assert (id != null);
      return path(String.format("/v1/tracks/%s", id));
    }

    public TrackRequest build() {
      return new TrackRequest(this);
    }

  }

}
