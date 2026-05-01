package data.categories;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Category;
import se.michaelthelin.spotify.requests.data.categories.GetSingleBrowseCategoryRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @deprecated This endpoint is deprecated per the Spotify API specification.
 */
@Deprecated
public class GetSingleBrowseCategoryExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String categoryId = "dinner";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetSingleBrowseCategoryRequest getSingleBrowseCategoryRequest = spotifyApi.getSingleBrowseCategory(categoryId)
//          .locale("sv_SE")
    .build();

  public static void getSingleBrowseCategory_Sync() {
    try {
      final Category category = getSingleBrowseCategoryRequest.execute();

      System.out.println("Name: " + category.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getSingleBrowseCategory_Async() {
    try {
      final CompletableFuture<Category> categoryFuture = getSingleBrowseCategoryRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Category category = categoryFuture.join();

      System.out.println("Name: " + category.getName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getSingleBrowseCategory_Sync();
    getSingleBrowseCategory_Async();
  }
}
