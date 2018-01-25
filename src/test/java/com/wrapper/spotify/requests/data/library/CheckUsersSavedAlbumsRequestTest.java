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
public class CheckUsersSavedAlbumsRequestTest implements ITest<Boolean[]> {
  private final CheckUsersSavedAlbumsRequest successRequest = SPOTIFY_API
          .checkUsersSavedAlbums("id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/library/CheckUsersSavedAlbumsRequest.json"))
          .build();

  public CheckUsersSavedAlbumsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Boolean[]) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Boolean[] booleans) {
    assertEquals(
            2,
            booleans.length);
  }
}
