package data.artists;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetArtistExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "0LcJLqbBmaGUft1e9Mm8HV";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetArtistRequest getArtistRequest = spotifyApi.getArtist(id)
    .build();

  public static void getArtist_Sync() {
    try {
      final Artist artist = getArtistRequest.execute();

      System.out.println("Name: " + artist.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getArtist_Async() {
    try {
      final CompletableFuture<Artist> albumFuture = getArtistRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Artist artist = albumFuture.join();

      System.out.println("Name: " + artist.getName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getArtist_Sync();
    getArtist_Async();
  }
}
