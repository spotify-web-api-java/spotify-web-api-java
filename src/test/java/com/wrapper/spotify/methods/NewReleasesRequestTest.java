/**
 * Copyright (C) 2014 Spotify AB
 */
package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.AlbumType;
import com.wrapper.spotify.models.NewReleases;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleAlbum;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;

public class NewReleasesRequestTest {

  @Test
  public void shouldGetNewReleases_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final NewReleasesRequest request = api.getNewReleases()
        .limit(3)
        .offset(1)
        .country("SE")
        .httpManager(TestUtil.MockedHttpManager.returningJson("new-releases.json"))
        .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<NewReleases> future = request.getAsync();

    Futures.addCallback(future, new FutureCallback<NewReleases>() {
      @Override
      public void onSuccess(NewReleases newReleases) {
        assertNotNull(newReleases.getAlbums());

        Page<SimpleAlbum> albums = newReleases.getAlbums();

        assertEquals("https://api.spotify.com/v1/browse/new-releases?country=SE&offset=1&limit=3",
                     albums.getHref());

        assertEquals(3, albums.getLimit());
        assertEquals(1, albums.getOffset());
        assertEquals("https://api.spotify.com/v1/browse/new-releases?country=SE&offset=4&limit=3",
                     albums.getNext());
        assertEquals("https://api.spotify.com/v1/browse/new-releases?country=SE&offset=0&limit=3",
                     albums.getPrevious());
        assertEquals(500, albums.getTotal());

        SimpleAlbum firstItem = albums.getItems().get(0);
        assertEquals(AlbumType.SINGLE, firstItem.getAlbumType());
        assertEquals(1, firstItem.getAvailableMarkets().size());
        assertEquals("SE", firstItem.getAvailableMarkets().get(0));
        assertNotNull(firstItem.getExternalUrls());
        assertEquals("spotify:album:5McUiSC2VSw2ToVHR8tnzZ", firstItem.getUri());

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
        .limit(3)
        .offset(1)
        .country("SE")
        .httpManager(TestUtil.MockedHttpManager.returningJson("new-releases.json"))
        .build();

    NewReleases newReleases = request.get();

    assertNotNull(newReleases.getAlbums());

    Page<SimpleAlbum> albums = newReleases.getAlbums();

    assertEquals("https://api.spotify.com/v1/browse/new-releases?country=SE&offset=1&limit=3",
                 albums.getHref());

    assertEquals(3, albums.getLimit());
    assertEquals(1, albums.getOffset());
    assertEquals("https://api.spotify.com/v1/browse/new-releases?country=SE&offset=4&limit=3",
                 albums.getNext());
    assertEquals("https://api.spotify.com/v1/browse/new-releases?country=SE&offset=0&limit=3",
                 albums.getPrevious());
    assertEquals(500, albums.getTotal());

    SimpleAlbum firstItem = albums.getItems().get(0);
    assertEquals(AlbumType.SINGLE, firstItem.getAlbumType());
    assertEquals(1, firstItem.getAvailableMarkets().size());
    assertEquals("SE", firstItem.getAvailableMarkets().get(0));
    assertNotNull(firstItem.getExternalUrls());
    assertEquals("spotify:album:5McUiSC2VSw2ToVHR8tnzZ", firstItem.getUri());

  }
}
