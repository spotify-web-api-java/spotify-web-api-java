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
public class SetVolumeForUsersPlaybackRequestTest implements ITest<String> {
  private final SetVolumeForUsersPlaybackRequest defaultRequest = SPOTIFY_API
          .setVolumeForUsersPlayback(100)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/SetVolumeForUsersPlaybackRequest.json"))
          .build();

  public SetVolumeForUsersPlaybackRequestTest() throws Exception {
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((String) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final String string) {
    assertNull(
            string);
  }
}
