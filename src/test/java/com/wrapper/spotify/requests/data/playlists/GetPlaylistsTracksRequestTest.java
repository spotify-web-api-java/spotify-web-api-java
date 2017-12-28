package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlaylistTrack;
import com.wrapper.spotify.model_objects.Track;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class GetPlaylistsTracksRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    String accessToken = "someToken";
    final Api api = Api.builder().accessToken(accessToken)
            .build();

    final GetPlaylistsTracksRequest request = api
            .getPlaylistTracks("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetPlaylistsTracksRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Paging<PlaylistTrack>> playlistTracksPageFuture = request.getAsync();

    Futures.addCallback(playlistTracksPageFuture, new FutureCallback<Paging<PlaylistTrack>>() {

      @Override
      public void onSuccess(Paging<PlaylistTrack> page) {
        assertNotNull(page);
        assertEquals(
                "https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn/tracks?offset=0&limit=100",
                page.getHref());
        assertEquals(100, page.getLimit());
        assertNull(page.getNext());
        assertEquals(0, page.getOffset());
        assertNull(page.getPrevious());
        assertTrue(page.getTotal() > 0);

        final PlaylistTrack playlistTrack = page.getItems()[0];
        assertNotNull(playlistTrack.getAddedAt());
        assertNotNull(playlistTrack.getAddedBy());

        final Track track = playlistTrack.getTrack();
        assertTrue(track.getPopularity() >= 0);

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
  public void shouldGetTracksResult_sync() throws Exception {
    String accessToken = "someToken";
    final Api api = Api.builder().accessToken("AccessToken").build();

    final GetPlaylistsTracksRequest request = api
            .getPlaylistTracks("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetPlaylistsTracksRequest.json"))
            .build();

    final Paging<PlaylistTrack> page = request.get();

    assertNotNull(page);
    assertEquals(
            "https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn/tracks?offset=0&limit=100",
            page.getHref());
    assertEquals(100, page.getLimit());
    assertNull(page.getNext());
    assertEquals(0, page.getOffset());
    assertNull(page.getPrevious());
    assertTrue(page.getTotal() > 0);

    final PlaylistTrack playlistTrack = page.getItems()[0];
    assertNotNull(playlistTrack.getAddedAt());
    assertNotNull(playlistTrack.getAddedBy());

    final Track track = playlistTrack.getTrack();
    assertTrue(track.getPopularity() >= 0);
  }
}
