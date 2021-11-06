package data.episodes;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.requests.data.episodes.GetEpisodeRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetEpisodeExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "4GI3dxEafwap1sFiTGPKd1";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetEpisodeRequest getEpisodeRequest = spotifyApi.getEpisode(id)
//          .market(CountryCode.SE)
    .build();

  public static void getEpisode_Sync() {
    try {
      final Episode episode = getEpisodeRequest.execute();

      System.out.println("Name: " + episode.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getEpisode_Async() {
    try {
      final CompletableFuture<Episode> episodeFuture = getEpisodeRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Episode episode = episodeFuture.join();

      System.out.println("Name: " + episode.getName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getEpisode_Sync();
    getEpisode_Async();
  }
}
