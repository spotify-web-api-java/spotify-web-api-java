package data.personalization.simplified;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;

import java.util.concurrent.Future;

public class GetUsersTopTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetUsersTopTracksRequest getUsersTopTracksRequest = spotifyApi.getUsersTopTracks()
          .limit(10)
          .offset(0)
          .time_range("medium_term")
          .build();

  public static void getUsersTopTracks_Sync() {
    try {
      final Paging<Track> trackPaging = getUsersTopTracksRequest.execute();

      System.out.println("Total: " + trackPaging.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getUsersTopTracks_Async() {
    try {
      final Future<Paging<Track>> pagingFuture = getUsersTopTracksRequest.executeAsync();

      // ...

      final Paging<Track> trackPaging = pagingFuture.get();

      System.out.println("Total: " + trackPaging.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}