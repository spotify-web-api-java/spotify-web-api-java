package se.michaelthelin.spotify.requests.data.artists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about artists similar to a given artist. Similarity is based on analysis of the
 * Spotify community’s listening history.
 */
@JsonDeserialize(builder = GetArtistsRelatedArtistsRequest.Builder.class)
public class GetArtistsRelatedArtistsRequest extends AbstractDataRequest<Artist[]> {

  /**
   * The private {@link GetArtistsRelatedArtistsRequest} constructor.
   *
   * @param builder A {@link GetArtistsRelatedArtistsRequest.Builder}.
   */
  private GetArtistsRelatedArtistsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the related {@link Artist} objects.
   *
   * @return An array of {@link Artist} objects.
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
   * Builder class for building a {@link GetArtistsRelatedArtistsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Artist[], Builder> {

    /**
     * Create a new {@link GetArtistsRelatedArtistsRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The artist ID setter.
     *
     * @param id The Spotify ID for the artist.
     * @return A {@link GetArtistsRelatedArtistsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetArtistsRelatedArtistsRequest}.
     */
    @Override
    public GetArtistsRelatedArtistsRequest build() {
      setPath("/v1/artists/{id}/related-artists");
      return new GetArtistsRelatedArtistsRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
