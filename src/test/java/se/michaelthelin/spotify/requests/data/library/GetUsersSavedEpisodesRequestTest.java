package se.michaelthelin.spotify.requests.data.library;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.miscellaneous.SavedEpisode;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetUsersSavedEpisodesRequestTest extends AbstractDataTest<Paging<SavedEpisode>> {
  private final GetUsersSavedEpisodesRequest defaultRequest = ITest.SPOTIFY_API.getUsersSavedEpisodes()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/library/GetUsersSavedEpisodesRequest.json"))
    .limit(ITest.LIMIT)
    .offset(ITest.OFFSET)
    .market(ITest.MARKET)
    .build();

  public GetUsersSavedEpisodesRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/episodes?limit=10&offset=0&market=SE",
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

  public void shouldReturnDefault(final Paging<SavedEpisode> savedEpisodesPaging) {
    assertEquals(
      "https://api.spotify.com/v1/me/episodes?offset=0&limit=20&market=SE",
      savedEpisodesPaging.getHref());
    assertEquals(
      2,
      savedEpisodesPaging.getItems().length);
    assertEquals(
      20,
      (int) savedEpisodesPaging.getLimit());
    assertNull(
      savedEpisodesPaging.getNext());
    assertEquals(
      0,
      (int) savedEpisodesPaging.getOffset());
    assertNull(
      savedEpisodesPaging.getPrevious());
    assertEquals(
      2,
      (int) savedEpisodesPaging.getTotal());

    SavedEpisode[] savedEpisodes = savedEpisodesPaging.getItems();
    assertEquals(
      Date.from(Instant.parse("2023-09-26T00:10:20Z")),
      savedEpisodes[0].getAddedAt());
    assertEquals(
      "#2038 - Trae Tha Truth",
      savedEpisodes[0].getEpisode().getName());
  }
}
