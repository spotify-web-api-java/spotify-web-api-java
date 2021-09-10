package data.library;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.library.CheckUsersSavedTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class CheckUsersSavedTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String[] ids = new String[]{"01iyCAUm8EvOFqVWYJ3dVX"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final CheckUsersSavedTracksRequest checkUsersSavedTracksRequest = spotifyApi.checkUsersSavedTracks(ids)
    .build();

  public static void checkUsersSavedTracks_Sync() {
    try {
      final Boolean[] booleans = checkUsersSavedTracksRequest.execute();

      System.out.println("Length: " + booleans.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void checkUsersSavedTracks_Async() {
    try {
      final CompletableFuture<Boolean[]> booleansFuture = checkUsersSavedTracksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Boolean[] booleans = booleansFuture.join();

      System.out.println("Length: " + booleans.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    checkUsersSavedTracks_Sync();
    checkUsersSavedTracks_Async();
  }
}
