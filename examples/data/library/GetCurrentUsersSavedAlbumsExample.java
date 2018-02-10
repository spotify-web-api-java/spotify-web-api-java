package data.library;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedAlbum;
import com.wrapper.spotify.requests.data.library.GetCurrentUsersSavedAlbumsRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetCurrentUsersSavedAlbumsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetCurrentUsersSavedAlbumsRequest getCurrentUsersSavedAlbumsRequest = spotifyApi
          .getCurrentUsersSavedAlbums()
          .limit(10)
          .market(CountryCode.SE)
          .offset(0)
          .build();

  public static void getCurrentUsersSavedAlbums_Sync() {
    try {
      final Paging<SavedAlbum> savedAlbumPaging = getCurrentUsersSavedAlbumsRequest.execute();

      System.out.println("Total: " + savedAlbumPaging.getTotal());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getCurrentUsersSavedAlbums_Async() {
    try {
      final Future<Paging<SavedAlbum>> pagingFuture = getCurrentUsersSavedAlbumsRequest.executeAsync();

      // ...

      final Paging<SavedAlbum> savedAlbumPaging = pagingFuture.get();

      System.out.println("Total: " + savedAlbumPaging.getTotal());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}