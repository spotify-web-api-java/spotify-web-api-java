package se.michaelthelin.spotify.requests.data.player;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.PlaybackQueue;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

/**
 * Get the list of objects that make up the user's queue.
 * <p>
 * Returns the items from the current user’s playback queue, including the currently playing item.
 * <p>
 *  The endpoint does not support paging since the queue is not expected to be large.
 *  Therefore, the request will return a {@link PlaybackQueue} object including a List of {@link se.michaelthelin.spotify.model_objects.IPlaylistItem}.
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
   * Get the user's current playback queue.
   *
   * @return An {@link PlaybackQueue} object including a List of {@link se.michaelthelin.spotify.model_objects.IPlaylistItem}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   * @throws ParseException         In case of an error parsing the response.
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
