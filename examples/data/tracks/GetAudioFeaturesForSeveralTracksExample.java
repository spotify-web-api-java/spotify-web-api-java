package data.tracks;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioFeaturesForSeveralTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetAudioFeaturesForSeveralTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String[] ids = new String[]{"01iyCAUm8EvOFqVWYJ3dVX"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetAudioFeaturesForSeveralTracksRequest getAudioFeaturesForSeveralTracksRequest = spotifyApi
    .getAudioFeaturesForSeveralTracks(ids)
    .build();

  public static void getAudioFeaturesForSeveralTracks_Sync() {
    try {
      final AudioFeatures[] audioFeatures = getAudioFeaturesForSeveralTracksRequest.execute();

      System.out.println("Length: " + audioFeatures.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAudioFeaturesForSeveralTracks_Async() {
    try {
      final CompletableFuture<AudioFeatures[]> audioFeaturesFuture = getAudioFeaturesForSeveralTracksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final AudioFeatures[] audioFeatures = audioFeaturesFuture.join();

      System.out.println("Length: " + audioFeatures.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getAudioFeaturesForSeveralTracks_Sync();
    getAudioFeaturesForSeveralTracks_Async();
  }
}
