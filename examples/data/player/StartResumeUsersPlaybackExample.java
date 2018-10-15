package data.player;

import com.google.gson.JsonParser;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.player.StartResumeUsersPlaybackRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class StartResumeUsersPlaybackExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi
          .startResumeUsersPlayback()
          .context_uri("spotify:album:5zT1JLIj9E57p3e1rFm9Uq")
          .device_id("5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e")
          .offset(new JsonParser().parse("{\"uri\":\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"}").getAsJsonObject())
          .uris(new JsonParser().parse("[\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"]").getAsJsonArray())
          .position_ms(10000)
          .build();

  public static void startResumeUsersPlayback_Sync() {
    try {
      final String string = startResumeUsersPlaybackRequest.execute();

      System.out.println("Null: " + string);
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void startResumeUsersPlayback_Async() {
    try {
      final Future<String> stringFuture = startResumeUsersPlaybackRequest.executeAsync();

      // ...

      final String string = stringFuture.get();

      System.out.println("Null: " + string);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}