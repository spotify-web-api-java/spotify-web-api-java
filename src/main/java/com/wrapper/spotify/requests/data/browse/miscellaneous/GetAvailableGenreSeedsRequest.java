package com.wrapper.spotify.requests.data.browse.miscellaneous;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;
import java.util.List;

/**
 * Retrieve a list of available genres seed parameter values for recommendations.
 */
public class GetAvailableGenreSeedsRequest extends AbstractDataRequest {

  /**
   * The private {@link GetAvailableGenreSeedsRequest} constructor.
   *
   * @param builder A {@link GetAvailableGenreSeedsRequest.Builder}.
   */
  private GetAvailableGenreSeedsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get all available genre seeds.
   *
   * @return All available genre seeds.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
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

  /**
   * Builder class for building a {@link GetAvailableGenreSeedsRequest.Builder}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetAvailableGenreSeedsRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetAvailableGenreSeedsRequest}.
     */
    @Override
    public GetAvailableGenreSeedsRequest build() {
      setPath("/v1/recommendations/available-genre-seeds");
      return new GetAvailableGenreSeedsRequest(this);
    }
  }
}
