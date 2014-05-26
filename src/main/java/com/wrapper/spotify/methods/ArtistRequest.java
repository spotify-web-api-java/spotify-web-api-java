package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Artist;

import java.io.IOException;

public class ArtistRequest extends AbstractRequest {

  protected ArtistRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Artist> getAsync() {
    SettableFuture<Artist> artistFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      JsonUtil.throwIfErrorsInResponse(jsonObject);

      artistFuture.set(JsonUtil.createArtist(jsonString));
    } catch (Exception e) {
      artistFuture.setException(e);
    }

    return artistFuture;
  }

  public Artist get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createArtist(jsonString);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String artistId;

    public Builder withId(String artistId) {
      assert (artistId != null);
      this.artistId = artistId;
      
      return path(String.format("/v1/artists/%s", artistId));
    }

    public ArtistRequest build() {
      assert (artistId != null);

      return new ArtistRequest(this);
    }

  }
}
