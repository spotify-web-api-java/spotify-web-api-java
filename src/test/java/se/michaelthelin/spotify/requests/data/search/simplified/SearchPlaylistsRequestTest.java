package se.michaelthelin.spotify.requests.data.search.simplified;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchPlaylistsRequestTest extends AbstractDataTest<Paging<PlaylistSimplified>> {
  private final SearchPlaylistsRequest defaultRequest = ITest.SPOTIFY_API
    .searchPlaylists(ITest.Q)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/search/simplified/SearchPlaylistsRequest.json"))
    .limit(ITest.LIMIT)
    .market(ITest.MARKET)
    .offset(ITest.OFFSET)
    .includeExternal("audio")
    .build();

  public SearchPlaylistsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/search?q=Abba&limit=10&market=SE&offset=0&include_external=audio&type=playlist",
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

  public void shouldReturnDefault(final Paging<PlaylistSimplified> playlistSimplifiedPaging) {
    assertEquals(
      "https://api.spotify.com/v1/search?query=Muse&type=playlist&market=US&offset=5&limit=10",
      playlistSimplifiedPaging.getHref());
    assertEquals(
      10,
      playlistSimplifiedPaging.getItems().length);
    assertEquals(
      10,
      (int) playlistSimplifiedPaging.getLimit());
    assertEquals(
      "https://api.spotify.com/v1/search?query=Muse&type=playlist&market=US&offset=15&limit=10",
      playlistSimplifiedPaging.getNext());
    assertEquals(
      5,
      (int) playlistSimplifiedPaging.getOffset());
    assertEquals(
      "https://api.spotify.com/v1/search?query=Muse&type=playlist&market=US&offset=0&limit=10",
      playlistSimplifiedPaging.getPrevious());
    assertEquals(
      21476,
      (int) playlistSimplifiedPaging.getTotal());
  }
}
