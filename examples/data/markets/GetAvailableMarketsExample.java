package data.markets;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.markets.GetAvailableMarketsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetAvailableMarketsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetAvailableMarketsRequest getAvailableMarketsRequest = spotifyApi.getAvailableMarkets()
    .build();

  public static void getAvailableMarkets_Sync() {
    try {
      final String[] markets = getAvailableMarketsRequest.execute();

      System.out.println("Number of markets: " + markets.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAvailableMarkets_Async() {
    try {
      final CompletableFuture<String[]> marketsFuture = getAvailableMarketsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final String[] markets = marketsFuture.join();

      System.out.println("Number of markets: " + markets.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getAvailableMarkets_Sync();
    getAvailableMarkets_Async();
  }
}
