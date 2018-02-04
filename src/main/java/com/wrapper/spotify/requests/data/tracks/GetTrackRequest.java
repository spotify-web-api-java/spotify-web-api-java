package com.wrapper.spotify.requests.data.tracks;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for a single track identified by its unique Spotify ID.
 */
public class GetTrackRequest extends AbstractDataRequest {

  /**
   * The private {@link GetTrackRequest} constructor.
   *
   * @param builder A {@link GetTrackRequest.Builder}.
   */
  private GetTrackRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a track.
   *
   * @return A {@link Track}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Track execute() throws
          IOException,
          SpotifyWebApiException {
    return new Track.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetTrackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetTrackRequest.Builder}.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The track ID setter.
     *
     * @param id The Spotify ID for the track.
     * @return A {@link GetTrackRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
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
     * @return A custom {@link GetTrackRequest}.
     */
    @Override
    public GetTrackRequest build() {
      setPath("/v1/tracks/{id}");
      return new GetTrackRequest(this);
    }
  }
}
