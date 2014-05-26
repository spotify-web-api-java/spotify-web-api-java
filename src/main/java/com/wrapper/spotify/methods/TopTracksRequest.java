package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Track;

import java.io.IOException;
import java.util.List;

public class TopTracksRequest extends AbstractRequest {

  public TopTracksRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<List<Track>> getAsync() {
    SettableFuture<List<Track>> tracksFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      JsonUtil.throwIfErrorsInResponse(jsonObject);

      tracksFuture.set(JsonUtil.createTracks(jsonString));
    } catch (Exception e) {
      tracksFuture.setException(e);
    }

    return tracksFuture;
  }

  public List<Track> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createTracks(getJson());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String artistId;
    private String countryCode;

    public Builder withId(String artistId) {
      assert (artistId != null);
      this.artistId = artistId;

      return path(String.format("/v1/artists/%s/toptracks", artistId));
    }

    public Builder withCountryCode(String countryCode) {
      assert (countryCode != null);
      this.countryCode = countryCode;

      return parameter("country", countryCode);
    }

    public TopTracksRequest build() {
      assert (countryCode != null);
      assert (artistId != null);

      return new TopTracksRequest(this);
    }

  }

}
