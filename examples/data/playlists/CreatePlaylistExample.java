package data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CreatePlaylistExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String userId = "abbaspotify";
  private static final String name = "Abba";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(userId, name)
          .collaborative(false)
          .public_(false)
          .description("Amazing music.")
          .build();

  public static void createPlaylist_Sync() {
    try {
      final Playlist playlist = createPlaylistRequest.execute();

      System.out.println("Name: " + playlist.getName());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void createPlaylist_Async() {
    try {
      final Future<Playlist> playlistFuture = createPlaylistRequest.executeAsync();

      // ...

      final Playlist playlist = playlistFuture.get();

      System.out.println("Name: " + playlist.getName());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}