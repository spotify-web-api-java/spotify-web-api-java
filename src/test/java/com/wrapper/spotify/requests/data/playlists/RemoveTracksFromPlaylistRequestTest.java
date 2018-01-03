package com.wrapper.spotify.requests.data.playlists;


import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.special.PlaylistTrackPosition;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RemoveTracksFromPlaylistRequestTest {
  @Test
  public void shouldAddTracksToPlaylist_async() throws Exception {
    final String accessToken = "someAccessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
    PlaylistTrackPosition playlistTrackPosition1 = new PlaylistTrackPosition("spotify:track:4BYGxv4rxSNcTgT3DsFB9o");
    PlaylistTrackPosition playlistTrackPosition2 = new PlaylistTrackPosition("spotify:track:0BG2iE6McPhmAEKIhfqy1X", new int[]{5});
    final PlaylistTrackPosition[] playlistTrackPositions = {playlistTrackPosition1, playlistTrackPosition2};
    final JsonArray tracksToRemove = new JsonParser().parse(new Gson().toJson(new ArrayList<>(Arrays.asList(playlistTrackPositions)))).getAsJsonArray();

    final RemoveTracksFromPlaylistRequest request = api.removeTracksFromPlaylist(myUsername, myPlaylistId, tracksToRemove)
            .snapshotId(snapshotId)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/RemoveTracksFromPlaylistRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<SnapshotResult> addTrackFuture = request.deleteAsync();

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
  public void shouldAddTracksToPlaylist_sync() throws Exception {
    final String accessToken = "someAccessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
    PlaylistTrackPosition playlistTrackPosition1 = new PlaylistTrackPosition("spotify:track:4BYGxv4rxSNcTgT3DsFB9o");
    PlaylistTrackPosition playlistTrackPosition2 = new PlaylistTrackPosition("spotify:track:0BG2iE6McPhmAEKIhfqy1X", new int[]{5});
    final PlaylistTrackPosition[] playlistTrackPositions = {playlistTrackPosition1, playlistTrackPosition2};
    final JsonArray tracksToRemove = new JsonParser().parse(new Gson().toJson(new ArrayList<>(Arrays.asList(playlistTrackPositions)))).getAsJsonArray();

    final RemoveTracksFromPlaylistRequest request = api.removeTracksFromPlaylist(myUsername, myPlaylistId, tracksToRemove)
            .snapshotId(snapshotId)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/RemoveTracksFromPlaylistRequest.json"))
            .build();

    final SnapshotResult snapshotResult = request.delete();
    assertEquals(snapshotId, snapshotResult.getSnapshotId());
  }
}
