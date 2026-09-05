package se.michaelthelin.spotify.requests.data.search.simplified;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AudiobookSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchAudiobooksRequestTest extends AbstractDataTest<Paging<AudiobookSimplified>> {
  private final SearchAudiobooksRequest defaultRequest = ITest.SPOTIFY_API.searchAudiobooks(ITest.Q)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/search/simplified/SearchAudiobooksRequest.json"))
    .limit(ITest.LIMIT)
    .market(ITest.MARKET)
    .offset(ITest.OFFSET)
    .build();

  public SearchAudiobooksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/search?q=Abba&limit=10&market=SE&offset=0&type=audiobook",
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

  public void shouldReturnDefault(final Paging<AudiobookSimplified> audiobookPaging) {
    assertEquals(
      "https://api.spotify.com/v1/search?query=Moby&type=audiobook&market=US&offset=5&limit=10",
      audiobookPaging.getHref());
    assertEquals(
      1,
      audiobookPaging.getItems().length);
    assertEquals(
      10,
      (int) audiobookPaging.getLimit());
    assertEquals(
      "https://api.spotify.com/v1/search?query=Moby&type=audiobook&market=US&offset=15&limit=10",
      audiobookPaging.getNext());
    assertEquals(
      5,
      (int) audiobookPaging.getOffset());
    assertEquals(
      "https://api.spotify.com/v1/search?query=Moby&type=audiobook&market=US&offset=0&limit=10",
      audiobookPaging.getPrevious());
    assertEquals(
      1,
      (int) audiobookPaging.getTotal());
  }
}
