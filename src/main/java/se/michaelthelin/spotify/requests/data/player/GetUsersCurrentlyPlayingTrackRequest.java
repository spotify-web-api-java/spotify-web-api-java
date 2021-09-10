package se.michaelthelin.spotify.requests.data.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get the object currently being played on the user’s Spotify account.
 */
@JsonDeserialize(builder = GetUsersCurrentlyPlayingTrackRequest.Builder.class)
public class GetUsersCurrentlyPlayingTrackRequest extends AbstractDataRequest<CurrentlyPlaying> {

  /**
   * The private {@link GetUsersCurrentlyPlayingTrackRequest} constructor.
   *
   * @param builder A {@link GetUsersCurrentlyPlayingTrackRequest.Builder}.
   */
  private GetUsersCurrentlyPlayingTrackRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an user's currently playing track.
   *
   * @return An user's currently playing track.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public CurrentlyPlaying execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new CurrentlyPlaying.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetUsersCurrentlyPlayingTrackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<CurrentlyPlaying, Builder> {

    /**
     * Create a new {@link GetUsersCurrentlyPlayingTrackRequest.Builder}.
     * <p>
     * Your access token must have the {@code user-read-currently-playing} scope and/or the
     * {@code user-read-playback-state} authorized in order to read information.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The market country code setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you
     *               want to apply Track Relinking.
     * @return A {@link GetUsersCurrentlyPlayingTrackRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/track-relinking-guide/">Spotify: Track Relinking Guide</a>
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The additional types setter.
     *
     * @param additionalTypes Optional. A comma-separated list of item types that your client supports
     *                        besides the default track type. Valid types are: {@code track} and {@code episode}.
     *                        An unsupported type in the response is expected to be represented as {@code null} value in the {@code item} field.
     * @return A {@link GetUsersCurrentlyPlayingTrackRequest.Builder}.
     */
    public GetUsersCurrentlyPlayingTrackRequest.Builder additionalTypes(final String additionalTypes) {
      assert (additionalTypes != null);
      assert (additionalTypes.matches("((^|,)(episode|track))+$"));
      return setQueryParameter("additional_types", additionalTypes);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetUsersCurrentlyPlayingTrackRequest}.
     */
    @Override
    public GetUsersCurrentlyPlayingTrackRequest build() {
      setPath("/v1/me/player/currently-playing");
      return new GetUsersCurrentlyPlayingTrackRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
