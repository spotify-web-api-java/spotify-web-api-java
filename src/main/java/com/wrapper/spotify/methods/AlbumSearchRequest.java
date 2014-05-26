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

      JsonUtil.throwIfErrorsInResponse(jsonObject);

      searchResultFuture.set(JsonUtil.createSimpleAlbumPage(jsonObject.getJSONObject("albums")));
    } catch (Exception e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public Page<SimpleAlbum> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createSimpleAlbumPage(JSONObject.fromObject(getJson()).getJSONObject("albums"));
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
      parameter("type","album");

      return parameter("q", searchQuery);
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
      assert (searchQuery != null);

      return new AlbumSearchRequest(this);
    }

  }

}
