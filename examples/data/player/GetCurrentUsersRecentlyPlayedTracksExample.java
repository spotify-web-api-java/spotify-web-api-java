package data.player;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;
import se.michaelthelin.spotify.model_objects.specification.PlayHistory;
import se.michaelthelin.spotify.requests.data.player.GetCurrentUsersRecentlyPlayedTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetCurrentUsersRecentlyPlayedTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetCurrentUsersRecentlyPlayedTracksRequest getCurrentUsersRecentlyPlayedTracksRequest =
    spotifyApi.getCurrentUsersRecentlyPlayedTracks()
//                  .after(new Date(1517087230000L))
//                  .before(new Date(1453932420000L))
//                  .limit(10)
      .build();

  public static void getCurrentUsersRecentlyPlayedTracks_Sync() {
    try {
      final PagingCursorbased<PlayHistory> playHistoryPagingCursorbased = getCurrentUsersRecentlyPlayedTracksRequest.execute();

      System.out.println("Total: " + playHistoryPagingCursorbased.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getCurrentUsersRecentlyPlayedTracks_Async() {
    try {
      final CompletableFuture<PagingCursorbased<PlayHistory>> pagingCursorbasedFuture = getCurrentUsersRecentlyPlayedTracksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final PagingCursorbased<PlayHistory> playHistoryPagingCursorbased = pagingCursorbasedFuture.join();

      System.out.println("Total: " + playHistoryPagingCursorbased.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getCurrentUsersRecentlyPlayedTracks_Sync();
    getCurrentUsersRecentlyPlayedTracks_Async();
  }
}
