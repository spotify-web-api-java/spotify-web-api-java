package data.tracks;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.AudioAnalysis;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioAnalysisForTrackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

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
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAudioAnalysisForTrack_Async() {
    try {
      final CompletableFuture<AudioAnalysis> audioAnalysisFuture = getAudioAnalysisForTrackRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final AudioAnalysis audioAnalysis = audioAnalysisFuture.join();

      System.out.println("Track duration: " + audioAnalysis.getTrack().getDuration());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getAudioAnalysisForTrack_Sync();
    getAudioAnalysisForTrack_Async();
  }
}
