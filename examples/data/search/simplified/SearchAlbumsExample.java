package data.search.simplified;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.search.simplified.SearchAlbumsRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SearchAlbumsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String q = "Abba";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final SearchAlbumsRequest searchAlbumsRequest = spotifyApi.searchAlbums(q)
          .market(CountryCode.SE)
          .limit(10)
          .offset(0)
          .build();

  public static void searchAlbums_Sync() {
    try {
      final Paging<AlbumSimplified> albumSimplifiedPaging = searchAlbumsRequest.execute();

      System.out.println("Total: " + albumSimplifiedPaging.getTotal());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void searchAlbums_Async() {
    try {
      final Future<Paging<AlbumSimplified>> pagingFuture = searchAlbumsRequest.executeAsync();

      // ...

      final Paging<AlbumSimplified> albumSimplifiedPaging = pagingFuture.get();

      System.out.println("Total: " + albumSimplifiedPaging.getTotal());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}