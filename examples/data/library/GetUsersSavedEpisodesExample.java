package data.library;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.SavedEpisode;
import se.michaelthelin.spotify.requests.data.library.GetUsersSavedEpisodesRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetUsersSavedEpisodesExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetUsersSavedEpisodesRequest getUsersSavedEpisodesRequest = spotifyApi.getUsersSavedEpisodes()
//          .limit(10)
//          .offset(0)
//          .market(CountryCode.SE)
    .build();

  public static void getUsersSavedEpisodes_Sync() {
    try {
      final Paging<SavedEpisode> savedEpisodePaging = getUsersSavedEpisodesRequest.execute();

      System.out.println("Total: " + savedEpisodePaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getUsersSavedEpisodes_Async() {
    try {
      final CompletableFuture<Paging<SavedEpisode>> pagingFuture = getUsersSavedEpisodesRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<SavedEpisode> savedEpisodePaging = pagingFuture.join();

      System.out.println("Total: " + savedEpisodePaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getUsersSavedEpisodes_Sync();
    getUsersSavedEpisodes_Async();
  }
}
