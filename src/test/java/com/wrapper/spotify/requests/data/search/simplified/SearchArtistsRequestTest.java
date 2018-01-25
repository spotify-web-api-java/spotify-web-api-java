package com.wrapper.spotify.requests.data.search.simplified;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SearchArtistsRequestTest implements ITest<Paging<Artist>> {
  private final SearchArtistsRequest successRequest = SPOTIFY_API
          .searchArtists("q")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/search/simplified/SearchArtistsRequest.json"))
          .build();

  public SearchArtistsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Paging<Artist>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Paging<Artist> artistPaging) {
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
