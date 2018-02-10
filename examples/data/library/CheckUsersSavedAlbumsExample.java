package data.library;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.library.CheckUsersSavedAlbumsRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CheckUsersSavedAlbumsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String[] ids = new String[]{"5zT1JLIj9E57p3e1rFm9Uq"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final CheckUsersSavedAlbumsRequest checkUsersSavedAlbumsRequest = spotifyApi.checkUsersSavedAlbums(ids)
          .build();

  public static void checkUsersSavedAlbums_Sync() {
    try {
      final Boolean[] booleans = checkUsersSavedAlbumsRequest.execute();

      System.out.println("Length: " + booleans.length);
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void checkUsersSavedAlbums_Async() {
    try {
      final Future<Boolean[]> booleansFuture = checkUsersSavedAlbumsRequest.executeAsync();

      // ...

      final Boolean[] booleans = booleansFuture.get();

      System.out.println("Length: " + booleans.length);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}