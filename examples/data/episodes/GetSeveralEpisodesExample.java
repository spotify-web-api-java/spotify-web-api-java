package data.episodes;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.requests.data.episodes.GetSeveralEpisodesRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetSeveralEpisodesExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String[] ids = new String[]{"4GI3dxEafwap1sFiTGPKd1"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetSeveralEpisodesRequest getSeveralEpisodesRequest = spotifyApi.getSeveralEpisodes(ids)
//          .market(CountryCode.SE)
    .build();

  public static void getSeveralEpisodes_Sync() {
    try {
      final Episode[] episodes = getSeveralEpisodesRequest.execute();

      System.out.println("Length: " + episodes.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getSeveralEpisodes_Async() {
    try {
      final CompletableFuture<Episode[]> episodesFuture = getSeveralEpisodesRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Episode[] episodes = episodesFuture.join();

      System.out.println("Length: " + episodes.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getSeveralEpisodes_Sync();
    getSeveralEpisodes_Async();
  }
}
