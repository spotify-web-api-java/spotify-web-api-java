package data.chapters;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Chapter;
import se.michaelthelin.spotify.requests.data.chapters.GetChapterRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetChapterExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "0D5wENF1pZcV7iSSUkxyQK";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetChapterRequest getChapterRequest = spotifyApi.getChapter(id)
//          .market(CountryCode.US)
    .build();

  public static void getChapter_Sync() {
    try {
      final Chapter chapter = getChapterRequest.execute();

      System.out.println("Name: " + chapter.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getChapter_Async() {
    try {
      final CompletableFuture<Chapter> chapterFuture = getChapterRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Chapter chapter = chapterFuture.join();

      System.out.println("Name: " + chapter.getName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getChapter_Sync();
    getChapter_Async();
  }
}
