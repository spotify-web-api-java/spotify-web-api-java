package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetArtistsRelatedArtistsRequestTest extends AbstractDataTest<Artist[]> {
  private final GetArtistsRelatedArtistsRequest defaultRequest = SPOTIFY_API.getArtistsRelatedArtists(ID_ARTIST)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/artists/GetArtistsRelatedArtistsRequest.json"))
          .build();

  public GetArtistsRelatedArtistsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/related-artists",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Artist[]) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Artist[] artists) {
    assertEquals(
            1,
            artists.length);
  }
}
