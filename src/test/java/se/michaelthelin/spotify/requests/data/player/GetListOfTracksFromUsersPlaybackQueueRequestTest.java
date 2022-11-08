package se.michaelthelin.spotify.requests.data.player;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.PlaybackQueue;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class GetListOfTracksFromUsersPlaybackQueueRequestTest extends AbstractDataTest<PlaybackQueue> {

  private final GetListOfTracksFromUsersPlaybackQueueRequest defaultRequest = ITest.SPOTIFY_API
    .getListOfTracksFromUsersPlaybackQueue()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson("requests/data/player/GetListOfTracksFromUsersPlaybackQueueRequest.json"))
    .build();

  public GetListOfTracksFromUsersPlaybackQueueRequestTest() throws Exception {
    assertEquals("https://api.spotify.com:443/v1/me/player/queue",
      defaultRequest.getUri().toString());
  }


  @Override
  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/player/queue",
      defaultRequest.getUri().toString());
  }

  @Override
  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Override
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  @Override
  public void shouldReturnDefault(PlaybackQueue type) {
    assertNotNull(type.getQueue());
    assertEquals(type.getQueue().size(), 1);
    assertEquals(type.getQueue().get(0).getName(), "string");
  }
}
