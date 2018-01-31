package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CheckUsersSavedAlbumsRequestTest extends AbstractDataTest<Boolean[]> {
  private final CheckUsersSavedAlbumsRequest defaultRequest = SPOTIFY_API.checkUsersSavedAlbums(ID_ALBUM, ID_ALBUM)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/library/CheckUsersSavedAlbumsRequest.json"))
          .build();

  public CheckUsersSavedAlbumsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/me/albums/contains?ids=5zT1JLIj9E57p3e1rFm9Uq%2C5zT1JLIj9E57p3e1rFm9Uq",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Boolean[]) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Boolean[] booleans) {
    assertEquals(
            2,
            booleans.length);
  }
}
