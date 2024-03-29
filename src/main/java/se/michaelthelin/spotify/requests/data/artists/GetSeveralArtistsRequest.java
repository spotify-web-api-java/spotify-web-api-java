package se.michaelthelin.spotify.requests.data.artists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for several artists based on their Spotify IDs.
 */
@JsonDeserialize(builder = GetSeveralArtistsRequest.Builder.class)
public class GetSeveralArtistsRequest extends AbstractDataRequest<Artist[]> {

  /**
   * The private {@link GetSeveralArtistsRequest} constructor.
   *
   * @param builder A {@link GetSeveralArtistsRequest.Builder}.
   */
  private GetSeveralArtistsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get several {@link Artist} objects.
   *
   * @return An array with {@link Artist} objects.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Artist[] execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Artist.JsonUtil().createModelObjectArray(getJson(), "artists");
  }

  /**
   * Builder class for building a {@link GetSeveralArtistsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Artist[], Builder> {

    /**
     * Create a new {@link GetSeveralArtistsRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The artist IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the artists. Maximum: 50 IDs.
     * @return A {@link GetSeveralArtistsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetSeveralArtistsRequest}.
     */
    @Override
    public GetSeveralArtistsRequest build() {
      setPath("/v1/artists");
      return new GetSeveralArtistsRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
