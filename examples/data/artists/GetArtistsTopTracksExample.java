package data.artists;

import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetArtistsTopTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "0LcJLqbBmaGUft1e9Mm8HV";
  private static final CountryCode countryCode = CountryCode.SE;

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetArtistsTopTracksRequest getArtistsTopTracksRequest = spotifyApi
    .getArtistsTopTracks(id, countryCode)
    .build();

  public static void getArtistsTopTracks_Sync() {
    try {
      final Track[] tracks = getArtistsTopTracksRequest.execute();

      System.out.println("Length: " + tracks.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getArtistsTopTracks_Async() {
    try {
      final CompletableFuture<Track[]> artistsFuture = getArtistsTopTracksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Track[] tracks = artistsFuture.join();

      System.out.println("Length: " + tracks.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getArtistsTopTracks_Sync();
    getArtistsTopTracks_Async();
  }
}
