package se.michaelthelin.spotify.requests.data.markets;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetAvailableMarketsRequestTest extends AbstractDataTest<String[]> {
  private final GetAvailableMarketsRequest defaultRequest = ITest.SPOTIFY_API
    .getAvailableMarkets()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/markets/GetAvailableMarketsRequest.json"))
    .build();

  public GetAvailableMarketsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/markets",
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

  public void shouldReturnDefault(final String[] markets) {
    assertNotNull(markets);
    assertTrue(markets.length > 0);
    assertEquals("AD", markets[0]);
  }
}
