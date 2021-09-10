package data.albums;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumsTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetAlbumsTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "5zT1JLIj9E57p3e1rFm9Uq";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetAlbumsTracksRequest getAlbumsTracksRequest = spotifyApi.getAlbumsTracks(id)
//          .limit(10)
//          .offset(0)
//          .market(CountryCode.SE)
    .build();

  public static void getAlbumsTracks_Sync() {
    try {
      final Paging<TrackSimplified> trackSimplifiedPaging = getAlbumsTracksRequest.execute();

      System.out.println("Total: " + trackSimplifiedPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAlbumsTracks_Async() {
    try {
      final CompletableFuture<Paging<TrackSimplified>> pagingFuture = getAlbumsTracksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<TrackSimplified> trackSimplifiedPaging = pagingFuture.join();

      System.out.println("Total: " + trackSimplifiedPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getAlbumsTracks_Sync();
    getAlbumsTracks_Async();
  }
}
