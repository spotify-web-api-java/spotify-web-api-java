package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetInformationAboutUsersCurrentPlaybackRequestTest implements ITest<CurrentlyPlayingContext> {
  private final GetInformationAboutUsersCurrentPlaybackRequest successRequest = SPOTIFY_API
          .getInformationAboutUsersCurrentPlayback()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetInformationAboutUsersCurrentPlaybackRequest.json"))
          .build();

  private final GetInformationAboutUsersCurrentPlaybackRequest failureRequest = SPOTIFY_API
          .getInformationAboutUsersCurrentPlayback()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetInformationAboutUsersCurrentPlaybackRequest_None.json"))
          .build();

  public GetInformationAboutUsersCurrentPlaybackRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((CurrentlyPlayingContext) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final CurrentlyPlayingContext currentlyPlayingContext) {
    assertEquals(
            1516669848357L,
            (long) currentlyPlayingContext.getTimestamp());
    assertEquals(
            69937,
            (int) currentlyPlayingContext.getProgress_ms());
    assertNotNull(
            currentlyPlayingContext.getItem());
    assertNull(
            currentlyPlayingContext.getContext());
    assertNotNull(
            currentlyPlayingContext.getDevice());
    assertEquals(
            "off",
            currentlyPlayingContext.getRepeat_state());
    assertFalse(
            currentlyPlayingContext.getShuffle_state());
  }

  @Test
  public void shouldFail_sync() throws IOException, SpotifyWebApiException {
    shouldFail(failureRequest.execute());
  }

  @Test
  public void shouldFail_async() throws ExecutionException, InterruptedException {
    shouldFail((CurrentlyPlayingContext) failureRequest.executeAsync().get());
  }

  public void shouldFail(final CurrentlyPlayingContext currentlyPlayingContext) {
    assertNull(
            currentlyPlayingContext);
  }
}
