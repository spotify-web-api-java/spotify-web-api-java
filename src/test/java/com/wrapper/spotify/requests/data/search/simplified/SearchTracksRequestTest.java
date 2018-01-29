package com.wrapper.spotify.requests.data.search.simplified;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SearchTracksRequestTest implements ITest<Paging<Track>> {
  private final SearchTracksRequest defaultRequest = SPOTIFY_API
          .searchTracks("q")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/search/simplified/SearchTracksRequest.json"))
          .build();

  public SearchTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<Track>) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<Track> trackPaging) {
    assertEquals(
            "https://api.spotify.com/v1/search?query=Muse&type=track&market=US&offset=5&limit=10",
            trackPaging.getHref());
    assertEquals(
            10,
            trackPaging.getItems().length);
    assertEquals(
            10,
            (int) trackPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/search?query=Muse&type=track&market=US&offset=15&limit=10",
            trackPaging.getNext());
    assertEquals(
            5,
            (int) trackPaging.getOffset());
    assertEquals(
            "https://api.spotify.com/v1/search?query=Muse&type=track&market=US&offset=0&limit=10",
            trackPaging.getPrevious());
    assertEquals(
            11113,
            (int) trackPaging.getTotal());
  }
}
