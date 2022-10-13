package se.michaelthelin.spotify.requests.data.shows;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.EpisodeSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetShowsEpisodesRequestTest extends AbstractDataTest<Paging<EpisodeSimplified>> {

  private final GetShowsEpisodesRequest defaultRequest = SPOTIFY_API.getShowEpisodes(ID_SHOW)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/shows/GetShowsEpisodesRequest.json"))
    .offset(OFFSET)
    .limit(LIMIT)
    .market(MARKET)
    .build();

  public GetShowsEpisodesRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/shows/5AvwZVawapvyhJUIx71pdJ/episodes?offset=0&limit=10&market=SE",
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

  public void shouldReturnDefault(final Paging<EpisodeSimplified> episodeSimplifiedPaging) {
    assertEquals(
      "https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ/episodes?offset=1&limit=2",
      episodeSimplifiedPaging.getHref());
    assertEquals(
      2,
      episodeSimplifiedPaging.getItems().length);
    assertEquals(
      2,
      (int) episodeSimplifiedPaging.getLimit());
    assertEquals(
      "https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ/episodes?offset=3&limit=2",
      episodeSimplifiedPaging.getNext());
    assertEquals(
      1,
      (int) episodeSimplifiedPaging.getOffset());
    assertEquals(
      "https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ/episodes?offset=0&limit=2",
      episodeSimplifiedPaging.getPrevious());
    assertEquals(
      499,
      (int) episodeSimplifiedPaging.getTotal());
  }
}
