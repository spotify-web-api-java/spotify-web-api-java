package com.wrapper.spotify.requests.data.playlists;


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
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class RemoveTracksFromPlaylistRequestTest {
  @Test
  public void shouldAddTracksToPlaylist_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
    PlaylistTrackPosition playlistTrackPosition1 = new PlaylistTrackPosition.Builder()
            .setUri("spotify:track:4BYGxv4rxSNcTgT3DsFB9o")
            .build();
    PlaylistTrackPosition playlistTrackPosition2 = new PlaylistTrackPosition.Builder()
            .setUri("spotify:track:0BG2iE6McPhmAEKIhfqy1X").setPositions(new int[]{5, 3})
            .build();
    final PlaylistTrackPosition[] playlistTrackPositions = {playlistTrackPosition1, playlistTrackPosition2};
    final JsonArray tracksToRemove = new JsonParser().parse(new Gson().toJson(new ArrayList<>(Arrays.asList(playlistTrackPositions)))).getAsJsonArray();

    final RemoveTracksFromPlaylistRequest request = api.removeTracksFromPlaylist(myUsername, myPlaylistId, tracksToRemove)
            .snapshotId(snapshotId)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/RemoveTracksFromPlaylistRequest.json"))
            .build();

    final Future<SnapshotResult> requestFuture = request.executeAsync();
    final SnapshotResult snapshotResult = requestFuture.get();

    assertEquals(snapshotId, snapshotResult.getSnapshotId());
  }

  @Test
  public void shouldAddTracksToPlaylist_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
    PlaylistTrackPosition playlistTrackPosition1 = new PlaylistTrackPosition.Builder()
            .setUri("spotify:track:4BYGxv4rxSNcTgT3DsFB9o")
            .build();
    PlaylistTrackPosition playlistTrackPosition2 = new PlaylistTrackPosition.Builder()
            .setUri("spotify:track:0BG2iE6McPhmAEKIhfqy1X").setPositions(new int[]{5, 3})
            .build();
    final PlaylistTrackPosition[] playlistTrackPositions = {playlistTrackPosition1, playlistTrackPosition2};
    final JsonArray tracksToRemove = new JsonParser().parse(new Gson().toJson(new ArrayList<>(Arrays.asList(playlistTrackPositions)))).getAsJsonArray();

    final RemoveTracksFromPlaylistRequest request = api.removeTracksFromPlaylist(myUsername, myPlaylistId, tracksToRemove)
            .snapshotId(snapshotId)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/RemoveTracksFromPlaylistRequest.json"))
            .build();

    final SnapshotResult snapshotResult = request.execute();
    assertEquals(snapshotId, snapshotResult.getSnapshotId());
  }
}
