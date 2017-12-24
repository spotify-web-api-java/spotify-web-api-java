package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.LibraryTrack;
import com.wrapper.spotify.model_objects.Paging;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class GetMySavedTracksRequestTest {

  @Test
  public void shouldGetSavedTracks_async() throws Exception {
    final Api api = Api.builder().accessToken("someAccessToken").build();

    final GetMySavedTracksRequest request = api.getMySavedTracks()
            .limit(5)
            .offset(1)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("saved-tracks.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Paging<LibraryTrack>> libraryTracksFuture = request.getAsync();

    Futures.addCallback(libraryTracksFuture, new FutureCallback<Paging<LibraryTrack>>() {

      @Override
      public void onSuccess(Paging<LibraryTrack> libraryTracks) {
        assertNotNull(libraryTracks);

        assertEquals("https://api.spotify.com/v1/me/tracks?offset=1&limit=1", libraryTracks.getHref());

        LibraryTrack[] items = libraryTracks.getItems();
        assertEquals(1, items.length);

        LibraryTrack firstItem = libraryTracks.getItems()[0];
        assertNotNull(firstItem.getAddedAt());
        assertNotNull(firstItem.getTrack());
        assertEquals("1bhUWB0zJMIKr9yVPrkEuI", firstItem.getTrack().getId());

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
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("saved-tracks.json"))
            .build();

    final Paging<LibraryTrack> libraryTracks = request.get();

    assertNotNull(libraryTracks);

    assertEquals("https://api.spotify.com/v1/me/tracks?offset=1&limit=1", libraryTracks.getHref());

    LibraryTrack[] items = libraryTracks.getItems();
    assertEquals(1, items.length);

    LibraryTrack firstItem = libraryTracks.getItems()[0];
    assertNotNull(firstItem.getAddedAt());
    assertNotNull(firstItem.getTrack());
    assertEquals("1bhUWB0zJMIKr9yVPrkEuI", firstItem.getTrack().getId());
  }

}
