package com.wrapper.spotify.requests.data.albums;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetSeveralAlbumsRequestTest {

  @Test
  public void shouldGetAlbumResultForIds_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final GetSeveralAlbumsRequest request = api.getAlbums("2hYe61Nd2oOoM6RYCwIma1")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetSeveralAlbumsRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Album[]> albumsFuture = request.getAsync();
    Futures.addCallback(albumsFuture, new FutureCallback<Album[]>() {

      @Override
      public void onSuccess(Album... albums) {
        assertEquals(1, albums.length);

        Album firstAlbum = albums[0];
        assertEquals("2hYe61Nd2oOoM6RYCwIma1", firstAlbum.getId());
        assertEquals(AlbumType.ALBUM, firstAlbum.getAlbumType());
        assertEquals("2013-01-01", firstAlbum.getReleaseDate());
        assertEquals(ReleaseDatePrecision.DAY, firstAlbum.getReleaseDatePrecision());
        assertEquals(2, firstAlbum.getCopyrights().length);

        ArtistSimplified[] artists = firstAlbum.getArtists();
        ArtistSimplified firstArtist = artists[0];
        assertEquals("https://api.spotify.com/v1/artists/53A0W3U0s8diEn9RhXQhVz", firstArtist.getHref());
        assertEquals("53A0W3U0s8diEn9RhXQhVz", firstArtist.getId());

        Paging<TrackSimplified> tracksPage = firstAlbum.getTracks();
        assertEquals("https://api.spotify.com/v1/albums/2hYe61Nd2oOoM6RYCwIma1/tracks?offset=0&limit=50", tracksPage.getHref());
        assertEquals(0, tracksPage.getOffset());
        assertEquals(50, tracksPage.getLimit());
        assertEquals(20, tracksPage.getTotal());
        assertEquals("52J94k3JBYbHlFyg7zAABB", tracksPage.getItems()[0].getId());

        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail();
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetAlbumResultForIds_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final GetSeveralAlbumsRequest request = api.getAlbums("2hYe61Nd2oOoM6RYCwIma1")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetSeveralAlbumsRequest.json"))
            .build();

    Album[] albums = request.get();

    assertEquals(1, albums.length);

    Album firstAlbum = albums[0];
    assertEquals("2hYe61Nd2oOoM6RYCwIma1", firstAlbum.getId());
    assertEquals(AlbumType.ALBUM, firstAlbum.getAlbumType());
    assertEquals("2013-01-01", firstAlbum.getReleaseDate());
    assertEquals(ReleaseDatePrecision.DAY, firstAlbum.getReleaseDatePrecision());

    ArtistSimplified[] artists = firstAlbum.getArtists();
    ArtistSimplified firstArtist = artists[0];
    assertEquals("https://api.spotify.com/v1/artists/53A0W3U0s8diEn9RhXQhVz", firstArtist.getHref());
    assertEquals("53A0W3U0s8diEn9RhXQhVz", firstArtist.getId());

    Paging<TrackSimplified> tracksPage = firstAlbum.getTracks();
    assertEquals("https://api.spotify.com/v1/albums/2hYe61Nd2oOoM6RYCwIma1/tracks?offset=0&limit=50", tracksPage.getHref());
    assertEquals(0, tracksPage.getOffset());
    assertEquals(50, tracksPage.getLimit());
    assertEquals(20, tracksPage.getTotal());
    assertEquals("52J94k3JBYbHlFyg7zAABB", tracksPage.getItems()[0].getId());
  }

  @Test
  public void shouldFailForNotFound_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final GetSeveralAlbumsRequest request = api.getAlbums("idontexist")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetSeveralAlbumsRequest_None.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Album[]> albumFuture = request.getAsync();

    Futures.addCallback(albumFuture, new FutureCallback<Album[]>() {
      @Override
      public void onSuccess(Album... albums) {
        assertEquals(1, albums.length);
        assertNull(albums[0]);
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail();
      }

    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

}
