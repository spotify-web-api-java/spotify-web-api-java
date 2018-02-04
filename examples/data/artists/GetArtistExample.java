package data.artists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;

import java.util.concurrent.Future;

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
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getArtist_Async() {
    try {
      final Future<Artist> albumFuture = getArtistRequest.executeAsync();

      // ...

      final Artist artist = albumFuture.get();

      System.out.println("Name: " + artist.getName());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}