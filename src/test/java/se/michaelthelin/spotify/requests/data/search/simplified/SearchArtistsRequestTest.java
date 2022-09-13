package se.michaelthelin.spotify.requests.data.search.simplified;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchArtistsRequestTest extends AbstractDataTest<Paging<Artist>> {
  private final SearchArtistsRequest defaultRequest = ITest.SPOTIFY_API.searchArtists(ITest.Q)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/search/simplified/SearchArtistsRequest.json"))
    .limit(ITest.LIMIT)
    .market(ITest.MARKET)
    .offset(ITest.OFFSET)
    .includeExternal("audio")
    .build();

  public SearchArtistsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/search?q=Abba&limit=10&market=SE&offset=0&include_external=audio&type=artist",
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

  public void shouldReturnDefault(final Paging<Artist> artistPaging) {
    assertEquals(
      "https://api.spotify.com/v1/search?query=Muse&type=artist&market=US&offset=5&limit=10",
      artistPaging.getHref());
    assertEquals(
      10,
      artistPaging.getItems().length);
    assertEquals(
      10,
      (int) artistPaging.getLimit());
    assertEquals(
      "https://api.spotify.com/v1/search?query=Muse&type=artist&market=US&offset=15&limit=10",
      artistPaging.getNext());
    assertEquals(
      5,
      (int) artistPaging.getOffset());
    assertEquals(
      "https://api.spotify.com/v1/search?query=Muse&type=artist&market=US&offset=0&limit=10",
      artistPaging.getPrevious());
    assertEquals(
      171,
      (int) artistPaging.getTotal());
  }
}
