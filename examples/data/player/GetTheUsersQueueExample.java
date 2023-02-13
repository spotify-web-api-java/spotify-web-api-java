package data.player;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.PlaybackQueue;
import se.michaelthelin.spotify.requests.data.player.GetTheUsersQueueRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetTheUsersQueueExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();

  private static final GetTheUsersQueueRequest getTheUsersQueueRequest = spotifyApi.getTheUsersQueue()
    .build();

  public static void getTheUsersQueue_Sync() {
    try {
      final PlaybackQueue playbackQueue = getTheUsersQueueRequest.execute();

      System.out.println("Count of items in the queue: " + playbackQueue.getQueue().size());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getTheUsersQueue_Async() {
    try {
      final CompletableFuture<PlaybackQueue> playbackQueueFuture = getTheUsersQueueRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final PlaybackQueue playbackQueue = playbackQueueFuture.join();

      System.out.println("Count of items in the queue: " + playbackQueue.getQueue().size());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getTheUsersQueue_Sync();
    getTheUsersQueue_Async();
  }
}
