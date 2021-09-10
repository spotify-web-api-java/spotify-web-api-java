package data.library;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.SavedAlbum;
import se.michaelthelin.spotify.requests.data.library.GetCurrentUsersSavedAlbumsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetCurrentUsersSavedAlbumsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetCurrentUsersSavedAlbumsRequest getCurrentUsersSavedAlbumsRequest = spotifyApi
    .getCurrentUsersSavedAlbums()
//          .limit(10)
//          .market(CountryCode.SE)
//          .offset(0)
    .build();

  public static void getCurrentUsersSavedAlbums_Sync() {
    try {
      final Paging<SavedAlbum> savedAlbumPaging = getCurrentUsersSavedAlbumsRequest.execute();

      System.out.println("Total: " + savedAlbumPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getCurrentUsersSavedAlbums_Async() {
    try {
      final CompletableFuture<Paging<SavedAlbum>> pagingFuture = getCurrentUsersSavedAlbumsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<SavedAlbum> savedAlbumPaging = pagingFuture.join();

      System.out.println("Total: " + savedAlbumPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getCurrentUsersSavedAlbums_Sync();
    getCurrentUsersSavedAlbums_Async();
  }
}
