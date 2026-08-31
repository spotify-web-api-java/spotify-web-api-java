package data.audiobooks;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.audiobooks.RemoveAudiobooksForCurrentUserRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @deprecated Remove one or more audiobooks from the current Spotify user's library.
 */
@Deprecated
public class RemoveAudiobooksForCurrentUserExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String ids = "7iHfbu1YMWVo6aiRxbLrvT";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final RemoveAudiobooksForCurrentUserRequest removeAudiobooksForCurrentUserRequest = spotifyApi.removeAudiobooksForCurrentUser(ids)
    .build();

  public static void removeAudiobooksForCurrentUser_Sync() {
    try {
      final String string = removeAudiobooksForCurrentUserRequest.execute();

      System.out.println("Null: " + (string == null));
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void removeAudiobooksForCurrentUser_Async() {
    try {
      final CompletableFuture<String> stringFuture = removeAudiobooksForCurrentUserRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final String string = stringFuture.join();

      System.out.println("Null: " + (string == null));
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    removeAudiobooksForCurrentUser_Sync();
    removeAudiobooksForCurrentUser_Async();
  }
}
