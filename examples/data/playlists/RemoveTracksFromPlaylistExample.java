package data.playlists;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.playlists.RemoveTracksFromPlaylistRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RemoveTracksFromPlaylistExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";
  private static final JsonArray tracks = new JsonParser()
          .parse("[{\"uri\":\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"}]").getAsJsonArray();

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final RemoveTracksFromPlaylistRequest removeTracksFromPlaylistRequest = spotifyApi
          .removeTracksFromPlaylist(playlistId, tracks)
          .snapshotId("JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+")
          .build();

  public static void removeTracksFromPlaylist_Sync() {
    try {
      final SnapshotResult snapshotResult = removeTracksFromPlaylistRequest.execute();

      System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void removeTracksFromPlaylist_Async() {
    try {
      final Future<SnapshotResult> snapshotResultFuture = removeTracksFromPlaylistRequest.executeAsync();

      // ...

      final SnapshotResult snapshotResult = snapshotResultFuture.get();

      System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}