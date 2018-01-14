package com.wrapper.spotify.requests.data.browse.miscellaneous;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;
import java.util.List;

public class GetAvailableGenreSeedsRequest extends AbstractDataRequest {

  private GetAvailableGenreSeedsRequest(final Builder builder) {
    super(builder);
  }

  public String[] execute() throws
          IOException,
          SpotifyWebApiException {
    List<String> genres = new Gson().fromJson(
            new JsonParser()
                    .parse(getJson())
                    .getAsJsonObject()
                    .get("genres")
                    .getAsJsonArray(),
            new TypeToken<List<String>>() {
            }.getType()
    );

    return genres.toArray(new String[0]);
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    @Override
    public GetAvailableGenreSeedsRequest build() {
      setPath("/v1/recommendations/available-genre-seeds");
      return new GetAvailableGenreSeedsRequest(this);
    }
  }
}
