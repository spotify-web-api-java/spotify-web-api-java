package data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.playlists.AddTracksToPlaylistRequest;

import java.io.IOException;
import java.util.concurrent.*;

public class AddTracksToPlaylistExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";
  private static final String[] uris = new String[]{"spotify:track:01iyCAUm8EvOFqVWYJ3dVX"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final AddTracksToPlaylistRequest addTracksToPlaylistRequest = spotifyApi
          .addTracksToPlaylist(playlistId, uris)
//          .position(0)
          .build();

  public static void addTracksToPlaylist_Sync() {
    try {
      final SnapshotResult snapshotResult = addTracksToPlaylistRequest.execute();

      System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void addTracksToPlaylist_Async() {
    try {
      final CompletableFuture<SnapshotResult> snapshotResultFuture = addTracksToPlaylistRequest.executeAsync();

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
}