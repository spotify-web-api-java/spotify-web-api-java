package data.artists;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.artists.GetSeveralArtistsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetSeveralArtistsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String[] ids = new String[]{"0LcJLqbBmaGUft1e9Mm8HV"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetSeveralArtistsRequest getSeveralArtistsRequest = spotifyApi.getSeveralArtists(ids)
    .build();

  public static void getSeveralArtists_Sync() {
    try {
      final Artist[] artists = getSeveralArtistsRequest.execute();

      System.out.println("Length: " + artists.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getSeveralArtists_Async() {
    try {
      final CompletableFuture<Artist[]> artistFuture = getSeveralArtistsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Artist[] artists = artistFuture.join();

      System.out.println("Length: " + artists.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getSeveralArtists_Sync();
    getSeveralArtists_Async();
  }
}
