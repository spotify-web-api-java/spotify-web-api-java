package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.objects.AlbumSimplified;
import com.wrapper.spotify.objects.AlbumType;
import com.wrapper.spotify.objects.Paging;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class AlbumsForArtistsRequestTest {

  @Test
  public void shouldGetAlbumResultForArtistId_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final AlbumsForArtistRequest request = api.getAlbumsForArtist("1vCWHaC5f2uS3yhpwWbIA6")
            .limit(2)
            .types(AlbumType.SINGLE)
            .market("US")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("artist-album.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Paging<AlbumSimplified>> albumsFuture = request.getAsync();

    Futures.addCallback(albumsFuture, new FutureCallback<Paging<AlbumSimplified>>() {
      @Override
      public void onSuccess(Paging<AlbumSimplified> albumSearchResult) {
        assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=0&limit=2&album_type=single&market=US", albumSearchResult.getHref());
        assertEquals(2, albumSearchResult.getLimit());
        assertEquals(0, albumSearchResult.getOffset());
        assertEquals(46, albumSearchResult.getTotal());
        assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=2&limit=2&album_type=single&market=US", albumSearchResult.getNext());
        assertNull(albumSearchResult.getPrevious());

        AlbumSimplified[] albums = albumSearchResult.getItems();
        assertEquals(2, albums.length);

        AlbumSimplified firstAlbum = albums[0];
        assertEquals(AlbumType.SINGLE, firstAlbum.getAlbumType());
        assertEquals("https://open.spotify.com/album/31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getExternalUrls().get("spotify"));
        assertEquals("https://api.spotify.com/v1/albums/31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getHref());
        assertEquals("31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getId());
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
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("artist-album.json"))
            .build();

    final Paging<AlbumSimplified> albumSearchResult = request.get();

    assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=0&limit=2&album_type=single&market=US", albumSearchResult.getHref());
    assertEquals(2, albumSearchResult.getLimit());
    assertEquals(0, albumSearchResult.getOffset());
    assertEquals(46, albumSearchResult.getTotal());
    assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=2&limit=2&album_type=single&market=US", albumSearchResult.getNext());
    assertNull(albumSearchResult.getPrevious());

    final AlbumSimplified[] albums = albumSearchResult.getItems();
    assertEquals(2, albums.length);

    AlbumSimplified firstAlbum = albums[0];
    assertEquals(AlbumType.SINGLE, firstAlbum.getAlbumType());
    assertEquals("https://open.spotify.com/album/31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/albums/31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getHref());
    assertEquals("31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getId());
    assertNotNull(firstAlbum.getImages());
  }

}
