package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersCurrentlyPlayingTrackRequestTest implements ITest<CurrentlyPlaying> {
  private final GetUsersCurrentlyPlayingTrackRequest successRequest = SPOTIFY_API
          .getUsersCurrentlyPlayingTrack()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetUsersCurrentlyPlayingTrackRequest.json"))
          .build();

  private final GetUsersCurrentlyPlayingTrackRequest failureRequest = SPOTIFY_API
          .getUsersCurrentlyPlayingTrack()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetUsersCurrentlyPlayingTrackRequest_None.json"))
          .build();

  public GetUsersCurrentlyPlayingTrackRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((CurrentlyPlaying) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final CurrentlyPlaying currentlyPlaying) {
    assertNull(
            currentlyPlaying.getContext());
    assertEquals(
            1516669900630L,
            (long) currentlyPlaying.getTimestamp());
    assertEquals(
            78810,
            (int) currentlyPlaying.getProgress_ms());
    assertFalse(
            currentlyPlaying.getIs_playing());
    assertNotNull(
            currentlyPlaying.getItem());
  }

  @Test
  public void shouldFail_sync() throws IOException, SpotifyWebApiException {
    shouldFail(failureRequest.execute());
  }

  @Test
  public void shouldFail_async() throws ExecutionException, InterruptedException {
    shouldFail((CurrentlyPlaying) failureRequest.executeAsync().get());
  }

  public void shouldFail(final CurrentlyPlaying currentlyPlaying) {
    assertNull(
            currentlyPlaying);
  }
}
