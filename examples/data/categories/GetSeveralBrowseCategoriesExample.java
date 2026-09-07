package data.categories;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Category;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.categories.GetSeveralBrowseCategoriesRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @deprecated This endpoint is deprecated per the Spotify API specification.
 */
@Deprecated
public class GetSeveralBrowseCategoriesExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetSeveralBrowseCategoriesRequest getSeveralBrowseCategoriesRequest = spotifyApi.getSeveralBrowseCategories()
//          .locale("sv_SE")
//          .limit(10)
//          .offset(0)
    .build();

  public static void getSeveralBrowseCategories_Sync() {
    try {
      final Paging<Category> categoryPaging = getSeveralBrowseCategoriesRequest.execute();

      System.out.println("Total: " + categoryPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getSeveralBrowseCategories_Async() {
    try {
      final CompletableFuture<Paging<Category>> categoryPagingFuture = getSeveralBrowseCategoriesRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<Category> categoryPaging = categoryPagingFuture.join();

      System.out.println("Total: " + categoryPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getSeveralBrowseCategories_Sync();
    getSeveralBrowseCategories_Async();
  }
}
