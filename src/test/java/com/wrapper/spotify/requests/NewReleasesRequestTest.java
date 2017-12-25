package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.AlbumSimplified;
import com.wrapper.spotify.enums.AlbumType;
import com.wrapper.spotify.model_objects.Paging;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class NewReleasesRequestTest {

  @Test
  public void shouldGetNewReleases_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final NewReleasesRequest request = api.getNewReleases()
            .limit(1)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("new-releases.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Paging<AlbumSimplified>> future = request.getAsync();

    Futures.addCallback(future, new FutureCallback<Paging<AlbumSimplified>>() {
      @Override
      public void onSuccess(Paging<AlbumSimplified> newReleases) {
        assertNotNull(newReleases.getItems());

        assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=0&limit=1",
                newReleases.getHref());

        assertEquals(1, newReleases.getLimit());
        assertEquals(0, newReleases.getOffset());
        assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=1&limit=1",
                newReleases.getNext());
        assertNull(newReleases.getPrevious());
        assertEquals(500, newReleases.getTotal());

        AlbumSimplified firstItem = newReleases.getItems()[0];
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

    final NewReleasesRequest request = api.getNewReleases()
            .limit(1)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("new-releases.json"))
            .build();

    Paging<AlbumSimplified> newReleases = request.get();

    assertNotNull(newReleases.getItems());

    assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=0&limit=1",
            newReleases.getHref());

    assertEquals(1, newReleases.getLimit());
    assertEquals(0, newReleases.getOffset());
    assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=1&limit=1",
            newReleases.getNext());
    assertNull(newReleases.getPrevious());
    assertEquals(500, newReleases.getTotal());

    AlbumSimplified firstItem = newReleases.getItems()[0];
    assertEquals(AlbumType.SINGLE, firstItem.getAlbumType());
    assertEquals(62, firstItem.getAvailableMarkets().length);
    assertNotNull(firstItem.getExternalUrls());
    assertEquals("spotify:album:52kvZcbEDm0v2kWZQXjuuA", firstItem.getUri());

  }
}
