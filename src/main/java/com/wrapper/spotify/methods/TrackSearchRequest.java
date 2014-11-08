package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;
import net.sf.json.JSONObject;

import java.io.IOException;

public class TrackSearchRequest extends AbstractRequest {

  public TrackSearchRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Page<Track>> getAsync() {
    SettableFuture<Page<Track>> searchResultFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      searchResultFuture.set(JsonUtil.createTrackPage(jsonObject));
    } catch (Exception e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public Page<Track> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    return JsonUtil.createTrackPage(jsonObject);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(String query) {
      assert (query != null);
      path("/v1/search");
      parameter("type","track");
      return parameter("q", query);
    }

    public Builder market(String market) {
      assert (market != null);
      return parameter("market", market);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return parameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return parameter("offset", String.valueOf(offset));
    }

    public TrackSearchRequest build() {
      return new TrackSearchRequest(this);
    }

  }

}
