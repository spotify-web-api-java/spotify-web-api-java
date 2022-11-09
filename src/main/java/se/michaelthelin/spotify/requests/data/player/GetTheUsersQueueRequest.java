package se.michaelthelin.spotify.requests.data.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.PlaybackQueue;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get the list of objects that make up the user's queue.
 * <p>
 * Returns the tracks from the current user’s playback queue. Does not include the current playing track.
 * <p>
 *  The endpoint does not support paging since the queue is not expected to be large.
 *  Therefore, the request will return a {@link PlaybackQueue} object including a List of {@link se.michaelthelin.spotify.model_objects.specification.Track}.
 */
@JsonDeserialize(builder = GetTheUsersQueueRequest.Builder.class)
public class GetTheUsersQueueRequest extends AbstractDataRequest<PlaybackQueue> {

  /**
   * The private {@link GetTheUsersQueueRequest} constructor.
   *
   * @param builder A {@link GetTheUsersQueueRequest.Builder}.
   */
  private GetTheUsersQueueRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an user's current playback queue.
   *
   * @return An {@link PlaybackQueue} object including a List of {@link se.michaelthelin.spotify.model_objects.specification.Track}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public PlaybackQueue execute() throws IOException, SpotifyWebApiException, ParseException {
    return new PlaybackQueue.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetTheUsersQueueRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<PlaybackQueue, GetTheUsersQueueRequest.Builder> {

    /**
     * Create a new {@link GetTheUsersQueueRequest.Builder}.
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
     * The request build method.
     *
     * @return A custom {@link GetTheUsersQueueRequest}.
     */
    @Override
    public GetTheUsersQueueRequest build() {
      setPath("/v1/me/player/queue");
      return new GetTheUsersQueueRequest(this);
    }

    @Override
    protected GetTheUsersQueueRequest.Builder self() {
      return this;
    }
  }
}
