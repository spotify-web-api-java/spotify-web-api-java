package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class PauseUsersPlaybackRequestTest implements ITest<String> {
  private final PauseUsersPlaybackRequest successRequest = SPOTIFY_API
          .pauseUsersPlayback()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/PauseUsersPlaybackRequest.json"))
          .build();

  public PauseUsersPlaybackRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((String) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final String string) {
    assertNull(
            string);
  }
}