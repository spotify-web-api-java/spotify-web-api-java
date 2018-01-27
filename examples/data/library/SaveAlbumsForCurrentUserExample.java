package data.library;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.data.library.SaveAlbumsForCurrentUserRequest;

import java.util.concurrent.Future;

public class SaveAlbumsForCurrentUserExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String[] ids = new String[]{"5zT1JLIj9E57p3e1rFm9Uq"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final SaveAlbumsForCurrentUserRequest saveAlbumsForCurrentUserRequest = spotifyApi
          .saveAlbumsForCurrentUser(ids)
          .build();

  public static void saveAlbumsForCurrentUser_Sync() {
    try {
      final String string = saveAlbumsForCurrentUserRequest.execute();

      System.out.println("Empty string: " + string);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void saveAlbumsForCurrentUser_Async() {
    try {
      final Future<String> stringFuture = saveAlbumsForCurrentUserRequest.executeAsync();

      // ...

      final String string = stringFuture.get();

      System.out.println("Empty string: " + string);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}