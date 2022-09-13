package se.michaelthelin.spotify.requests.data.search;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SearchItemRequestTest extends AbstractDataTest<SearchResult> {
  private final SearchItemRequest defaultRequest = ITest.SPOTIFY_API
    .searchItem(ITest.Q, ModelObjectType.ARTIST.getType())
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/search/SearchItemRequest.json"))
    .limit(ITest.LIMIT)
    .market(ITest.MARKET)
    .offset(ITest.OFFSET)
    .includeExternal("audio")
    .build();

  public SearchItemRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/search?q=Abba&type=artist&limit=10&market=SE&offset=0&include_external=audio",
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

  public void shouldReturnDefault(final SearchResult searchResult) {
    assertNull(
      searchResult.getAlbums());
    assertNotNull(
      searchResult.getArtists());
    assertNull(
      searchResult.getEpisodes());
    assertNull(
      searchResult.getPlaylists());
    assertNull(
      searchResult.getShows());
    assertNotNull(
      searchResult.getTracks());
  }
}
