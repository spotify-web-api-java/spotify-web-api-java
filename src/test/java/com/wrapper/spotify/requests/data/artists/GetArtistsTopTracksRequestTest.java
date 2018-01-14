package com.wrapper.spotify.requests.data.artists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetArtistsTopTracksRequestTest implements ITest<Track[]> {
  private final GetArtistsTopTracksRequest successRequest = SPOTIFY_API.getArtistsTopTracks("id", CountryCode.UNDEFINED)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/artists/GetArtistsTopTracksRequest.json"))
          .build();

  public GetArtistsTopTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Track[]) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Track[] tracks) {
    assertEquals(
            10,
            tracks.length);
  }
}
