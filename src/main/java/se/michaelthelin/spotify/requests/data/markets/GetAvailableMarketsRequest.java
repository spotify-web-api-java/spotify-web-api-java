package se.michaelthelin.spotify.requests.data.markets;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get the list of markets where Spotify is available.
 *
 * @deprecated This endpoint has been deprecated by Spotify.
 */
@Deprecated
@JsonDeserialize(builder = GetAvailableMarketsRequest.Builder.class)
public class GetAvailableMarketsRequest extends AbstractDataRequest<String[]> {

  /**
   * The private {@link GetAvailableMarketsRequest} constructor.
   *
   * @param builder A {@link GetAvailableMarketsRequest.Builder}.
   */
  private GetAvailableMarketsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the list of available markets.
   *
   * @return An array of ISO 3166-1 alpha-2 country codes.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public String[] execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    JsonObject jsonObject = JsonParser.parseString(getJson()).getAsJsonObject();
    return new Gson().fromJson(jsonObject.getAsJsonArray("markets"), String[].class);
  }

  /**
   * Builder class for building a {@link GetAvailableMarketsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String[], Builder> {

    /**
     * Create a new {@link GetAvailableMarketsRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetAvailableMarketsRequest}.
     */
    @Override
    public GetAvailableMarketsRequest build() {
      setPath("/v1/markets");
      return new GetAvailableMarketsRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
