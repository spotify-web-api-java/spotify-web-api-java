package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
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
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      JsonUtil.throwIfErrorsInResponse(jsonObject);

      trackFuture.set(JsonUtil.createTrack(jsonString));
    } catch (Exception e) {
      trackFuture.setException(e);
    }

    return trackFuture;
  }

  public Track get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createTrack(getJson());
  }

  public static Builder builder() {
    return new Builder();
  }


  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String trackId;

    /**
     * The track with the given id.
     *
     * @param trackId The id for the track.
     * @return Track Request
     */
    public Builder withId(String trackId) {
      assert (trackId != null);
      this.trackId = trackId;

      return path(String.format("/v1/tracks/%s", trackId));
    }

    public TrackRequest build() {
      assert (trackId != null);

      return new TrackRequest(this);
    }

  }

}
