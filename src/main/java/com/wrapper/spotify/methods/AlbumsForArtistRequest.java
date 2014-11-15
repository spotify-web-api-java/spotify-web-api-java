package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.AlbumType;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleAlbum;

import java.io.IOException;

public class AlbumsForArtistRequest extends AbstractRequest {

  public AlbumsForArtistRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Page<SimpleAlbum>> getAsync() {
    SettableFuture<Page<SimpleAlbum>> searchResultFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      searchResultFuture.set(JsonUtil.createSimpleAlbumPage(jsonObject));
    } catch (Exception e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public Page<SimpleAlbum> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    return JsonUtil.createSimpleAlbumPage(jsonObject);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder forArtist(String id) {
      assert (id != null);
      return path(String.format("/v1/artists/%s/albums", id));
    }

    public Builder types(AlbumType... types) {
      assert (types != null);
      assert (types.length > 0);
      String albumsParameter = Joiner.on(",").join(types).toString();
      return parameter("album_type", albumsParameter);
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

    public AlbumsForArtistRequest build() {
      return new AlbumsForArtistRequest(this);
    }

  }

}
