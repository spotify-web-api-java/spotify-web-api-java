package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Track;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class TracksRequest extends AbstractRequest {

  public TracksRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<List<Track>> getAsync() {
    SettableFuture<List<Track>> tracksFuture = SettableFuture.create();

    try {
      final JSONObject jsonObject = JSONObject.fromObject(getJson());

      tracksFuture.set(JsonUtil.createTracks(jsonObject));
    } catch (Exception e) {
      tracksFuture.setException(e);
    }

    return tracksFuture;
  }

  public List<Track> get() throws IOException, WebApiException {
    final JSONObject jsonObject = JSONObject.fromObject(getJson());
    return JsonUtil.createTracks(jsonObject);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(List<String> ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids);
      path("/v1/tracks");
      return parameter("ids", idsParameter);
    }

    public TracksRequest build() {
      return new TracksRequest(this);
    }

  }

}
