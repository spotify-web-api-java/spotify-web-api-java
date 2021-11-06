package data.follow;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.follow.CheckCurrentUserFollowsArtistsOrUsersRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class CheckCurrentUserFollowsArtistsOrUsersExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final ModelObjectType type = ModelObjectType.ARTIST;
  private static final String[] ids = new String[]{"0LcJLqbBmaGUft1e9Mm8HV"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final CheckCurrentUserFollowsArtistsOrUsersRequest checkCurrentUserFollowsArtistsOrUsersRequest =
    spotifyApi.checkCurrentUserFollowsArtistsOrUsers(type, ids)
      .build();

  public static void checkCurrentUserFollowsArtistsOrUsers_Sync() {
    try {
      final Boolean[] booleans = checkCurrentUserFollowsArtistsOrUsersRequest.execute();

      System.out.println("Length: " + booleans.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void checkCurrentUserFollowsArtistsOrUsers_Async() {
    try {
      final CompletableFuture<Boolean[]> booleansFuture = checkCurrentUserFollowsArtistsOrUsersRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Boolean[] booleans = booleansFuture.join();

      System.out.println("Length: " + booleans.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    checkCurrentUserFollowsArtistsOrUsers_Sync();
    checkCurrentUserFollowsArtistsOrUsers_Async();
  }
}
