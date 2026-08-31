package se.michaelthelin.spotify.requests.data.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Add a track or an episode to the end of the user's current playback queue.
 */
@JsonDeserialize(builder = AddItemToPlaybackQueueRequest.Builder.class)
public class AddItemToPlaybackQueueRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link AddItemToPlaybackQueueRequest} constructor.
   *
   * @param builder A {@link AddItemToPlaybackQueueRequest.Builder}.
   */
  private AddItemToPlaybackQueueRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Add an item to the user's playback queue.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public String execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return postJson();
  }

  /**
   * Builder class for building a {@link AddItemToPlaybackQueueRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link AddItemToPlaybackQueueRequest.Builder}.
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
     * @return A {@link AddItemToPlaybackQueueRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.isEmpty());
      return setQueryParameter("device_id", device_id);
    }

    /**
     * The uri setter.
     *
     * @param uri Required. The uri of the item to add to the queue.
     *            Must be a track or an episode uri.
     * @return A {@link AddItemToPlaybackQueueRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder uri(final String uri) {
      assert (uri != null);
      assert (!uri.isEmpty());
      return setQueryParameter("uri", uri);
    }


    /**
     * The request build method.
     *
     * @return A custom {@link AddItemToPlaybackQueueRequest}.
     */
    @Override
    public AddItemToPlaybackQueueRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/player/queue");
      return new AddItemToPlaybackQueueRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }

}
