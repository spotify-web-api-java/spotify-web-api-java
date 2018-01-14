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
            "https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/albums?offset=5&limit=10&album_type=single,album&market=ES",
            albumSimplifiedPaging.getHref());
    assertEquals(
            10,
            albumSimplifiedPaging.getItems().length);
    assertEquals(
            10,
            (int) albumSimplifiedPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/albums?offset=15&limit=10&album_type=single,album&market=ES",
            albumSimplifiedPaging.getNext());
    assertEquals(
            5,
            (int) albumSimplifiedPaging.getOffset());
    assertEquals(
            "https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/albums?offset=0&limit=10&album_type=single,album&market=ES",
            albumSimplifiedPaging.getPrevious());
    assertEquals(
            99,
            (int) albumSimplifiedPaging.getTotal());
  }
}
