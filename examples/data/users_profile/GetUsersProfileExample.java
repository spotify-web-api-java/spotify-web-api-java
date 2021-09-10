package data.users_profile;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.users_profile.GetUsersProfileRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetUsersProfileExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String userId = "user_id";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetUsersProfileRequest getUsersProfileRequest = spotifyApi.getUsersProfile(userId)
    .build();

  public static void getUsersProfile_Sync() {
    try {
      final User user = getUsersProfileRequest.execute();

      System.out.println("Display name: " + user.getDisplayName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getUsersProfile_Async() {
    try {
      final CompletableFuture<User> userFuture = getUsersProfileRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final User user = userFuture.join();

      System.out.println("Display name: " + user.getDisplayName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getUsersProfile_Sync();
    getUsersProfile_Async();
  }
}
