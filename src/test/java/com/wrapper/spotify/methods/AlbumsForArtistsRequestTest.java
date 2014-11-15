package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.AlbumType;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleAlbum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class AlbumsForArtistsRequestTest {

  @Test
  public void shouldGetAlbumResultForArtistId_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final AlbumsForArtistRequest request = api.getAlbumsForArtist("1vCWHaC5f2uS3yhpwWbIA6")
        .limit(2)
        .types(AlbumType.SINGLE)
        .market("US")
        .httpManager(TestUtil.MockedHttpManager.returningJson("artist-album.json"))
        .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Page<SimpleAlbum>> albumsFuture = request.getAsync();

    Futures.addCallback(albumsFuture, new FutureCallback<Page<SimpleAlbum>>() {
      @Override
      public void onSuccess(Page<SimpleAlbum> albumSearchResult) {
        assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=0&limit=2&album_type=single&market=US", albumSearchResult.getHref());
        assertEquals(2, albumSearchResult.getLimit());
        assertEquals(0, albumSearchResult.getOffset());
        assertEquals(34, albumSearchResult.getTotal());
        assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=2&limit=2&album_type=single&market=US", albumSearchResult.getNext());
        assertNull(albumSearchResult.getPrevious());

        List<SimpleAlbum> albums = albumSearchResult.getItems();
        assertEquals(2, albums.size());

        SimpleAlbum firstAlbum = albums.get(0);
        assertEquals(AlbumType.SINGLE, firstAlbum.getAlbumType());
        assertEquals("https://open.spotify.com/album/6RcscDLgp8v0mSRxvRhfG0", firstAlbum.getExternalUrls().get("spotify"));
        assertEquals("https://api.spotify.com/v1/albums/6RcscDLgp8v0mSRxvRhfG0", firstAlbum.getHref());
        assertEquals("6RcscDLgp8v0mSRxvRhfG0", firstAlbum.getId());
        assertNotNull(firstAlbum.getImages());
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetAlbumResultForArtistId_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final AlbumsForArtistRequest request = api.getAlbumsForArtist("1vCWHaC5f2uS3yhpwWbIA6")
        .limit(2)
        .types(AlbumType.SINGLE)
        .market("US")
        .httpManager(TestUtil.MockedHttpManager.returningJson("artist-album.json"))
        .build();

    final Page<SimpleAlbum> albumSearchResult = request.get();

    assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=0&limit=2&album_type=single&market=US", albumSearchResult.getHref());
    assertEquals(2, albumSearchResult.getLimit());
    assertEquals(0, albumSearchResult.getOffset());
    assertEquals(34, albumSearchResult.getTotal());
    assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=2&limit=2&album_type=single&market=US", albumSearchResult.getNext());
    assertNull(albumSearchResult.getPrevious());

    final List<SimpleAlbum> albums = albumSearchResult.getItems();
    assertEquals(2, albums.size());

    SimpleAlbum firstAlbum = albums.get(0);
    assertEquals(AlbumType.SINGLE, firstAlbum.getAlbumType());
    assertEquals("https://open.spotify.com/album/6RcscDLgp8v0mSRxvRhfG0", firstAlbum.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/albums/6RcscDLgp8v0mSRxvRhfG0", firstAlbum.getHref());
    assertEquals("6RcscDLgp8v0mSRxvRhfG0", firstAlbum.getId());
    assertNotNull(firstAlbum.getImages());
  }

}
