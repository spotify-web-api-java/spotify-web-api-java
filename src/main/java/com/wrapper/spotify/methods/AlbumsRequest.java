package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Album;

import java.io.IOException;
import java.util.Arrays;
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

      JsonUtil.throwIfErrorsInResponse(jsonObject);

      albumsFuture.set(JsonUtil.createAlbums(jsonString));
    } catch (Exception e) {
      albumsFuture.setException(e);
    }

    return albumsFuture;
  }

  public List<Album> get() throws IOException, WebApiException {
    String jsonString = getJson();
    JSONObject jsonObject = JSONObject.fromObject(jsonString);

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createAlbums(jsonString);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String idsParameter;

    public Builder withIds(String... albumIds) {
      return withIds(Arrays.asList(albumIds));
    }

    public Builder withIds(List<String> albumIds) {
      assert (albumIds != null);

      final String idsParameter = Joiner.on(",").join(albumIds).toString();
      this.idsParameter = idsParameter;
      path("/v1/albums");

      return parameter("ids", idsParameter);
    }

    public AlbumsRequest build() {
      assert (idsParameter != null);

      return new AlbumsRequest(this);
    }

  }
}
