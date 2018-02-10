package data.player;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.Device;
import com.wrapper.spotify.requests.data.player.GetUsersAvailableDevicesRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetUsersAvailableDevicesExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetUsersAvailableDevicesRequest getUsersAvailableDevicesRequest = spotifyApi
          .getUsersAvailableDevices()
          .build();

  public static void getUsersAvailableDevices_Sync() {
    try {
      final Device[] devices = getUsersAvailableDevicesRequest.execute();

      System.out.println("Length: " + devices.length);
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getUsersAvailableDevices_Async() {
    try {
      final Future<Device[]> devicesFuture = getUsersAvailableDevicesRequest.executeAsync();

      // ...

      final Device[] devices = devicesFuture.get();

      System.out.println("Length: " + devices.length);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}