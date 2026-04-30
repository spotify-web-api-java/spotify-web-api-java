package data.audiobooks;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.SavedAudiobook;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.audiobooks.GetUsersSavedAudiobooksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @deprecated Get a list of the audiobooks saved in the current Spotify user's library.
 */
@Deprecated
public class GetUsersSavedAudiobooksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetUsersSavedAudiobooksRequest getUsersSavedAudiobooksRequest = spotifyApi.getUsersSavedAudiobooks()
//          .limit(10)
//          .offset(0)
    .build();

  public static void getUsersSavedAudiobooks_Sync() {
    try {
      final Paging<SavedAudiobook> audiobookPaging = getUsersSavedAudiobooksRequest.execute();

      System.out.println("Total: " + audiobookPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getUsersSavedAudiobooks_Async() {
    try {
      final CompletableFuture<Paging<SavedAudiobook>> audiobookPagingFuture = getUsersSavedAudiobooksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<SavedAudiobook> audiobookPaging = audiobookPagingFuture.join();

      System.out.println("Total: " + audiobookPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getUsersSavedAudiobooks_Sync();
    getUsersSavedAudiobooks_Async();
  }
}
