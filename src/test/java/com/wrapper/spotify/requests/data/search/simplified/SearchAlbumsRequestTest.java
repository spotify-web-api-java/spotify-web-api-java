package com.wrapper.spotify.requests.data.search.simplified;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SearchAlbumsRequestTest implements ITest<Paging<AlbumSimplified>> {
  private final SearchAlbumsRequest defaultRequest = SPOTIFY_API
          .searchAlbums("q")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/search/simplified/SearchAlbumsRequest.json"))
          .build();

  public SearchAlbumsRequestTest() throws Exception {
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<AlbumSimplified>) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<AlbumSimplified> albumSimplifiedPaging) {
    assertEquals(
            "https://api.spotify.com/v1/search?query=Muse&type=album&market=US&offset=5&limit=10",
            albumSimplifiedPaging.getHref());
    assertEquals(
            10,
            albumSimplifiedPaging.getItems().length);
    assertEquals(
            10,
            (int) albumSimplifiedPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/search?query=Muse&type=album&market=US&offset=15&limit=10",
            albumSimplifiedPaging.getNext());
    assertEquals(
            5,
            (int) albumSimplifiedPaging.getOffset());
    assertEquals(
            "https://api.spotify.com/v1/search?query=Muse&type=album&market=US&offset=0&limit=10",
            albumSimplifiedPaging.getPrevious());
    assertEquals(
            1032,
            (int) albumSimplifiedPaging.getTotal());
  }
}
