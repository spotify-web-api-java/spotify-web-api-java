/**
 * Copyright (C) 2014 Spotify AB
 */
package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.LibraryTrack;
import com.wrapper.spotify.models.Page;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class GetMySavedTracksRequestTest {

  @Test
  public void shouldGetSavedTracks_async() throws Exception {
    final Api api = Api.builder().accessToken("someAccessToken").build();

    final GetMySavedTracksRequest request = api.getMySavedTracks()
        .limit(5)
        .offset(1)
        .httpManager(TestUtil.MockedHttpManager.returningJson("saved-tracks.json"))
        .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Page<LibraryTrack>> libraryTracksFuture = request.getAsync();

    Futures.addCallback(libraryTracksFuture, new FutureCallback<Page<LibraryTrack>>() {

      @Override
      public void onSuccess(Page<LibraryTrack> libraryTracks) {
        assertNotNull(libraryTracks);

        assertEquals("https://api.spotify.com/v1/me/tracks?offset=1&limit=5",
                     libraryTracks.getHref());

        List<LibraryTrack> items = libraryTracks.getItems();
        assertEquals(5, items.size());

        LibraryTrack firstItem = libraryTracks.getItems().get(0);
        assertNotNull(firstItem.getAddedAt());
        assertNotNull(firstItem.getTrack());
        assertEquals("13zm8XhfM4RBtQpjdqY44e", firstItem.getTrack().getId());

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
  public void shouldGetSavedTracks_sync() throws Exception {
    final Api api = Api.builder().accessToken("someAccessToken").build();

    final GetMySavedTracksRequest request = api.getMySavedTracks()
        .limit(5)
        .offset(1)
        .httpManager(TestUtil.MockedHttpManager.returningJson("saved-tracks.json"))
        .build();

    final Page<LibraryTrack> libraryTracks = request.get();

    assertNotNull(libraryTracks);

    assertEquals("https://api.spotify.com/v1/me/tracks?offset=1&limit=5", libraryTracks.getHref());

    List<LibraryTrack> items = libraryTracks.getItems();
    assertEquals(5, items.size());

    LibraryTrack firstItem = libraryTracks.getItems().get(0);
    assertNotNull(firstItem.getAddedAt());
    assertNotNull(firstItem.getTrack());
    assertEquals("13zm8XhfM4RBtQpjdqY44e", firstItem.getTrack().getId());
  }

}
