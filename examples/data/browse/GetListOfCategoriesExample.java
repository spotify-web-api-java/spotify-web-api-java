package data.browse;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Category;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.browse.GetListOfCategoriesRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetListOfCategoriesExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetListOfCategoriesRequest getListOfCategoriesRequest = spotifyApi.getListOfCategories()
//          .country(CountryCode.SE)
//          .limit(10)
//          .offset(0)
//          .locale("sv_SE")
    .build();

  public static void getListOfCategories_Sync() {
    try {
      final Paging<Category> categoryPaging = getListOfCategoriesRequest.execute();

      System.out.println("Total: " + categoryPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getListOfCategories_Async() {
    try {
      final CompletableFuture<Paging<Category>> pagingFuture = getListOfCategoriesRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<Category> categoryPaging = pagingFuture.join();

      System.out.println("Total: " + categoryPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getListOfCategories_Sync();
    getListOfCategories_Async();
  }
}
