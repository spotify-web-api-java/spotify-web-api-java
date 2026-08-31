package data.playlists;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.GetUsersPlaylistsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @deprecated Get a list of the playlists owned or followed by a Spotify user.
 */
@Deprecated
public class GetUsersPlaylistsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String userId = "abbaspotify";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetUsersPlaylistsRequest getUsersPlaylistsRequest = spotifyApi.getUsersPlaylists(userId)
//          .limit(10)
//          .offset(0)
    .build();

  public static void getUsersPlaylists_Sync() {
    try {
      final Paging<PlaylistSimplified> playlistSimplifiedPaging = getUsersPlaylistsRequest.execute();

      System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getUsersPlaylists_Async() {
    try {
      final CompletableFuture<Paging<PlaylistSimplified>> playlistSimplifiedPagingFuture = getUsersPlaylistsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<PlaylistSimplified> playlistSimplifiedPaging = playlistSimplifiedPagingFuture.join();

      System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getUsersPlaylists_Sync();
    getUsersPlaylists_Async();
  }
}
