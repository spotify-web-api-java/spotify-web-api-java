package data.player;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.requests.data.player.GetCurrentlyPlayingTrackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetCurrentlyPlayingTrackExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetCurrentlyPlayingTrackRequest getCurrentlyPlayingTrackRequest = spotifyApi
    .getCurrentlyPlayingTrack()
//          .market(CountryCode.SE)
//          .additionalTypes("track,episode")
    .build();

  public static void getCurrentlyPlayingTrack_Sync() {
    try {
      final CurrentlyPlaying currentlyPlaying = getCurrentlyPlayingTrackRequest.execute();

      System.out.println("Timestamp: " + currentlyPlaying.getTimestamp());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getCurrentlyPlayingTrack_Async() {
    try {
      final CompletableFuture<CurrentlyPlaying> currentlyPlayingFuture = getCurrentlyPlayingTrackRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final CurrentlyPlaying currentlyPlaying = currentlyPlayingFuture.join();

      System.out.println("Timestamp: " + currentlyPlaying.getTimestamp());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getCurrentlyPlayingTrack_Sync();
    getCurrentlyPlayingTrack_Async();
  }
}
