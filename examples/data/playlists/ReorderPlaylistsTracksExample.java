package data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.playlists.ReorderPlaylistsTracksRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ReorderPlaylistsTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";
  private static final int rangeStart = 0;
  private static final int rangeLength = 1;
  private static final int insertBefore = 0;

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final ReorderPlaylistsTracksRequest reorderPlaylistsTracksRequest = spotifyApi.
          reorderPlaylistsTracks(playlistId, rangeStart, insertBefore)
          .range_length(rangeLength)
          .snapshot_id("JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+")
          .build();

  public static void reorderPlaylistsTracks_Sync() {
    try {
      final SnapshotResult snapshotResult = reorderPlaylistsTracksRequest.execute();

      System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void reorderPlaylistsTracks_Async() {
    try {
      final Future<SnapshotResult> snapshotResultFuture = reorderPlaylistsTracksRequest.executeAsync();

      // ...

      final SnapshotResult snapshotResult = snapshotResultFuture.get();

      System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}