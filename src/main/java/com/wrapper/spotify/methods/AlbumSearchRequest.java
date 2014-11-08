package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleAlbum;

import java.io.IOException;

public class AlbumSearchRequest extends AbstractRequest {

  protected AlbumSearchRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Page<SimpleAlbum>> getAsync() {
    SettableFuture<Page<SimpleAlbum>> searchResultFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      searchResultFuture.set(JsonUtil.createSimpleAlbumPage(jsonObject.getJSONObject("albums")));
    } catch (Exception e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public Page<SimpleAlbum> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    return JsonUtil.createSimpleAlbumPage(JSONObject.fromObject(jsonObject).getJSONObject("albums"));
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(String query) {
      assert (query != null);
      path("/v1/search");
      parameter("type","album");
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

    public AlbumSearchRequest build() {
      return new AlbumSearchRequest(this);
    }

  }

}
