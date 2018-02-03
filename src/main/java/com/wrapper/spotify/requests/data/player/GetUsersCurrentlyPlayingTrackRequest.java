package com.wrapper.spotify.requests.data.player;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get the object currently being played on the user’s Spotify account.
 */
public class GetUsersCurrentlyPlayingTrackRequest extends AbstractDataRequest {

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
  @SuppressWarnings("unchecked")
  public CurrentlyPlaying execute() throws
          IOException,
          SpotifyWebApiException {
    return new CurrentlyPlaying.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetUsersCurrentlyPlayingTrackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

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
     * The request build method.
     *
     * @return A custom {@link GetUsersCurrentlyPlayingTrackRequest}.
     */
    @Override
    public GetUsersCurrentlyPlayingTrackRequest build() {
      setPath("/v1/me/player/currently-playing");
      return new GetUsersCurrentlyPlayingTrackRequest(this);
    }
  }
}
