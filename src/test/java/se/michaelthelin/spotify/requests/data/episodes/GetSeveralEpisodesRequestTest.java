package se.michaelthelin.spotify.requests.data.episodes;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetSeveralEpisodesRequestTest extends AbstractDataTest<Episode[]> {
  private final GetSeveralEpisodesRequest defaultRequest = ITest.SPOTIFY_API
    .getSeveralEpisodes(ITest.ID_EPISODE)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/episodes/GetSeveralEpisodesRequest.json"))
    .build();

  public GetSeveralEpisodesRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/episodes?ids=4GI3dxEafwap1sFiTGPKd1",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Episode[] episodes) {
    assertNotNull(episodes);
    assertEquals(1, episodes.length);
    assertEquals("4GI3dxEafwap1sFiTGPKd1", episodes[0].getId());
  }
}
