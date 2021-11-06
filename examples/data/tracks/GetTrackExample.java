package data.tracks;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetTrackExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "01iyCAUm8EvOFqVWYJ3dVX";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
//          .market(CountryCode.SE)
    .build();

  public static void getTrack_Sync() {
    try {
      final Track track = getTrackRequest.execute();

      System.out.println("Name: " + track.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getTrack_Async() {
    try {
      final CompletableFuture<Track> trackFuture = getTrackRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Track track = trackFuture.join();

      System.out.println("Name: " + track.getName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getTrack_Sync();
    getTrack_Async();
  }
}
