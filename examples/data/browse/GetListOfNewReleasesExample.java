package data.browse;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.browse.GetListOfNewReleasesRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetListOfNewReleasesExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetListOfNewReleasesRequest getListOfNewReleasesRequest = spotifyApi.getListOfNewReleases()
//          .country(CountryCode.SE)
//          .limit(10)
//          .offset(0)
    .build();

  public static void getListOfNewReleases_Sync() {
    try {
      final Paging<AlbumSimplified> albumSimplifiedPaging = getListOfNewReleasesRequest.execute();

      System.out.println("Total: " + albumSimplifiedPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getListOfNewReleases_Async() {
    try {
      final CompletableFuture<Paging<AlbumSimplified>> pagingFuture = getListOfNewReleasesRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<AlbumSimplified> albumSimplifiedPaging = pagingFuture.join();

      System.out.println("Total: " + albumSimplifiedPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getListOfNewReleases_Sync();
    getListOfNewReleases_Async();
  }
}
