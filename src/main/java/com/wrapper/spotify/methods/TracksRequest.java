package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Track;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TracksRequest extends AbstractRequest {

  public TracksRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<List<Track>> getAsync() {
    SettableFuture<List<Track>> tracksFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      JsonUtil.throwIfErrorsInResponse(jsonObject);

      tracksFuture.set(JsonUtil.createTracks(jsonObject));
    } catch (Exception e) {
      tracksFuture.setException(e);
    }

    return tracksFuture;
  }

  public List<Track> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createTracks(jsonObject);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String idsParameter;

    public Builder withIds(String... trackIds) {
      return withIds(Arrays.asList(trackIds));
    }

    public Builder withIds(List<String> trackIds) {
      assert (trackIds != null);
      String idsParameter = Joiner.on(",").join(trackIds).toString();
      this.idsParameter = idsParameter;
      path("/v1/tracks");
      return parameter("ids", idsParameter);
    }

    public TracksRequest build() {
      assert (idsParameter != null);

      return new TracksRequest(this);
    }

  }

}
