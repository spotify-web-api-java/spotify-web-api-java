package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Page;

import java.io.IOException;

public class ArtistSearchRequest extends AbstractRequest {


  public ArtistSearchRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Page<Artist>> getAsync() {
    SettableFuture<Page<Artist>> searchResultFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);

      searchResultFuture.set(JsonUtil.createArtistPage(jsonObject.getJSONObject("artists")));
    } catch (Exception e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public Page<Artist> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    return JsonUtil.createArtistPage(jsonObject.getJSONObject("artists"));
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(String query) {
      assert (query != null);
      path("/v1/search");
      parameter("type","artist");
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

    public ArtistSearchRequest build() {
      return new ArtistSearchRequest(this);
    }

  }
}
