/**
 * Copyright (C) 2014 Spotify AB
 */
package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.LibraryAlbum;
import com.wrapper.spotify.models.Page;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class GetMySavedAlbumsRequestTest {

  @Test
  public void shouldGetSavedAlbums_async() throws Exception {
    final Api api = Api.builder().accessToken("someAccessToken").build();

    final GetMySavedAlbumsRequest request = api.getMySavedAlbums()
        .limit(5)
        .offset(1)
        .httpManager(TestUtil.MockedHttpManager.returningJson("saved-albums.json"))
        .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Page<LibraryAlbum>> libraryAlbumsFuture = request.getAsync();

    Futures.addCallback(libraryAlbumsFuture, new FutureCallback<Page<LibraryAlbum>>() {

      @Override
      public void onSuccess(Page<LibraryAlbum> libraryAlbums) {
        assertNotNull(libraryAlbums);

        assertEquals("https://api.spotify.com/v1/me/albums?offset=1&limit=5",
                     libraryAlbums.getHref());

        List<LibraryAlbum> items = libraryAlbums.getItems();
        assertEquals(5, items.size());

        LibraryAlbum firstItem = libraryAlbums.getItems().get(0);
        assertNotNull(firstItem.getAddedAt());
        assertNotNull(firstItem.getAlbum());
        assertEquals("1IeZPlTQ4xjVJPXi0jj4qB", firstItem.getAlbum().getId());

        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future: " + throwable.getMessage());
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetSavedAlbums_sync() throws Exception {
    final Api api = Api.builder().accessToken("someAccessToken").build();

    final GetMySavedAlbumsRequest request = api.getMySavedAlbums()
        .limit(5)
        .offset(1)
        .httpManager(TestUtil.MockedHttpManager.returningJson("saved-albums.json"))
        .build();

    final Page<LibraryAlbum> libraryAlbums = request.get();

    assertNotNull(libraryAlbums);

    assertEquals("https://api.spotify.com/v1/me/albums?offset=1&limit=5", libraryAlbums.getHref());

    List<LibraryAlbum> items = libraryAlbums.getItems();
    assertEquals(5, items.size());

    LibraryAlbum firstItem = libraryAlbums.getItems().get(0);
    assertNotNull(firstItem.getAddedAt());
    assertNotNull(firstItem.getAlbum());
    assertEquals("1IeZPlTQ4xjVJPXi0jj4qB", firstItem.getAlbum().getId());
  }

}
