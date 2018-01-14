package com.wrapper.spotify.requests.data.albums;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetAlbumsTracksRequestTest implements ITest<Paging<TrackSimplified>> {

  private final GetAlbumsTracksRequest successRequest = SPOTIFY_API.getAlbumsTracks("id")
          .limit(2)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/albums/GetAlbumsTracksRequest.json"))
          .build();

  public GetAlbumsTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Paging<TrackSimplified>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Paging<TrackSimplified> trackSimplifiedPaging) {
    assertEquals(
            "https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy/tracks?offset=5&limit=10&market=ES",
            trackSimplifiedPaging.getHref());
    assertEquals(
            10,
            trackSimplifiedPaging.getItems().length);
    assertEquals(
            10,
            (int) trackSimplifiedPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy/tracks?offset=15&limit=10&market=ES",
            trackSimplifiedPaging.getNext());
    assertEquals(
            5,
            (int) trackSimplifiedPaging.getOffset());
    assertEquals(
            "https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy/tracks?offset=0&limit=10&market=ES",
            trackSimplifiedPaging.getPrevious());
    assertEquals(
            18,
            (int) trackSimplifiedPaging.getTotal());
  }
}
