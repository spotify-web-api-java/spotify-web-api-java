package com.wrapper.spotify.requests.data.tracks;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get audio features for multiple tracks based on their Spotify IDs.
 */
public class GetAudioFeaturesForSeveralTracksRequest extends AbstractDataRequest {

  /**
   * The private {@link GetAudioFeaturesForSeveralTracksRequest} constructor.
   *
   * @param builder A {@link GetAudioFeaturesForSeveralTracksRequest.Builder}.
   */
  private GetAudioFeaturesForSeveralTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get audio features for several tracks.
   *
   * @return Multiple {@link AudioFeatures} objects.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public AudioFeatures[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new AudioFeatures.JsonUtil().createModelObjectArray(getJson(), "audio_features");
  }

  /**
   * Builder class for building a {@link GetAudioFeaturesForSeveralTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetAudioFeaturesForSeveralTracksRequest.Builder}.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The track IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the tracks. Maximum: 100 IDs.
     * @return A {@link GetAudioFeaturesForSeveralTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 100);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetAudioFeaturesForSeveralTracksRequest}.
     */
    @Override
    public GetAudioFeaturesForSeveralTracksRequest build() {
      setPath("/v1/audio-features");
      return new GetAudioFeaturesForSeveralTracksRequest(this);
    }
  }
}
