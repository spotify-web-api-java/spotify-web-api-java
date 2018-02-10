package data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Category;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.browse.GetListOfCategoriesRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetListOfCategoriesExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetListOfCategoriesRequest getListOfCategoriesRequest = spotifyApi.getListOfCategories()
          .country(CountryCode.SE)
          .limit(10)
          .offset(0)
          .locale("sv_SE")
          .build();

  public static void getListOfCategories_Sync() {
    try {
      final Paging<Category> categoryPaging = getListOfCategoriesRequest.execute();

      System.out.println("Total: " + categoryPaging.getTotal());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getListOfCategories_Async() {
    try {
      final Future<Paging<Category>> pagingFuture = getListOfCategoriesRequest.executeAsync();

      // ...

      final Paging<Category> categoryPaging = pagingFuture.get();

      System.out.println("Total: " + categoryPaging.getTotal());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}