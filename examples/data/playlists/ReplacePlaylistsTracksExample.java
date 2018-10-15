package data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.playlists.ReplacePlaylistsTracksRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ReplacePlaylistsTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";
  private static final String[] uris = new String[]{"uris"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final ReplacePlaylistsTracksRequest replacePlaylistsTracksRequest = spotifyApi
          .replacePlaylistsTracks(playlistId, uris)
          .build();

  public static void replacePlaylistsTracks_Sync() {
    try {
      final String string = replacePlaylistsTracksRequest.execute();

      System.out.println("Null: " + string);
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void replacePlaylistsTracks_Async() {
    try {
      final Future<String> stringFuture = replacePlaylistsTracksRequest.executeAsync();

      // ...

      final String string = stringFuture.get();

      System.out.println("Null: " + string);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}