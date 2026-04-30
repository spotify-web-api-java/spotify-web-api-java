package data.playlists;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistCoverImageRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetPlaylistCoverImageExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetPlaylistCoverImageRequest getPlaylistCoverRequest = spotifyApi
    .getPlaylistCoverImage(playlistId)
    .build();

  public static void getPlaylistCover_Sync() {
    try {
      final Image[] images = getPlaylistCoverRequest.execute();

      System.out.println("Length: " + images.length);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getPlaylistCover_Async() {
    try {
      final CompletableFuture<Image[]> imagesFuture = getPlaylistCoverRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Image[] images = imagesFuture.join();

      System.out.println("Length: " + images.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getPlaylistCover_Sync();
    getPlaylistCover_Async();
  }
}
