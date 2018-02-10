package data.tracks;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import com.wrapper.spotify.requests.data.tracks.GetAudioFeaturesForSeveralTracksRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAudioFeaturesForSeveralTracks_Async() {
    try {
      final Future<AudioFeatures[]> audioFeaturesFuture = getAudioFeaturesForSeveralTracksRequest.executeAsync();

      // ...

      final AudioFeatures[] audioFeatures = audioFeaturesFuture.get();

      System.out.println("Length: " + audioFeatures.length);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}