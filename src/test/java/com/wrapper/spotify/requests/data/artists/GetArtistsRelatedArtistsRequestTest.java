package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetArtistsRelatedArtistsRequestTest implements ITest<Artist[]> {
  private final GetArtistsRelatedArtistsRequest successRequest = SPOTIFY_API.getArtistsRelatedArtists("id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/artists/GetArtistsRelatedArtistsRequest.json"))
          .build();

  public GetArtistsRelatedArtistsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Artist[]) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Artist[] artists) {
    assertEquals(
            1,
            artists.length);
  }
}
