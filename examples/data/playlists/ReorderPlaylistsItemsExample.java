package data.playlists;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.ReorderPlaylistsItemsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class ReorderPlaylistsItemsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";
  private static final int rangeStart = 0;
  private static final int rangeLength = 1;
  private static final int insertBefore = 0;

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final ReorderPlaylistsItemsRequest reorderPlaylistsItemsRequest = spotifyApi.
    reorderPlaylistsItems(playlistId, rangeStart, insertBefore)
//          .range_length(rangeLength)
//          .snapshot_id("JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+")
    .build();

  public static void reorderPlaylistsItems_Sync() {
    try {
      final SnapshotResult snapshotResult = reorderPlaylistsItemsRequest.execute();

      System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void reorderPlaylistsItems_Async() {
    try {
      final CompletableFuture<SnapshotResult> snapshotResultFuture = reorderPlaylistsItemsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final SnapshotResult snapshotResult = snapshotResultFuture.join();

      System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    reorderPlaylistsItems_Sync();
    reorderPlaylistsItems_Async();
  }
}
