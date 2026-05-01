package data.audiobooks;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ChapterSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.audiobooks.GetAudiobookChaptersRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetAudiobookChaptersExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "7iHfbu1YMWVo6aiRxbLrvT";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetAudiobookChaptersRequest getAudiobookChaptersRequest = spotifyApi.getAudiobookChapters(id)
//          .market(CountryCode.US)
//          .limit(10)
//          .offset(0)
    .build();

  public static void getAudiobookChapters_Sync() {
    try {
      final Paging<ChapterSimplified> chapterPaging = getAudiobookChaptersRequest.execute();

      System.out.println("Total: " + chapterPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAudiobookChapters_Async() {
    try {
      final CompletableFuture<Paging<ChapterSimplified>> chapterPagingFuture = getAudiobookChaptersRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<ChapterSimplified> chapterPaging = chapterPagingFuture.join();

      System.out.println("Total: " + chapterPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getAudiobookChapters_Sync();
    getAudiobookChapters_Async();
  }
}
