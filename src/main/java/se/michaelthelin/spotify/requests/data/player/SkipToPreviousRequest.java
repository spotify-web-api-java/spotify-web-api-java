package se.michaelthelin.spotify.requests.data.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Skips to previous track in the user’s queue.
 * <p>
 * <b>Note:</b> This will ALWAYS skip to the previous track, regardless of the current track’s progress.
 * Returning to the start of the current track should be performed using a
 * {@link SeekToPositionRequest}.
 */
@JsonDeserialize(builder = SkipToPreviousRequest.Builder.class)
public class SkipToPreviousRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link SkipToPreviousRequest} constructor.
   *
   * @param builder A {@link SkipToPreviousRequest.Builder}.
   */
  private SkipToPreviousRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Skip to the previous track in the user’s queue.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public String execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return postJson();
  }

  /**
   * Builder class for building a {@link SkipToPreviousRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link SkipToPreviousRequest.Builder}.
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
     * The device ID setter.
     *
     * @param device_id Optional. The ID of the device this command is targeting. If not supplied, the
     *                  user's currently active device is the target.
     * @return A {@link SkipToPreviousRequest.Builder}.
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
     * @return A custom {@link SkipToPreviousRequest}.
     */
    @Override
    public SkipToPreviousRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/player/previous");
      return new SkipToPreviousRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
