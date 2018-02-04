package com.wrapper.spotify.requests.data.search.simplified;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SearchPlaylistsRequestTest extends AbstractDataTest<Paging<PlaylistSimplified>> {
  private final SearchPlaylistsRequest defaultRequest = SPOTIFY_API
          .searchPlaylists(Q)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/search/simplified/SearchPlaylistsRequest.json"))
          .limit(LIMIT)
          .market(MARKET)
          .offset(OFFSET)
          .build();

  public SearchPlaylistsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/search?q=Abba&limit=10&market=SE&offset=0&type=playlist",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<PlaylistSimplified>) defaultRequest.executeAsync().get());
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
