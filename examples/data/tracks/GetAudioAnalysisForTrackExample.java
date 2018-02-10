package data.tracks;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.AudioAnalysis;
import com.wrapper.spotify.requests.data.tracks.GetAudioAnalysisForTrackRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetAudioAnalysisForTrackExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "01iyCAUm8EvOFqVWYJ3dVX";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetAudioAnalysisForTrackRequest getAudioAnalysisForTrackRequest = spotifyApi
          .getAudioAnalysisForTrack(id)
          .build();

  public static void getAudioAnalysisForTrack_Sync() {
    try {
      final AudioAnalysis audioAnalysis = getAudioAnalysisForTrackRequest.execute();

      System.out.println("Track duration: " + audioAnalysis.getTrack().getDuration());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAudioAnalysisForTrack_Async() {
    try {
      final Future<AudioAnalysis> audioAnalysisFuture = getAudioAnalysisForTrackRequest.executeAsync();

      // ...

      final AudioAnalysis audioAnalysis = audioAnalysisFuture.get();

      System.out.println("Track duration: " + audioAnalysis.getTrack().getDuration());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}