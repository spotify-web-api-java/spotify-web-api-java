package com.wrapper.spotify.requests.data.search;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SearchResult;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.apache.hc.core5.http.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchItemRequestTest extends AbstractDataTest<SearchResult> {
  private final SearchItemRequest defaultRequest = SPOTIFY_API
    .searchItem(Q, ModelObjectType.ARTIST.getType())
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/search/SearchItemRequest.json"))
    .limit(LIMIT)
    .market(MARKET)
    .offset(OFFSET)
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
