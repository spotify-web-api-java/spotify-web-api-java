package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.AlbumSimplified;
import com.wrapper.spotify.model_objects.AlbumType;
import com.wrapper.spotify.model_objects.NewReleases;
import com.wrapper.spotify.model_objects.Paging;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class GetListOfNewReleasesRequestTest {

  @Test
  public void shouldGetNewReleases_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final GetListOfNewReleasesRequest request = api.getNewReleases()
            .limit(1)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("new-releases.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<NewReleases> future = request.getAsync();

    Futures.addCallback(future, new FutureCallback<NewReleases>() {
      @Override
      public void onSuccess(NewReleases newReleases) {
        assertNotNull(newReleases.getAlbums());

        Paging<AlbumSimplified> albums = newReleases.getAlbums();

        assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=0&limit=1",
                albums.getHref());

        assertEquals(1, albums.getLimit());
        assertEquals(0, albums.getOffset());
        assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=1&limit=1",
                albums.getNext());
        assertNull(albums.getPrevious());
        assertEquals(500, albums.getTotal());

        AlbumSimplified firstItem = albums.getItems()[0];
        assertEquals(AlbumType.SINGLE, firstItem.getAlbumType());
        assertEquals(62, firstItem.getAvailableMarkets().length);
        assertNotNull(firstItem.getExternalUrls());
        assertEquals("spotify:album:52kvZcbEDm0v2kWZQXjuuA", firstItem.getUri());

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
  public void shouldGetArtistsResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final GetListOfNewReleasesRequest request = api.getNewReleases()
            .limit(1)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("new-releases.json"))
            .build();

    NewReleases newReleases = request.get();

    assertNotNull(newReleases.getAlbums());

    Paging<AlbumSimplified> albums = newReleases.getAlbums();

    assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=0&limit=1",
            albums.getHref());

    assertEquals(1, albums.getLimit());
    assertEquals(0, albums.getOffset());
    assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=1&limit=1",
            albums.getNext());
    assertNull(albums.getPrevious());
    assertEquals(500, albums.getTotal());

    AlbumSimplified firstItem = albums.getItems()[0];
    assertEquals(AlbumType.SINGLE, firstItem.getAlbumType());
    assertEquals(62, firstItem.getAvailableMarkets().length);
    assertNotNull(firstItem.getExternalUrls());
    assertEquals("spotify:album:52kvZcbEDm0v2kWZQXjuuA", firstItem.getUri());

  }
}
