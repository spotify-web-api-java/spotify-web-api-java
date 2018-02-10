package data.tracks;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.tracks.GetSeveralTracksRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetSeveralTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String[] ids = new String[]{"01iyCAUm8EvOFqVWYJ3dVX"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetSeveralTracksRequest getSeveralTracksRequest = spotifyApi.getSeveralTracks(ids)
          .market(CountryCode.SE)
          .build();

  public static void getSeveralTracks_Sync() {
    try {
      final Track[] tracks = getSeveralTracksRequest.execute();

      System.out.println("Length: " + tracks.length);
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getSeveralTracks_Async() {
    try {
      final Future<Track[]> tracksFuture = getSeveralTracksRequest.executeAsync();

      // ...

      final Track[] tracks = tracksFuture.get();

      System.out.println("Length: " + tracks.length);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}