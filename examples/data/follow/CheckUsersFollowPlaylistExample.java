package data.follow;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.follow.CheckUsersFollowPlaylistRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CheckUsersFollowPlaylistExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String ownerId = "abbaspotify";
  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";
  private static final String[] ids = new String[]{"abbaspotify"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final CheckUsersFollowPlaylistRequest checkUsersFollowPlaylistRequest = spotifyApi
          .checkUsersFollowPlaylist(ownerId, playlistId, ids)
          .build();

  public static void checkUsersFollowPlaylist_Sync() {
    try {
      final Boolean[] booleans = checkUsersFollowPlaylistRequest.execute();

      System.out.println("Length: " + booleans.length);
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void checkUsersFollowPlaylist_Async() {
    try {
      final Future<Boolean[]> booleansFuture = checkUsersFollowPlaylistRequest.executeAsync();

      // ...

      final Boolean[] booleans = booleansFuture.get();

      System.out.println("Length: " + booleans.length);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}