package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RemoveAlbumsForCurrentUserRequestTest implements ITest<String> {
  private final RemoveAlbumsForCurrentUserRequest successRequest = SPOTIFY_API
          .removeAlbumsForCurrentUser("id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/library/RemoveAlbumsForCurrentUserRequest.json"))
          .build();

  public RemoveAlbumsForCurrentUserRequestTest() throws Exception {
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
    assertEquals(
            "",
            string);
  }
}
