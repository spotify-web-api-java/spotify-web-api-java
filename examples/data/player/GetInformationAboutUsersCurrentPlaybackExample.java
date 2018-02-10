package data.player;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.requests.data.player.GetInformationAboutUsersCurrentPlaybackRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetInformationAboutUsersCurrentPlaybackExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
          spotifyApi.getInformationAboutUsersCurrentPlayback()
                  .market(CountryCode.SE)
                  .build();

  public static void getInformationAboutUsersCurrentPlayback_Sync() {
    try {
      final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

      System.out.println("Timestamp: " + currentlyPlayingContext.getTimestamp());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getInformationAboutUsersCurrentPlayback_Async() {
    try {
      final Future<CurrentlyPlayingContext> currentlyPlayingContextFuture = getInformationAboutUsersCurrentPlaybackRequest.executeAsync();

      // ...

      final CurrentlyPlayingContext currentlyPlayingContext = currentlyPlayingContextFuture.get();

      System.out.println("Timestamp: " + currentlyPlayingContext.getTimestamp());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}