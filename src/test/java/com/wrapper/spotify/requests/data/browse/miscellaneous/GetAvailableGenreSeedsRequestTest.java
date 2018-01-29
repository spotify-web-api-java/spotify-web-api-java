package com.wrapper.spotify.requests.data.browse.miscellaneous;

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
public class GetAvailableGenreSeedsRequestTest implements ITest<String[]> {
  private final GetAvailableGenreSeedsRequest defaultRequest = SPOTIFY_API.getAvailableGenreSeeds()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/browse/miscellaneous/GetAvailableGenreSeedsRequest.json"))
          .build();

  public GetAvailableGenreSeedsRequestTest() throws Exception {
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((String[]) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final String[] strings) {
    assertEquals(
            126,
            strings.length);
  }
}
