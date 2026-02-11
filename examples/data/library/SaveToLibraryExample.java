package data.library;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.library.SaveToLibraryRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class SaveToLibraryExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final JsonArray uris = JsonParser.parseString("[\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"]").getAsJsonArray();

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final SaveToLibraryRequest saveToLibraryRequest = spotifyApi.saveToLibrary(uris)
    .build();

  public static void saveToLibrary_Sync() {
    try {
      final String string = saveToLibraryRequest.execute();

      System.out.println("Null: " + (string == null));
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void saveToLibrary_Async() {
    try {
      final CompletableFuture<String> stringFuture = saveToLibraryRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final String string = stringFuture.join();

      System.out.println("Null: " + (string == null));
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    saveToLibrary_Sync();
    saveToLibrary_Async();
  }
}
