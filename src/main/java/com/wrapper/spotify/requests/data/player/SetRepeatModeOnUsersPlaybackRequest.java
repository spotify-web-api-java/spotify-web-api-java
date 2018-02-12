package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * Set the repeat mode for the user’s playback.
 */
public class SetRepeatModeOnUsersPlaybackRequest extends AbstractDataRequest {

  /**
   * The private {@link SetRepeatModeOnUsersPlaybackRequest} constructor.
   *
   * @param builder A {@link SetRepeatModeOnUsersPlaybackRequest.Builder}.
   */
  private SetRepeatModeOnUsersPlaybackRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Set the repeat mode on a user's playback.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return putJson();
  }

  /**
   * Builder class for building a {@link SetRepeatModeOnUsersPlaybackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link SetRepeatModeOnUsersPlaybackRequest.Builder}.
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
     * The repeat state setter.
     *
     * @param state Required. {@code track}, {@code context} or {@code off}. {@code track} will repeat the current
     *              track. {@code context} will repeat the current context. {@code off} will turn repeat off.
     * @return A {@link SetRepeatModeOnUsersPlaybackRequest.Builder}.
     */
    public Builder state(final String state) {
      assert (state != null);
      assert (state.equals("track") || state.equals("context") || state.equals("off"));
      return setQueryParameter("state", state);
    }

    /**
     * The device ID setter.
     *
     * @param device_id Optional. The ID of the device this command is targeting. If not supplied, the
     *                  user's currently active device is the target.
     * @return A {@link SetRepeatModeOnUsersPlaybackRequest.Builder}.
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
     * @return A custom {@link SetRepeatModeOnUsersPlaybackRequest}.
     */
    @Override
    public SetRepeatModeOnUsersPlaybackRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/player/repeat");
      return new SetRepeatModeOnUsersPlaybackRequest(this);
    }
  }
}
