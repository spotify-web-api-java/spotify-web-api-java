package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Artist;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class RelatedArtistsRequest extends AbstractRequest {

  public RelatedArtistsRequest(Builder builder) {
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

      artistsFuture.set(JsonUtil.createArtists(jsonObject));
    } catch (Exception e) {
      artistsFuture.setException(e);
    }

    return artistsFuture;
  }

  public List<Artist> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    return JsonUtil.createArtists(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public RelatedArtistsRequest build() {
      return new RelatedArtistsRequest(this);
    }

  }

}
