package se.michaelthelin.spotify.requests.data.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Set the volume for the user’s current playback device.
 */
@JsonDeserialize(builder = SetVolumeForUsersPlaybackRequest.Builder.class)
public class SetVolumeForUsersPlaybackRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link SetVolumeForUsersPlaybackRequest} constructor.
   *
   * @param builder A {@link SetVolumeForUsersPlaybackRequest.Builder}.
   */
  private SetVolumeForUsersPlaybackRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Set the volume for the user’s current playback device.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public String execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return putJson();
  }

  /**
   * Builder class for building a {@link SetVolumeForUsersPlaybackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link SetVolumeForUsersPlaybackRequest.Builder}.
     * <p>
     * Your access token must have the {@code user-modify-playback-state} scope authorized in order to control playback.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The volume percentage setter.
     *
     * @param volume_percent Required. The volume to set. Must be a value from 0 to 100 inclusive.
     * @return A {@link SetVolumeForUsersPlaybackRequest.Builder}.
     */
    public Builder volume_percent(final Integer volume_percent) {
      assert (volume_percent != null);
      assert (0 <= volume_percent && volume_percent <= 100);
      return setQueryParameter("volume_percent", volume_percent);
    }

    /**
     * The device ID setter.
     *
     * @param device_id Optional. The ID of the device this command is targeting. If not supplied, the
     *                  user's currently active device is the target.
     * @return A {@link SetVolumeForUsersPlaybackRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.isEmpty());
      return setQueryParameter("device_id", device_id);
    }

    /**
     * The build method.
     *
     * @return A custom {@link SetVolumeForUsersPlaybackRequest}.
     */
    @Override
    public SetVolumeForUsersPlaybackRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/player/volume");
      return new SetVolumeForUsersPlaybackRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
