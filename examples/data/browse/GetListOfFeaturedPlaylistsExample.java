package data.browse;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.FeaturedPlaylists;
import se.michaelthelin.spotify.requests.data.browse.GetListOfFeaturedPlaylistsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetListOfFeaturedPlaylistsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetListOfFeaturedPlaylistsRequest getListOfFeaturedPlaylistsRequest = spotifyApi
    .getListOfFeaturedPlaylists()
//          .country(CountryCode.SE)
//          .limit(10)
//          .offset(0)
//          .timestamp(new Date(1414054800000L))
    .build();

  public static void getListOfFeaturedPlaylists_Sync() {
    try {
      final FeaturedPlaylists featuredPlaylists = getListOfFeaturedPlaylistsRequest.execute();

      System.out.println("Message: " + featuredPlaylists.getMessage());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getListOfFeaturedPlaylists_Async() {
    try {
      final CompletableFuture<FeaturedPlaylists> featuredPlaylistsFuture = getListOfFeaturedPlaylistsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final FeaturedPlaylists featuredPlaylists = featuredPlaylistsFuture.join();

      System.out.println("Message: " + featuredPlaylists.getMessage());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getListOfFeaturedPlaylists_Sync();
    getListOfFeaturedPlaylists_Async();
  }
}
