package data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.data.playlists.ReplacePlaylistsTracksRequest;

import java.util.concurrent.Future;

public class ReplacePlaylistsTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String userId = "abbaspotify";
  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";
  private static final String[] uris = new String[]{"uris"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final ReplacePlaylistsTracksRequest replacePlaylistsTracksRequest = spotifyApi
          .replacePlaylistsTracks(userId, playlistId, uris)
          .build();

  public static void replacePlaylistsTracks_Sync() {
    try {
      final String string = replacePlaylistsTracksRequest.execute();

      System.out.println("Empty String: " + string);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void replacePlaylistsTracks_Async() {
    try {
      final Future<String> stringFuture = replacePlaylistsTracksRequest.executeAsync();

      // ...

      final String string = stringFuture.get();

      System.out.println("Empty String: " + string);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}