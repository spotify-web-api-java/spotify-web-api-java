package data.audiobooks;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AudiobookSimplified;
import se.michaelthelin.spotify.requests.data.audiobooks.GetSeveralAudiobooksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @deprecated Get Spotify catalog information for several audiobooks identified by their Spotify IDs.
 */
@Deprecated
public class GetSeveralAudiobooksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String ids = "7iHfbu1YMWVo6aiRxbLrvT,2Takcwg7h193S42OLvM3c7";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetSeveralAudiobooksRequest getSeveralAudiobooksRequest = spotifyApi.getSeveralAudiobooks(ids)
    .build();

  public static void getSeveralAudiobooks_Sync() {
    try {
      final AudiobookSimplified[] audiobooks = getSeveralAudiobooksRequest.execute();

      System.out.println("Number of audiobooks: " + audiobooks.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getSeveralAudiobooks_Async() {
    try {
      final CompletableFuture<AudiobookSimplified[]> audiobooksFuture = getSeveralAudiobooksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final AudiobookSimplified[] audiobooks = audiobooksFuture.join();

      System.out.println("Number of audiobooks: " + audiobooks.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getSeveralAudiobooks_Sync();
    getSeveralAudiobooks_Async();
  }
}
