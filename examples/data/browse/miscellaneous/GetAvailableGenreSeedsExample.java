package data.browse.miscellaneous;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.data.browse.miscellaneous.GetAvailableGenreSeedsRequest;

import java.util.concurrent.Future;

public class GetAvailableGenreSeedsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetAvailableGenreSeedsRequest getAvailableGenreSeedsRequest = spotifyApi.getAvailableGenreSeeds()
          .build();

  public static void getAvailableGenreSeeds_Sync() {
    try {
      final String[] strings = getAvailableGenreSeedsRequest.execute();

      System.out.println("Length: " + strings.length);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getAvailableGenreSeeds_Async() {
    try {
      final Future<String[]> stringsFuture = getAvailableGenreSeedsRequest.executeAsync();

      // ...

      final String[] strings = stringsFuture.get();

      System.out.println("Length: " + strings.length);
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}