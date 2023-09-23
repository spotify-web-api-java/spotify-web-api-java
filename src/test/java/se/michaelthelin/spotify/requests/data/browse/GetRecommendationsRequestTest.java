package se.michaelthelin.spotify.requests.data.browse;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Recommendations;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetRecommendationsRequestTest extends AbstractDataTest<Recommendations> {
  private final GetRecommendationsRequest defaultRequest = SPOTIFY_API.getRecommendations()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/browse/GetRecommendationsRequest.json"))
    .limit(LIMIT)
    .market(MARKET)
    .max_popularity(MAX_POPULARITY)
    .min_popularity(MIN_POPULARITY)
    .seed_artists(SEED_ARTISTS)
    .seed_genres(SEED_GENRES)
    .seed_tracks(SEED_TRACKS)
    .target_popularity(TARGET_POPULARITY)
    .build();

  public GetRecommendationsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/recommendations?limit=10&market=SE&max_popularity=50&min_popularity=10&seed_artists=0LcJLqbBmaGUft1e9Mm8HV&seed_genres=electro&seed_tracks=01iyCAUm8EvOFqVWYJ3dVX&target_popularity=20",
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

  public void shouldReturnDefault(final Recommendations recommendations) {
    assertEquals(
      3,
      recommendations.getSeeds().length);
    assertEquals(
      10,
      recommendations.getTracks().length);
  }
}
