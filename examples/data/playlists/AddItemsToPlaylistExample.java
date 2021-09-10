package data.playlists;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AddItemsToPlaylistExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";
  private static final String[] uris = new String[]{"spotify:track:01iyCAUm8EvOFqVWYJ3dVX", "spotify:episode:4GI3dxEafwap1sFiTGPKd1"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi
    .addItemsToPlaylist(playlistId, uris)
//          .position(0)
    .build();

  public static void addItemsToPlaylist_Sync() {
    try {
      final SnapshotResult snapshotResult = addItemsToPlaylistRequest.execute();

      System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void addItemsToPlaylist_Async() {
    try {
      final CompletableFuture<SnapshotResult> snapshotResultFuture = addItemsToPlaylistRequest.executeAsync();

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
    addItemsToPlaylist_Sync();
    addItemsToPlaylist_Async();
  }
}
