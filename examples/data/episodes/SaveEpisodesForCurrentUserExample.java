package data.episodes;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.episodes.SaveEpisodesForCurrentUserRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @deprecated Save one or more episodes to the current user's library.
 */
@Deprecated
public class SaveEpisodesForCurrentUserExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String ids = "046WGwFKUevqKMA4NQfYUv";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final SaveEpisodesForCurrentUserRequest saveEpisodesForCurrentUserRequest = spotifyApi.saveEpisodesForCurrentUser(ids)
    .build();

  public static void saveEpisodesForCurrentUser_Sync() {
    try {
      final String string = saveEpisodesForCurrentUserRequest.execute();

      System.out.println("Null: " + (string == null));
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void saveEpisodesForCurrentUser_Async() {
    try {
      final CompletableFuture<String> stringFuture = saveEpisodesForCurrentUserRequest.executeAsync();

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
    saveEpisodesForCurrentUser_Sync();
    saveEpisodesForCurrentUser_Async();
  }
}
