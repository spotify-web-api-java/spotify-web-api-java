package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Album;

import java.io.IOException;
import java.util.List;

public class AlbumsRequest extends AbstractRequest {

  public AlbumsRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<List<Album>> getAsync() {
    SettableFuture<List<Album>> albumsFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);

      albumsFuture.set(JsonUtil.createAlbums(jsonString));
    } catch (Exception e) {
      albumsFuture.setException(e);
    }

    return albumsFuture;
  }

  public List<Album> get() throws IOException, WebApiException {
    String jsonString = getJson();
    JSONObject jsonObject = JSONObject.fromObject(jsonString);

    return JsonUtil.createAlbums(jsonString);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(List<String> ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids).toString();
      path("/v1/albums");
      return parameter("ids", idsParameter);
    }

    public AlbumsRequest build() {
      return new AlbumsRequest(this);
    }

  }
}
