package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetArtistsAlbumsRequestTest extends AbstractDataTest<Paging<AlbumSimplified>> {
  private final GetArtistsAlbumsRequest defaultRequest = SPOTIFY_API.getArtistsAlbums(ID_ARTIST)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/artists/GetArtistsAlbumsRequest.json"))
          .album_type(ALBUM_TYPE)
          .limit(LIMIT)
          .market(MARKET)
          .offset(OFFSET)
          .build();

  public GetArtistsAlbumsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/albums?album_type=album&limit=10&market=SE&offset=0",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<AlbumSimplified>) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<AlbumSimplified> albumSimplifiedPaging) {
    assertEquals(
            "https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=0&limit=2&album_type=single&market=ES",
            albumSimplifiedPaging.getHref());
    assertEquals(
            2,
            albumSimplifiedPaging.getItems().length);
    assertEquals(
            2,
            (int) albumSimplifiedPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=2&limit=2&album_type=single&market=ES",
            albumSimplifiedPaging.getNext());
    assertEquals(
            0,
            (int) albumSimplifiedPaging.getOffset());
    assertNull(
            albumSimplifiedPaging.getPrevious());
    assertEquals(
            48,
            (int) albumSimplifiedPaging.getTotal());
  }
}
