package data.personalization.simplified;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;

import java.util.concurrent.Future;

public class GetUsersTopArtistsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
          .limit(10)
          .offset(0)
          .time_range("medium_term")
          .build();

  public static void getUsersTopArtists_Sync() {
    try {
      final Paging<Artist> artistPaging = getUsersTopArtistsRequest.execute();

      System.out.println("Total: " + artistPaging.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getUsersTopArtists_Async() {
    try {
      final Future<Paging<Artist>> pagingFuture = getUsersTopArtistsRequest.executeAsync();

      // ...

      final Paging<Artist> artistPaging = pagingFuture.get();

      System.out.println("Total: " + artistPaging.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}