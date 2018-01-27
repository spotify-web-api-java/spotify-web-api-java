package data.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.data.player.TransferUsersPlaybackRequest;

import java.util.concurrent.Future;

public class TransferUsersPlaybackExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final JsonArray deviceIds = new JsonParser().parse("[\"5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e\"]").getAsJsonArray();

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final TransferUsersPlaybackRequest transferUsersPlaybackRequest = spotifyApi
          .transferUsersPlayback(deviceIds)
          .play(false)
          .build();

  public static void transferUsersPlayback_Sync() {
    try {
      final String string = transferUsersPlaybackRequest.execute();

      System.out.println("Empty String: " + string);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void transferUsersPlayback_Async() {
    try {
      final Future<String> stringFuture = transferUsersPlaybackRequest.executeAsync();

      // ...

      final String string = stringFuture.get();

      System.out.println("Empty String: " + string);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}