package com.wrapper.spotify.requests.data.search.simplified;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SearchPlaylistsRequestTest implements ITest<Paging<PlaylistSimplified>> {
  private final SearchPlaylistsRequest successRequest = SPOTIFY_API
          .searchPlaylists("q")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/search/simplified/SearchPlaylistsRequest.json"))
          .build();

  public SearchPlaylistsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Paging<PlaylistSimplified>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Paging<PlaylistSimplified> playlistSimplifiedPaging) {
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
