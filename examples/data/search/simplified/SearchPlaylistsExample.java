package data.search.simplified;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;

import java.io.IOException;
import java.util.concurrent.*;

public class SearchPlaylistsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String q = "Abba";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final SearchPlaylistsRequest searchPlaylistsRequest = spotifyApi.searchPlaylists(q)
//          .market(CountryCode.SE)
//          .limit(10)
//          .offset(0)
          .build();

  public static void searchPlaylists_Sync() {
    try {
      final Paging<PlaylistSimplified> playlistSimplifiedPaging = searchPlaylistsRequest.execute();

      System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void searchPlaylists_Async() {
    try {
      final CompletableFuture<Paging<PlaylistSimplified>> pagingFuture = searchPlaylistsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<PlaylistSimplified> playlistSimplifiedPaging = pagingFuture.join();

      System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }
}