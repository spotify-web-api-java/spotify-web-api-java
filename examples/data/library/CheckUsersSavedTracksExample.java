package data.library;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.library.CheckUsersSavedTracksRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CheckUsersSavedTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String[] ids = new String[]{"01iyCAUm8EvOFqVWYJ3dVX"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final CheckUsersSavedTracksRequest checkUsersSavedTracksRequest = spotifyApi.checkUsersSavedTracks(ids)
          .build();

  public static void checkUsersSavedTracks_Sync() {
    try {
      final Boolean[] booleans = checkUsersSavedTracksRequest.execute();

      System.out.println("Length: " + booleans.length);
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void checkUsersSavedTracks_Async() {
    try {
      final Future<Boolean[]> booleansFuture = checkUsersSavedTracksRequest.executeAsync();

      // ...

      final Boolean[] booleans = booleansFuture.get();

      System.out.println("Length: " + booleans.length);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}