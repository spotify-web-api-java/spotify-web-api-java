package data.tracks;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.tracks.GetSeveralTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetSeveralTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String[] ids = new String[]{"01iyCAUm8EvOFqVWYJ3dVX"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetSeveralTracksRequest getSeveralTracksRequest = spotifyApi.getSeveralTracks(ids)
//          .market(CountryCode.SE)
    .build();

  public static void getSeveralTracks_Sync() {
    try {
      final Track[] tracks = getSeveralTracksRequest.execute();

      System.out.println("Length: " + tracks.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getSeveralTracks_Async() {
    try {
      final CompletableFuture<Track[]> tracksFuture = getSeveralTracksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Track[] tracks = tracksFuture.join();

      System.out.println("Length: " + tracks.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getSeveralTracks_Sync();
    getSeveralTracks_Async();
  }
}
