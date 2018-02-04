package data.users_profile;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

import java.util.concurrent.Future;

public class GetCurrentUsersProfileExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile()
          .build();

  public static void getCurrentUsersProfile_Sync() {
    try {
      final User user = getCurrentUsersProfileRequest.execute();

      System.out.println("Display name: " + user.getDisplayName());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getCurrentUsersProfile_Async() {
    try {
      final Future<User> userFuture = getCurrentUsersProfileRequest.executeAsync();

      // ...

      final User user = userFuture.get();

      System.out.println("Display name: " + user.getDisplayName());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}