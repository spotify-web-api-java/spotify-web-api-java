package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

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

    final ReorderPlaylistsTracksRequest request = api.reorderPlaylistsTracks(myUsername, myPlaylistId, rangeStart, insertBefore)
            .range_length(rangeLength)
            .snapshot_id(snapshotId)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/ReorderPlaylistsTracksRequest.json"))
            .build();

    final Future<SnapshotResult> requestFuture = request.executeAsync();
    final SnapshotResult snapshotResult = requestFuture.get();

    assertEquals(snapshotId, snapshotResult.getSnapshotId());
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

    final ReorderPlaylistsTracksRequest request = api.reorderPlaylistsTracks(myUsername, myPlaylistId, rangeStart, insertBefore)
            .range_length(rangeLength)
            .snapshot_id(snapshotId)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/ReorderPlaylistsTracksRequest.json"))
            .build();

    final SnapshotResult snapshotResult = request.execute();
    assertEquals(snapshotId, snapshotResult.getSnapshotId());
  }
}
