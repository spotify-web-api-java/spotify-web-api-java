package com.wrapper.spotify.requests.data.browse.miscellaneous;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetAvailableGenreSeedsRequestTest implements ITest<String[]> {
  private final GetAvailableGenreSeedsRequest successRequest = SPOTIFY_API.getAvailableGenreSeeds()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/browse/miscellaneous/GetAvailableGenreSeedsRequest.json"))
          .build();

  public GetAvailableGenreSeedsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((String[]) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final String[] strings) {
    assertEquals(
            126,
            strings.length);
  }
}
