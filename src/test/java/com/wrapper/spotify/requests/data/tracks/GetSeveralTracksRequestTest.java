package com.wrapper.spotify.requests.data.tracks;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetSeveralTracksRequestTest extends AbstractDataTest<Track[]> {
  private final GetSeveralTracksRequest defaultRequest = SPOTIFY_API
          .getSeveralTracks(ID_TRACK)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/tracks/GetSeveralTracksRequest.json"))
          .market(MARKET)
          .build();

  public GetSeveralTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/tracks?ids=01iyCAUm8EvOFqVWYJ3dVX&market=SE",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Track[]) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Track[] tracks) {
    assertEquals(
            2,
            tracks.length);
  }
}
