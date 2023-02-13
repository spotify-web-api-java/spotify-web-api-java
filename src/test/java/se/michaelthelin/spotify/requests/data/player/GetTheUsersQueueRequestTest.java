package se.michaelthelin.spotify.requests.data.player;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.PlaybackQueue;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class GetTheUsersQueueRequestTest extends AbstractDataTest<PlaybackQueue> {

  private final GetTheUsersQueueRequest defaultRequest = ITest.SPOTIFY_API
    .getTheUsersQueue()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson("requests/data/player/GetTheUsersQueueRequest.json"))
    .build();

  public GetTheUsersQueueRequestTest() throws Exception {
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
    assertNotNull(type.getCurrentlyPlaying());
    assertEquals(type.getCurrentlyPlaying().getName(), "Borders");
    assertNotNull(type.getQueue());
    assertEquals(type.getQueue().size(), 20);
    assertEquals(type.getQueue().get(0).getName(), "AUSGABE VIERUNDSIEBZIG");
    assertEquals(type.getQueue().get(0).getType(), ModelObjectType.EPISODE);
    assertEquals(type.getQueue().get(1).getName(), "Helvegen");
    assertEquals(type.getQueue().get(1).getType(), ModelObjectType.TRACK);
  }
}
