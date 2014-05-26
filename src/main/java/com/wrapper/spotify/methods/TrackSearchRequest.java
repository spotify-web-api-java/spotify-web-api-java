package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;

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

      JsonUtil.throwIfErrorsInResponse(jsonObject);

      searchResultFuture.set(JsonUtil.createTrackPage(jsonObject));
    } catch (Exception e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public Page<Track> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createTrackPage(jsonObject);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String searchQuery;

    public Builder withQuery(String searchQuery) {
      assert (searchQuery != null);
      this.searchQuery = searchQuery;

      path("/v1/search");
      parameter("type","track");
      return parameter("q", searchQuery);
    }

    public Builder withLimit(int limit) {
      assert (limit > 0);
      return parameter("limit", String.valueOf(limit));
    }

    public Builder withOffset(int offset) {
      assert (offset >= 0);
      return parameter("offset", String.valueOf(offset));
    }

    public TrackSearchRequest build() {
      assert (searchQuery != null);
      return new TrackSearchRequest(this);
    }

  }

}
