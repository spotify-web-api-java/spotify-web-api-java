package se.michaelthelin.spotify.requests.data.tracks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.AudioAnalysis;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a detailed audio analysis for a single track identified by its unique Spotify ID.
 *
 * @deprecated This endpoint has been deprecated by Spotify.
 */
@Deprecated
@JsonDeserialize(builder = GetTracksAudioAnalysisRequest.Builder.class)
public class GetTracksAudioAnalysisRequest extends AbstractDataRequest<AudioAnalysis> {

  /**
   * The private {@link GetTracksAudioAnalysisRequest} constructor.
   *
   * @param builder A {@link GetTracksAudioAnalysisRequest.Builder}.
   */
  private GetTracksAudioAnalysisRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an audio analysis for a track.
   *
   * @return An {@link AudioAnalysis}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public AudioAnalysis execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new AudioAnalysis.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetTracksAudioAnalysisRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<AudioAnalysis, Builder> {

    /**
     * Create a new {@link GetTracksAudioAnalysisRequest.Builder}.
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
     * @return A {@link GetTracksAudioAnalysisRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.isEmpty());
      return setPathParameter("id", id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetTracksAudioAnalysisRequest}.
     */
    @Override
    public GetTracksAudioAnalysisRequest build() {
      setPath("/v1/audio-analysis/{id}");
      return new GetTracksAudioAnalysisRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
