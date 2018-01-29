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
  private final SearchItemRequest defaultRequest = SPOTIFY_API
          .searchItem("q", "track")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/search/SearchItemRequest.json"))
          .build();

  public SearchItemRequestTest() throws Exception {
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((SearchResult) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final SearchResult searchResult) {
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
