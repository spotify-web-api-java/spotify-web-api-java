package se.michaelthelin.spotify.requests.data.search.simplified;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.ShowSimplified;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchShowsRequestTest extends AbstractDataTest<Paging<ShowSimplified>> {
  private final SearchShowsRequest defaultRequest = ITest.SPOTIFY_API.searchShows(ITest.Q)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/search/simplified/SearchShowsRequest.json"))
    .limit(ITest.LIMIT)
    .market(ITest.MARKET)
    .offset(ITest.OFFSET)
    .includeExternal("audio")
    .build();

  public SearchShowsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/search?q=Abba&limit=10&market=SE&offset=0&include_external=audio&type=show",
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

  public void shouldReturnDefault(final Paging<ShowSimplified> artistPaging) {
    assertEquals(
      "https://api.spotify.com/v1/search?query=Muse&type=show&market=US&offset=5&limit=10",
      artistPaging.getHref());
    assertEquals(
      10,
      artistPaging.getItems().length);
    assertEquals(
      10,
      (int) artistPaging.getLimit());
    assertEquals(
      "https://api.spotify.com/v1/search?query=Muse&type=show&market=US&offset=15&limit=10",
      artistPaging.getNext());
    assertEquals(
      5,
      (int) artistPaging.getOffset());
    assertEquals(
      "https://api.spotify.com/v1/search?query=Muse&type=show&market=US&offset=0&limit=10",
      artistPaging.getPrevious());
    assertEquals(
      2101,
      (int) artistPaging.getTotal());
  }
}
