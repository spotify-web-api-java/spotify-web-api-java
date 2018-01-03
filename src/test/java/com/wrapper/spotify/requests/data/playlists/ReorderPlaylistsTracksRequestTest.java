package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ReorderPlaylistsTracksRequestTest {
  @Test
  public void shouldReorderTracksInPlaylist_async() throws Exception {
    final String accessToken = "someAccessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
    final int rangeStart = 10;
    final int rangeLength = 2;
    final int insertBefore = 5;

    final ReorderPlaylistsTracksRequest request = api.reorderTracksInPlaylist(myUsername, myPlaylistId, rangeStart, insertBefore)
            .range_length(rangeLength)
            .snapshot_id(snapshotId)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/ReorderPlaylistsTracksRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<SnapshotResult> addTrackFuture = request.putAsync();

    Futures.addCallback(addTrackFuture, new FutureCallback<SnapshotResult>() {
      @Override
      public void onSuccess(SnapshotResult response) {
        assertEquals(snapshotId, response.getSnapshotId());
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
  public void shouldReorderTracksInPlaylist_sync() throws Exception {
    final String accessToken = "someAccessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
    final int rangeStart = 10;
    final int rangeLength = 2;
    final int insertBefore = 5;

    final ReorderPlaylistsTracksRequest request = api.reorderTracksInPlaylist(myUsername, myPlaylistId, rangeStart, insertBefore)
            .range_length(rangeLength)
            .snapshot_id(snapshotId)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/ReorderPlaylistsTracksRequest.json"))
            .build();

    final SnapshotResult snapshotResult = request.put();
    assertEquals(snapshotId, snapshotResult.getSnapshotId());
  }
}
