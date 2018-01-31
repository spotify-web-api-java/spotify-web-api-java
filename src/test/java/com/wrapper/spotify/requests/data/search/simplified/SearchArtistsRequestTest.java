package com.wrapper.spotify.requests.data.search.simplified;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SearchArtistsRequestTest extends AbstractDataTest<Paging<Artist>> {
  private final SearchArtistsRequest defaultRequest = SPOTIFY_API.searchArtists(Q)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/search/simplified/SearchArtistsRequest.json"))
          .limit(LIMIT)
          .market(MARKET)
          .offset(OFFSET)
          .build();

  public SearchArtistsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/search?q=Abba&limit=10&market=SE&offset=0&type=artist",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<Artist>) defaultRequest.executeAsync().get());
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
