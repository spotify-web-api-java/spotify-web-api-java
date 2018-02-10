package data.albums;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.albums.GetAlbumsTracksRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetAlbumsTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "5zT1JLIj9E57p3e1rFm9Uq";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetAlbumsTracksRequest getAlbumsTracksRequest = spotifyApi.getAlbumsTracks(id)
          .limit(10)
          .offset(0)
          .market(CountryCode.SE)
          .build();

  public static void getAlbumsTracks_Sync() {
    try {
      final Paging<TrackSimplified> trackSimplifiedPaging = getAlbumsTracksRequest.execute();

      System.out.println("Total: " + trackSimplifiedPaging.getTotal());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAlbumsTracks_Async() {
    try {
      final Future<Paging<TrackSimplified>> pagingFuture = getAlbumsTracksRequest.executeAsync();

      // ...

      final Paging<TrackSimplified> trackSimplifiedPaging = pagingFuture.get();

      System.out.println("Total: " + trackSimplifiedPaging.getTotal());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}