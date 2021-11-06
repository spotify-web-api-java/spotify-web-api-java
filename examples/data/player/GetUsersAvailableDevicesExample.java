package data.player;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.Device;
import se.michaelthelin.spotify.requests.data.player.GetUsersAvailableDevicesRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

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
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getUsersAvailableDevices_Async() {
    try {
      final CompletableFuture<Device[]> devicesFuture = getUsersAvailableDevicesRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Device[] devices = devicesFuture.join();

      System.out.println("Length: " + devices.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getUsersAvailableDevices_Sync();
    getUsersAvailableDevices_Async();
  }
}
