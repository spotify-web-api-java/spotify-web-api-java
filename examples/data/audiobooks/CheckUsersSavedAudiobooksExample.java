package data.audiobooks;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.audiobooks.CheckUsersSavedAudiobooksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @deprecated Check if one or more audiobooks are already saved in the current Spotify user's library.
 */
@Deprecated
public class CheckUsersSavedAudiobooksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String ids = "7iHfbu1YMWVo6aiRxbLrvT,2Takcwg7h193S42OLvM3c7";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final CheckUsersSavedAudiobooksRequest checkUsersSavedAudiobooksRequest = spotifyApi.checkUsersSavedAudiobooks(ids)
    .build();

  public static void checkUsersSavedAudiobooks_Sync() {
    try {
      final Boolean[] booleans = checkUsersSavedAudiobooksRequest.execute();

      System.out.println("Number of results: " + booleans.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void checkUsersSavedAudiobooks_Async() {
    try {
      final CompletableFuture<Boolean[]> booleansFuture = checkUsersSavedAudiobooksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Boolean[] booleans = booleansFuture.join();

      System.out.println("Number of results: " + booleans.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    checkUsersSavedAudiobooks_Sync();
    checkUsersSavedAudiobooks_Async();
  }
}
