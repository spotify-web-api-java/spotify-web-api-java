package data.artists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.artists.GetArtistsRelatedArtistsRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetArtistsRelatedArtistsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "0LcJLqbBmaGUft1e9Mm8HV";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetArtistsRelatedArtistsRequest getArtistsRelatedArtistsRequest = spotifyApi
          .getArtistsRelatedArtists(id)
          .build();

  public static void getArtistsRelatedArtists_Sync() {
    try {
      final Artist[] artists = getArtistsRelatedArtistsRequest.execute();

      System.out.println("Length: " + artists.length);
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getArtistsRelatedArtists_Async() {
    try {
      final Future<Artist[]> artistsFuture = getArtistsRelatedArtistsRequest.executeAsync();

      // ...

      final Artist[] artists = artistsFuture.get();

      System.out.println("Length: " + artists.length);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}