package com.wrapper.spotify.requests.data.albums;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetAlbumsTracksRequestTest extends AbstractDataTest<Paging<TrackSimplified>> {

  private final GetAlbumsTracksRequest defaultRequest = SPOTIFY_API.getAlbumsTracks(ID_ALBUM)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/albums/GetAlbumsTracksRequest.json"))
          .offset(OFFSET)
          .limit(LIMIT)
          .market(MARKET)
          .build();

  public GetAlbumsTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/albums/5zT1JLIj9E57p3e1rFm9Uq/tracks?offset=0&limit=10&market=SE",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<TrackSimplified>) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<TrackSimplified> trackSimplifiedPaging) {
    assertEquals(
            "https://api.spotify.com/v1/albums/6akEvsycLGftJxYudPjmqK/tracks?offset=0&limit=2",
            trackSimplifiedPaging.getHref());
    assertEquals(
            2,
            trackSimplifiedPaging.getItems().length);
    assertEquals(
            2,
            (int) trackSimplifiedPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/albums/6akEvsycLGftJxYudPjmqK/tracks?offset=2&limit=2",
            trackSimplifiedPaging.getNext());
    assertEquals(
            0,
            (int) trackSimplifiedPaging.getOffset());
    assertNull(
            trackSimplifiedPaging.getPrevious());
    assertEquals(
            11,
            (int) trackSimplifiedPaging.getTotal());
  }
}
