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
public class CheckUsersSavedTracksRequestTest extends AbstractDataTest<Boolean[]> {
  private final CheckUsersSavedTracksRequest defaultRequest = SPOTIFY_API.checkUsersSavedTracks(ID_TRACK, ID_TRACK)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/library/CheckUsersSavedTracksRequest.json"))
          .build();

  public CheckUsersSavedTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/me/tracks/contains?ids=01iyCAUm8EvOFqVWYJ3dVX%2C01iyCAUm8EvOFqVWYJ3dVX",
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
