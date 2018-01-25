package com.wrapper.spotify.requests.data.search;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class SearchItemRequestTest implements ITest<SearchResult> {
  private final SearchItemRequest successRequest = SPOTIFY_API
          .searchItem("q", "track")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/search/SearchItemRequest.json"))
          .build();

  public SearchItemRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((SearchResult) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final SearchResult searchResult) {
    assertNull(
            searchResult.getAlbums());
    assertNotNull(
            searchResult.getArtists());
    assertNull(
            searchResult.getPlaylists());
    assertNotNull(
            searchResult.getTracks());
  }
}
