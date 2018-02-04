package com.wrapper.spotify.requests.data.tracks;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get audio feature information for a single track identified by its unique Spotify ID.
 */
public class GetAudioFeaturesForTrackRequest extends AbstractDataRequest {

  /**
   * The private {@link GetAudioFeaturesForTrackRequest} constructor.
   *
   * @param builder A {@link GetAudioFeaturesForTrackRequest.Builder}.
   */
  private GetAudioFeaturesForTrackRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get audio features for a track.
   *
   * @return An {@link AudioFeatures} object.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public AudioFeatures execute() throws
          IOException,
          SpotifyWebApiException {
    return new AudioFeatures.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetAudioFeaturesForTrackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetAudioFeaturesForTrackRequest.Builder}.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The track ID setter.
     *
     * @param id Required. The Spotify ID for the track.
     * @return A {@link GetAudioFeaturesForTrackRequest.Builder}..
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetAudioFeaturesForTrackRequest}.
     */
    @Override
    public GetAudioFeaturesForTrackRequest build() {
      setPath("/v1/audio-features/{id}");
      return new GetAudioFeaturesForTrackRequest(this);
    }
  }
}
