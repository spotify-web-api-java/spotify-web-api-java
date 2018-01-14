package com.wrapper.spotify.requests.data.artists;

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
public class GetArtistsAlbumsRequestTest implements ITest<Paging<AlbumSimplified>> {
  private final GetArtistsAlbumsRequest successRequest = SPOTIFY_API.getArtistsAlbums("id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/artists/GetArtistsAlbumsRequest.json"))
          .build();

  public GetArtistsAlbumsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Paging<AlbumSimplified>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Paging<AlbumSimplified> albumSimplifiedPaging) {
    assertEquals(
            "https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=0&limit=2&album_type=single&market=US",
            albumSimplifiedPaging.getHref());
    assertEquals(
            2,
            albumSimplifiedPaging.getItems().length);
    assertEquals(
            2,
            (int) albumSimplifiedPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=2&limit=2&album_type=single&market=US",
            albumSimplifiedPaging.getNext());
    assertEquals(
            0,
            (int) albumSimplifiedPaging.getOffset());
    assertEquals(
            null,
            albumSimplifiedPaging.getPrevious());
    assertEquals(
            46,
            (int) albumSimplifiedPaging.getTotal());
  }
}
