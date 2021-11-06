package data.browse.miscellaneous;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.browse.miscellaneous.GetAvailableGenreSeedsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetAvailableGenreSeedsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetAvailableGenreSeedsRequest getAvailableGenreSeedsRequest = spotifyApi.getAvailableGenreSeeds()
    .build();

  public static void getAvailableGenreSeeds_Sync() {
    try {
      final String[] strings = getAvailableGenreSeedsRequest.execute();

      System.out.println("Length: " + strings.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAvailableGenreSeeds_Async() {
    try {
      final CompletableFuture<String[]> stringsFuture = getAvailableGenreSeedsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final String[] strings = stringsFuture.join();

      System.out.println("Length: " + strings.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getAvailableGenreSeeds_Sync();
    getAvailableGenreSeeds_Async();
  }
}
