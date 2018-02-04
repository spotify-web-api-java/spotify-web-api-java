package com.wrapper.spotify.requests.data.artists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about an artist’s top tracks by country.
 */
public class GetArtistsTopTracksRequest extends AbstractDataRequest {

  /**
   * The private {@link GetArtistsTopTracksRequest} constructor.
   *
   * @param builder A {@link GetArtistsTopTracksRequest.Builder}.
   */
  private GetArtistsTopTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the top {@link Track} objects.
   *
   * @return An array of {@link Track} objects.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Track[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new Track.JsonUtil().createModelObjectArray(getJson(), "tracks");
  }

  /**
   * Builder class for building a {@link GetArtistsTopTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetArtistsTopTracksRequest.Builder} instance.
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
     * @return A {@link GetArtistsTopTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    /**
     * The country code setter.
     *
     * @param country Required. The country: an ISO 3166-1 alpha-2 country code.
     * @return A {@link GetArtistsTopTracksRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder country(final CountryCode country) {
      assert (country != null);
      return setQueryParameter("country", country);
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
  }
}
