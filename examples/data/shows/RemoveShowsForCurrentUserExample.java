package data.shows;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.shows.RemoveShowsForCurrentUserRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @deprecated Remove one or more shows from the current user's library.
 */
@Deprecated
public class RemoveShowsForCurrentUserExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String ids = "5AvwZVawapvyhJUIx71pdJ";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final RemoveShowsForCurrentUserRequest removeShowsForCurrentUserRequest = spotifyApi.removeShowsForCurrentUser(ids)
    .build();

  public static void removeShowsForCurrentUser_Sync() {
    try {
      final String string = removeShowsForCurrentUserRequest.execute();

      System.out.println("Null: " + (string == null));
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void removeShowsForCurrentUser_Async() {
    try {
      final CompletableFuture<String> stringFuture = removeShowsForCurrentUserRequest.executeAsync();

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
    removeShowsForCurrentUser_Sync();
    removeShowsForCurrentUser_Async();
  }
}
