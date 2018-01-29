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
  private final GetUsersCurrentlyPlayingTrackRequest defaultRequest = SPOTIFY_API
          .getUsersCurrentlyPlayingTrack()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetUsersCurrentlyPlayingTrackRequest.json"))
          .build();

  private final GetUsersCurrentlyPlayingTrackRequest emptyRequest = SPOTIFY_API
          .getUsersCurrentlyPlayingTrack()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetUsersCurrentlyPlayingTrackRequest_None.json"))
          .build();

  public GetUsersCurrentlyPlayingTrackRequestTest() throws Exception {
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((CurrentlyPlaying) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final CurrentlyPlaying currentlyPlaying) {
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
  public void shouldReturnEmpty_sync() throws IOException, SpotifyWebApiException {
    shouldReturnEmpty(emptyRequest.execute());
  }

  @Test
  public void shouldReturnEmpty_async() throws ExecutionException, InterruptedException {
    shouldReturnEmpty((CurrentlyPlaying) emptyRequest.executeAsync().get());
  }

  public void shouldReturnEmpty(final CurrentlyPlaying currentlyPlaying) {
    assertNull(
            currentlyPlaying);
  }
}
