package data.search;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SearchResult;
import com.wrapper.spotify.requests.data.search.SearchItemRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SearchItemExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String q = "Abba";
  private static final String type = ModelObjectType.ARTIST.getType();

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final SearchItemRequest searchItemRequest = spotifyApi.searchItem(q, type)
          .market(CountryCode.SE)
          .limit(10)
          .offset(0)
          .build();

  public static void searchItem_Sync() {
    try {
      final SearchResult searchResult = searchItemRequest.execute();

      System.out.println("Total tracks: " + searchResult.getTracks().getTotal());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void searchItem_Async() {
    try {
      final Future<SearchResult> searchResultFuture = searchItemRequest.executeAsync();

      // ...

      final SearchResult searchResult = searchResultFuture.get();

      System.out.println("Total tracks: " + searchResult.getTracks().getTotal());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}