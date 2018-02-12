package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * Skips to next track in the user’s queue.
 */
public class SkipUsersPlaybackToNextTrackRequest extends AbstractDataRequest {

  /**
   * The private {@link SkipUsersPlaybackToNextTrackRequest} constructor.
   *
   * @param builder A {@link SkipUsersPlaybackToNextTrackRequest.Builder}.
   */
  private SkipUsersPlaybackToNextTrackRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Skip to the next track in the user’s queue.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return postJson();
  }

  /**
   * Builder class for building a {@link SkipUsersPlaybackToNextTrackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link SkipUsersPlaybackToNextTrackRequest.Builder}.
     * <p>
     * Your access token must have the {@code user-modify-playback-state} scope authorized in order to control playback.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The device ID setter.
     *
     * @param device_id Optional. The ID of the device this command is targeting. If not supplied, the
     *                  user's currently active device is the target.
     * @return A {@link SkipUsersPlaybackToNextTrackRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.equals(""));
      return setQueryParameter("device_id", device_id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link SkipUsersPlaybackToNextTrackRequest}.
     */
    @Override
    public SkipUsersPlaybackToNextTrackRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/player/next");
      return new SkipUsersPlaybackToNextTrackRequest(this);
    }
  }
}
