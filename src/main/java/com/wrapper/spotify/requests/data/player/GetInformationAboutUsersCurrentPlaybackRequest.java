package com.wrapper.spotify.requests.data.player;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get information about the user’s current playback state, including track, track progress, and active device.
 */
public class GetInformationAboutUsersCurrentPlaybackRequest extends AbstractDataRequest {

  /**
   * The private {@link GetInformationAboutUsersCurrentPlaybackRequest} constructor.
   *
   * @param builder A {@link GetInformationAboutUsersCurrentPlaybackRequest.Builder}.
   */
  private GetInformationAboutUsersCurrentPlaybackRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get information about a user's playback.
   *
   * @return Information about a users playback.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public CurrentlyPlayingContext execute() throws
          IOException,
          SpotifyWebApiException {
    return new CurrentlyPlayingContext.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetInformationAboutUsersCurrentPlaybackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetInformationAboutUsersCurrentPlaybackRequest.Builder}.
     * <p>
     * Your access token must have the {@code user-read-playback-state} scope authorized in order to read information.
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
     * @return A {@link GetInformationAboutUsersCurrentPlaybackRequest.Builder}.
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
     * @return A custom {@link GetInformationAboutUsersCurrentPlaybackRequest}.
     */
    @Override
    public GetInformationAboutUsersCurrentPlaybackRequest build() {
      setPath("/v1/me/player");
      return new GetInformationAboutUsersCurrentPlaybackRequest(this);
    }
  }
}
