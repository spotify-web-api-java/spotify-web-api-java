package com.wrapper.spotify.requests.data.browse;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetRecommendationsRequestTest implements ITest<Recommendations> {
  private final GetRecommendationsRequest defaultRequest = SPOTIFY_API.getRecommendations()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/browse/GetRecommendationsRequest.json"))
          .build();

  public GetRecommendationsRequestTest() throws Exception {
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Recommendations) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Recommendations recommendations) {
    assertEquals(
            2,
            recommendations.getSeeds().length);
    assertEquals(
            2,
            recommendations.getTracks().length);
  }
}
