package data.playlists;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class CreatePlaylistExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String userId = "abbaspotify";
  private static final String name = "Abba";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(userId, name)
//          .collaborative(false)
//          .public_(false)
//          .description("Amazing music.")
    .build();

  public static void createPlaylist_Sync() {
    try {
      final Playlist playlist = createPlaylistRequest.execute();

      System.out.println("Name: " + playlist.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void createPlaylist_Async() {
    try {
      final CompletableFuture<Playlist> playlistFuture = createPlaylistRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Playlist playlist = playlistFuture.join();

      System.out.println("Name: " + playlist.getName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    createPlaylist_Sync();
    createPlaylist_Async();
  }
}
