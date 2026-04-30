package data.chapters;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Chapter;
import se.michaelthelin.spotify.requests.data.chapters.GetSeveralChaptersRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @deprecated Get Spotify catalog information for several chapters identified by their Spotify IDs.
 */
@Deprecated
public class GetSeveralChaptersExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String ids = "0D5wENF1pZcV7iSSUkxyQK,0IsXCFLeyikOH6CLIZpnM7";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetSeveralChaptersRequest getSeveralChaptersRequest = spotifyApi.getSeveralChapters(ids)
    .build();

  public static void getSeveralChapters_Sync() {
    try {
      final Chapter[] chapters = getSeveralChaptersRequest.execute();

      System.out.println("Number of chapters: " + chapters.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getSeveralChapters_Async() {
    try {
      final CompletableFuture<Chapter[]> chaptersFuture = getSeveralChaptersRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Chapter[] chapters = chaptersFuture.join();

      System.out.println("Number of chapters: " + chapters.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getSeveralChapters_Sync();
    getSeveralChapters_Async();
  }
}
