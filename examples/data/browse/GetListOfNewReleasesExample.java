package data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.browse.GetListOfNewReleasesRequest;

import java.util.concurrent.Future;

public class GetListOfNewReleasesExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetListOfNewReleasesRequest getListOfNewReleasesRequest = spotifyApi.getListOfNewReleases()
          .country(CountryCode.SE)
          .limit(10)
          .offset(0)
          .build();

  public static void getListOfNewReleases_Sync() {
    try {
      final Paging<AlbumSimplified> albumSimplifiedPaging = getListOfNewReleasesRequest.execute();

      System.out.println("Total: " + albumSimplifiedPaging.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getListOfNewReleases_Async() {
    try {
      final Future<Paging<AlbumSimplified>> pagingFuture = getListOfNewReleasesRequest.executeAsync();

      // ...

      final Paging<AlbumSimplified> albumSimplifiedPaging = pagingFuture.get();

      System.out.println("Total: " + albumSimplifiedPaging.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}