package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Track;

import java.io.IOException;
import java.util.List;

public class TopTracksRequest extends AbstractRequest {

  public TopTracksRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<List<Track>> getAsync() {
    SettableFuture<List<Track>> tracksFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);
      tracksFuture.set(JsonUtil.createTracks(jsonObject));
    } catch (Exception e) {
      tracksFuture.setException(e);
    }

    return tracksFuture;
  }

  public List<Track> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);
    return JsonUtil.createTracks(jsonObject);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(String id) {
      assert (id != null);
      return path(String.format("/v1/artists/%s/toptracks", id));
    }

    public Builder countryCode(String countryCode) {
      assert (countryCode != null);
      return parameter("country", countryCode);
    }

    public TopTracksRequest build() {
      return new TopTracksRequest(this);
    }

  }

}
