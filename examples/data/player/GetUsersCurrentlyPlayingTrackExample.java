package data.player;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import com.wrapper.spotify.requests.data.player.GetUsersCurrentlyPlayingTrackRequest;

import java.util.concurrent.Future;

public class GetUsersCurrentlyPlayingTrackExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetUsersCurrentlyPlayingTrackRequest getUsersCurrentlyPlayingTrackRequest = spotifyApi
          .getUsersCurrentlyPlayingTrack()
          .market(CountryCode.SE)
          .build();

  public static void getUsersCurrentlyPlayingTrack_Sync() {
    try {
      final CurrentlyPlaying currentlyPlaying = getUsersCurrentlyPlayingTrackRequest.execute();

      System.out.println("Timestamp: " + currentlyPlaying.getTimestamp());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getUsersCurrentlyPlayingTrack_Async() {
    try {
      final Future<CurrentlyPlaying> currentlyPlayingFuture = getUsersCurrentlyPlayingTrackRequest.executeAsync();

      // ...

      final CurrentlyPlaying currentlyPlaying = currentlyPlayingFuture.get();

      System.out.println("Timestamp: " + currentlyPlaying.getTimestamp());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}