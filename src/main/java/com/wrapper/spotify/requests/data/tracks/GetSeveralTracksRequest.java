package com.wrapper.spotify.requests.data.tracks;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for multiple tracks based on their Spotify IDs.
 */
public class GetSeveralTracksRequest extends AbstractDataRequest {

  /**
   * The private {@link GetSeveralTracksRequest} constructor.
   *
   * @param builder A {@link GetSeveralTracksRequest.Builder}.
   */
  private GetSeveralTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get several tracks.
   *
   * @return Multiple {@link Track} objects.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Track[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new Track.JsonUtil().createModelObjectArray(getJson(), "tracks");
  }

  /**
   * Builder class for building a {@link GetSeveralTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetSeveralTracksRequest.Builder}.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The track IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the tracks. Maximum: 50 IDs.
     * @return A {@link GetSeveralTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The market country code setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this
     *               parameter if you want to apply Track Relinking.
     * @return A {@link GetSeveralTracksRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     * @see <a href="https://developer.spotify.com/web-api/track-relinking-guide/">Spotify: Track Relinking Guide</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetSeveralTracksRequest}.
     */
    @Override
    public GetSeveralTracksRequest build() {
      setPath("/v1/tracks");
      return new GetSeveralTracksRequest(this);
    }
  }
}
