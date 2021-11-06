package data.follow;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;
import se.michaelthelin.spotify.requests.data.follow.GetUsersFollowedArtistsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetUsersFollowedArtistsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final ModelObjectType type = ModelObjectType.ARTIST;

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetUsersFollowedArtistsRequest getUsersFollowedArtistsRequest = spotifyApi
    .getUsersFollowedArtists(type)
//          .after("0LcJLqbBmaGUft1e9Mm8HV")
//          .limit(10)
    .build();

  public static void getUsersFollowedArtists_Sync() {
    try {
      final PagingCursorbased<Artist> artistPagingCursorbased = getUsersFollowedArtistsRequest.execute();

      System.out.println("Total: " + artistPagingCursorbased.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getUsersFollowedArtists_Async() {
    try {
      final CompletableFuture<PagingCursorbased<Artist>> pagingCursorbasedFuture = getUsersFollowedArtistsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final PagingCursorbased<Artist> artistPagingCursorbased = pagingCursorbasedFuture.join();

      System.out.println("Total: " + artistPagingCursorbased.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getUsersFollowedArtists_Sync();
    getUsersFollowedArtists_Async();
  }
}
