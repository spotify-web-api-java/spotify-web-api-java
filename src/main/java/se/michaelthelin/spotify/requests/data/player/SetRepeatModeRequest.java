package se.michaelthelin.spotify.requests.data.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Set the repeat mode for the user’s playback.
 */
@JsonDeserialize(builder = SetRepeatModeRequest.Builder.class)
public class SetRepeatModeRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link SetRepeatModeRequest} constructor.
   *
   * @param builder A {@link SetRepeatModeRequest.Builder}.
   */
  private SetRepeatModeRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Set the repeat mode on a user's playback.
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
   * Builder class for building a {@link SetRepeatModeRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link SetRepeatModeRequest.Builder}.
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
     * The repeat state setter.
     *
     * @param state Required. {@code track}, {@code context} or {@code off}. {@code track} will repeat the current
     *              track. {@code context} will repeat the current context. {@code off} will turn repeat off.
     * @return A {@link SetRepeatModeRequest.Builder}.
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
     * @return A {@link SetRepeatModeRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.isEmpty());
      return setQueryParameter("device_id", device_id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link SetRepeatModeRequest}.
     */
    @Override
    public SetRepeatModeRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/player/repeat");
      return new SetRepeatModeRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
