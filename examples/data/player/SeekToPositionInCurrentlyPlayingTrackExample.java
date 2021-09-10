package data.player;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.player.SeekToPositionInCurrentlyPlayingTrackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class SeekToPositionInCurrentlyPlayingTrackExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final int positionMs = 10000;

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final SeekToPositionInCurrentlyPlayingTrackRequest seekToPositionInCurrentlyPlayingTrackRequest =
    spotifyApi.seekToPositionInCurrentlyPlayingTrack(positionMs)
//                  .device_id("5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e")
      .build();

  public static void seekToPositionInCurrentlyPlayingTrack_Sync() {
    try {
      final String string = seekToPositionInCurrentlyPlayingTrackRequest.execute();

      System.out.println("Null: " + string);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void seekToPositionInCurrentlyPlayingTrack_Async() {
    try {
      final CompletableFuture<String> stringFuture = seekToPositionInCurrentlyPlayingTrackRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final String string = stringFuture.join();

      System.out.println("Null: " + string);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    seekToPositionInCurrentlyPlayingTrack_Sync();
    seekToPositionInCurrentlyPlayingTrack_Async();
  }
}
