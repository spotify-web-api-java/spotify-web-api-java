package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.apache.hc.core5.http.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.wrapper.spotify.Assertions.assertHasBodyParameter;
import static com.wrapper.spotify.Assertions.assertHasHeader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class RemoveUsersSavedTracksRequestTest extends AbstractDataTest<String> {
  private final RemoveUsersSavedTracksRequest defaultRequest = SPOTIFY_API
    .removeUsersSavedTracks(ID_TRACK, ID_TRACK)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .build();
  private final RemoveUsersSavedTracksRequest bodyRequest = SPOTIFY_API
    .removeUsersSavedTracks(TRACKS)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .build();

  public RemoveUsersSavedTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/tracks?ids=01iyCAUm8EvOFqVWYJ3dVX%2C01iyCAUm8EvOFqVWYJ3dVX",
      defaultRequest.getUri().toString());

    assertHasAuthorizationHeader(bodyRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(bodyRequest,
      "ids",
      TRACKS);
    assertEquals("https://api.spotify.com:443/v1/me/tracks",
      bodyRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final String string) {
    assertNull(
      string);
  }
}
