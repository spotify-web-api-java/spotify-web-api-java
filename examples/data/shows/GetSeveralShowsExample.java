package data.shows;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ShowSimplified;
import se.michaelthelin.spotify.requests.data.shows.GetSeveralShowsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetSeveralShowsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String[] ids = new String[]{"5AvwZVawapvyhJUIx71pdJ"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetSeveralShowsRequest getSeveralShowsRequest = spotifyApi.getSeveralShows(ids)
//          .market(CountryCode.SE)
    .build();

  public static void getSeveralShows_Sync() {
    try {
      final ShowSimplified[] shows = getSeveralShowsRequest.execute();

      System.out.println("Length: " + shows.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getSeveralShows_Async() {
    try {
      final CompletableFuture<ShowSimplified[]> showsFuture = getSeveralShowsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final ShowSimplified[] shows = showsFuture.join();

      System.out.println("Length: " + shows.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getSeveralShows_Sync();
    getSeveralShows_Async();
  }
}
