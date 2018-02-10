package data.tracks;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import com.wrapper.spotify.requests.data.tracks.GetAudioFeaturesForTrackRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetAudioFeaturesForTrackExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "01iyCAUm8EvOFqVWYJ3dVX";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetAudioFeaturesForTrackRequest getAudioFeaturesForTrackRequest = spotifyApi
          .getAudioFeaturesForTrack(id)
          .build();

  public static void getAudioFeaturesForTrack_Sync() {
    try {
      final AudioFeatures audioFeatures = getAudioFeaturesForTrackRequest.execute();

      System.out.println("ID: " + audioFeatures.getId());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAudioFeaturesForTrack_Async() {
    try {
      final Future<AudioFeatures> audioFeaturesFuture = getAudioFeaturesForTrackRequest.executeAsync();

      // ...

      final AudioFeatures audioFeatures = audioFeaturesFuture.get();

      System.out.println("ID: " + audioFeatures.getId());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}