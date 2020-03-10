package com.wrapper.spotify.requests.data.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * Add a track or an episode to the end of the user's current playback queue.
 */
@JsonDeserialize(builder = AddItemToUsersPlaybackQueueRequest.Builder.class)
public class AddItemToUsersPlaybackQueueRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link AddItemToUsersPlaybackQueueRequest} constructor.
   *
   * @param builder A {@link AddItemToUsersPlaybackQueueRequest.Builder}.
   */
  private AddItemToUsersPlaybackQueueRequest(final Builder builder){
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
    SpotifyWebApiException {
    return postJson();
  }

  /**
   * Builder class for building a {@link AddItemToUsersPlaybackQueueRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder>{

    /**
     * Create a new {@link AddItemToUsersPlaybackQueueRequest.Builder}.
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
     * @return A {@link AddItemToUsersPlaybackQueueRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.equals(""));
      return setQueryParameter("device_id", device_id);
    }

    /**
     * The uri setter.
     *
     * @param uri Required. The uri of the item to add to the queue.
     *            Must be a track or an episode uri.
     * @return A {@link AddItemToUsersPlaybackQueueRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder uri(final String uri){
      assert (uri != null);
      assert (!uri.equals(""));
      return setQueryParameter("uri", uri);
    }


    /**
     * The request build method.
     *
     * @return A custom {@link AddItemToUsersPlaybackQueueRequest}.
     */
    @Override
    public AddItemToUsersPlaybackQueueRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/player/queue");
      return new AddItemToUsersPlaybackQueueRequest(this);
    }
  }

}
