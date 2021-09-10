package data.follow;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.follow.UnfollowArtistsOrUsersRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class UnfollowArtistsOrUsersExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final ModelObjectType type = ModelObjectType.ARTIST;
  private static final String[] ids = new String[]{"0LcJLqbBmaGUft1e9Mm8HV"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final UnfollowArtistsOrUsersRequest unfollowArtistsOrUsersRequest = spotifyApi
    .unfollowArtistsOrUsers(type, ids)
    .build();

  public static void unfollowArtistsOrUsers_Sync() {
    try {
      final String string = unfollowArtistsOrUsersRequest.execute();

      System.out.println("Null: " + string);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void unfollowArtistsOrUsers_Async() {
    try {
      final CompletableFuture<String> stringFuture = unfollowArtistsOrUsersRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final String string = stringFuture.join();

      System.out.println("Null: " + string);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    unfollowArtistsOrUsers_Sync();
    unfollowArtistsOrUsers_Async();
  }
}
