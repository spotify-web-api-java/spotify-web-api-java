package se.michaelthelin.spotify.requests.data.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Toggle shuffle on or off for user’s playback.
 */
@JsonDeserialize(builder = TogglePlaybackShuffleRequest.Builder.class)
public class TogglePlaybackShuffleRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link TogglePlaybackShuffleRequest} constructor.
   *
   * @param builder A {@link TogglePlaybackShuffleRequest.Builder}.
   */
  private TogglePlaybackShuffleRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Toggle the shuffle state of an user's playback.
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
   * Builder class for building a {@link TogglePlaybackShuffleRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link TogglePlaybackShuffleRequest.Builder}.
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
     * The toggle state setter.
     *
     * @param state Required. {@code true}: Shuffle user's playback. {@code false}: Do not shuffle user's playback.
     * @return A {@link TogglePlaybackShuffleRequest.Builder}.
     */
    public Builder state(final Boolean state) {
      return setQueryParameter("state", state);
    }

    /**
     * The device ID setter.
     *
     * @param device_id Optional. The ID of the device this command is targeting. If not supplied, the
     *                  user's currently active device is the target.
     * @return A {@link TogglePlaybackShuffleRequest.Builder}.
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
     * @return A custom {@link TogglePlaybackShuffleRequest}.
     */
    @Override
    public TogglePlaybackShuffleRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/player/shuffle");
      return new TogglePlaybackShuffleRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
