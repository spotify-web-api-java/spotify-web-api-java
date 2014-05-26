package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Artist;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ArtistsRequest extends AbstractRequest {

  protected ArtistsRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<List<Artist>> getAsync() {
    SettableFuture<List<Artist>> artistsFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      JsonUtil.throwIfErrorsInResponse(jsonObject);

      artistsFuture.set(JsonUtil.createArtists(getJson()));
    } catch (Exception e) {
      artistsFuture.setException(e);
    }

    return artistsFuture;
  }

  public List<Artist> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createArtists(jsonString);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String idsParameter;

    public Builder withIds(String... ids) {
      return withIds(Arrays.asList(ids));
    }

    public Builder withIds(List<String> ids) {
      assert (ids != null);
      final String idsParameter = Joiner.on(",").join(ids).toString();

      this.idsParameter = idsParameter;

      path("/v1/artists");
      return parameter("ids", idsParameter);
    }

    public ArtistsRequest build() {
      assert (idsParameter != null);
      return new ArtistsRequest(this);
    }

  }
}
