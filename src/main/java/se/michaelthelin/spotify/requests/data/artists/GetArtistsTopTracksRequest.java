package se.michaelthelin.spotify.requests.data.artists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about an artist's top tracks by country.
 *
 * @deprecated This endpoint has been deprecated by Spotify.
 */
@Deprecated
@JsonDeserialize(builder = GetArtistsTopTracksRequest.Builder.class)
public class GetArtistsTopTracksRequest extends AbstractDataRequest<Track[]> {

  /**
   * The private {@link GetArtistsTopTracksRequest} constructor.
   *
   * @param builder A {@link GetArtistsTopTracksRequest.Builder}.
   */
  private GetArtistsTopTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an artist's top tracks.
   *
   * @return Multiple {@link Track} objects.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Track[] execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Track.JsonUtil().createModelObjectArray(getJson(), "tracks");
  }

  /**
   * Builder class for building a {@link GetArtistsTopTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Track[], Builder> {

    /**
     * Create a new {@link GetArtistsTopTracksRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The artist ID path parameter setter.
     *
     * @param id Required. The Spotify ID for the artist.
     * @return A {@link GetArtistsTopTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.isEmpty());
      return setPathParameter("id", id);
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply
     *               Track Relinking.
     * @return A {@link GetArtistsTopTracksRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetArtistsTopTracksRequest}.
     */
    @Override
    public GetArtistsTopTracksRequest build() {
      setPath("/v1/artists/{id}/top-tracks");
      return new GetArtistsTopTracksRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
