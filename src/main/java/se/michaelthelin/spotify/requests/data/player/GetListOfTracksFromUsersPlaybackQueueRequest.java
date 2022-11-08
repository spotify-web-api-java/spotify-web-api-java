package se.michaelthelin.spotify.requests.data.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.PlaybackQueue;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

@JsonDeserialize(builder = GetListOfTracksFromUsersPlaybackQueueRequest.Builder.class)
public class GetListOfTracksFromUsersPlaybackQueueRequest extends AbstractDataRequest<PlaybackQueue> {

  private GetListOfTracksFromUsersPlaybackQueueRequest(final Builder builder) {
    super(builder);
  }

  @Override
  public PlaybackQueue execute() throws IOException, SpotifyWebApiException, ParseException {
    return new PlaybackQueue.JsonUtil().createModelObject(getJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<PlaybackQueue, GetListOfTracksFromUsersPlaybackQueueRequest.Builder> {

    /**
     * Create a new {@link GetListOfTracksFromUsersPlaybackQueueRequest.Builder}.
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
     * @return A custom {@link GetListOfTracksFromUsersPlaybackQueueRequest}.
     */
    @Override
    public GetListOfTracksFromUsersPlaybackQueueRequest build() {
      setPath("/v1/me/player/queue");
      return new GetListOfTracksFromUsersPlaybackQueueRequest(this);
    }

    @Override
    protected GetListOfTracksFromUsersPlaybackQueueRequest.Builder self() {
      return this;
    }
  }
}
