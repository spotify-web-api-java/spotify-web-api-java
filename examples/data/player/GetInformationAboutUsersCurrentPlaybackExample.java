package data.player;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import se.michaelthelin.spotify.requests.data.player.GetInformationAboutUsersCurrentPlaybackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetInformationAboutUsersCurrentPlaybackExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
    spotifyApi.getInformationAboutUsersCurrentPlayback()
//                  .market(CountryCode.SE)
//                  .additionalTypes("track,episode")
      .build();

  public static void getInformationAboutUsersCurrentPlayback_Sync() {
    try {
      final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

      System.out.println("Timestamp: " + currentlyPlayingContext.getTimestamp());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getInformationAboutUsersCurrentPlayback_Async() {
    try {
      final CompletableFuture<CurrentlyPlayingContext> currentlyPlayingContextFuture = getInformationAboutUsersCurrentPlaybackRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final CurrentlyPlayingContext currentlyPlayingContext = currentlyPlayingContextFuture.join();

      System.out.println("Timestamp: " + currentlyPlayingContext.getTimestamp());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getInformationAboutUsersCurrentPlayback_Sync();
    getInformationAboutUsersCurrentPlayback_Async();
  }
}
