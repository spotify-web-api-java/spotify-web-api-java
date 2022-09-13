package se.michaelthelin.spotify.requests.data.browse.miscellaneous;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAvailableGenreSeedsRequestTest extends AbstractDataTest<String[]> {
  private final GetAvailableGenreSeedsRequest defaultRequest = SPOTIFY_API.getAvailableGenreSeeds()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/browse/miscellaneous/GetAvailableGenreSeedsRequest.json"))
    .build();

  public GetAvailableGenreSeedsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/recommendations/available-genre-seeds",
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

  public void shouldReturnDefault(final String[] strings) {
    assertEquals(
      126,
      strings.length);
  }
}
