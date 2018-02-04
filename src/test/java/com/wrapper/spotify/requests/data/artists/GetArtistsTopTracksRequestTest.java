package com.wrapper.spotify.requests.data.artists;

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
public class GetArtistsTopTracksRequestTest extends AbstractDataTest<Track[]> {
  private final GetArtistsTopTracksRequest defaultRequest = SPOTIFY_API.getArtistsTopTracks(ID_ARTIST, COUNTRY)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/artists/GetArtistsTopTracksRequest.json"))
          .build();

  public GetArtistsTopTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/top-tracks?country=SE",
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
            1,
            tracks.length);
  }
}
