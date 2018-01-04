package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class AddTracksToPlaylistRequestTest {

  @Test
  public void shouldAddTracksToPlaylist_async() throws Exception {
    final String accessToken = "someAccessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String[] tracksToAdd = new String[]{"spotify:track:4BYGxv4rxSNcTgT3DsFB9o", "spotify:track:0BG2iE6McPhmAEKIhfqy1X"};
    final int insertIndex = 3;

    final AddTracksToPlaylistRequest request = api.addTracksToPlaylist(myUsername, myPlaylistId, tracksToAdd)
            .position(insertIndex)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/AddTracksToPlaylistRequest.json"))
            .build();

    final Future<SnapshotResult> requestFuture = request.executeAsync();
    final SnapshotResult snapshotResult = requestFuture.get();

    assertEquals("JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+", snapshotResult.getSnapshotId());
  }
}
