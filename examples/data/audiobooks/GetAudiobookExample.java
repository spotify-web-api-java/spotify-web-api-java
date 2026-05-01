package data.audiobooks;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Audiobook;
import se.michaelthelin.spotify.requests.data.audiobooks.GetAudiobookRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetAudiobookExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "7iHfbu1YMWVo6aiRxbLrvT";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetAudiobookRequest getAudiobookRequest = spotifyApi.getAudiobook(id)
//          .market(CountryCode.US)
    .build();

  public static void getAudiobook_Sync() {
    try {
      final Audiobook audiobook = getAudiobookRequest.execute();

      System.out.println("Name: " + audiobook.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAudiobook_Async() {
    try {
      final CompletableFuture<Audiobook> audiobookFuture = getAudiobookRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Audiobook audiobook = audiobookFuture.join();

      System.out.println("Name: " + audiobook.getName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getAudiobook_Sync();
    getAudiobook_Async();
  }
}
