package data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Image;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistCoverImageRequest;

import java.util.concurrent.Future;

public class GetPlaylistCoverImageExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String userId = "abbaspotify";
  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetPlaylistCoverImageRequest getPlaylistCoverImageRequest = spotifyApi
          .getPlaylistCoverImage(userId, playlistId)
          .build();

  public static void getPlaylistCoverImage_Sync() {
    try {
      final Image[] images = getPlaylistCoverImageRequest.execute();

      System.out.println("Length: " + images.length);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getPlaylistCoverImage_Async() {
    try {
      final Future<Image[]> imagesFuture = getPlaylistCoverImageRequest.executeAsync();

      // ...

      final Image[] images = imagesFuture.get();

      System.out.println("Length: " + images.length);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}