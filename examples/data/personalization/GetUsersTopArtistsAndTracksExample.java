package data.personalization;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.personalization.GetUsersTopArtistsAndTracksRequest;

import java.util.concurrent.Future;

public class GetUsersTopArtistsAndTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final ModelObjectType type = ModelObjectType.ARTIST;

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetUsersTopArtistsAndTracksRequest getUsersTopArtistsAndTracksRequest = spotifyApi
          .getUsersTopArtistsAndTracks(type)
          .limit(10)
          .offset(0)
          .time_range("medium_term")
          .build();

  @SuppressWarnings("unchecked")
  public static void getUsersTopArtistsAndTracks_Sync() {
    try {
      final Paging<Artist> artistPaging = getUsersTopArtistsAndTracksRequest.execute();

      System.out.println("Total: " + artistPaging.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getUsersTopArtistsAndTracks_Async() {
    try {
      final Future<Paging<Artist>> pagingFuture = getUsersTopArtistsAndTracksRequest.executeAsync();

      // ...

      final Paging<Artist> artistPaging = pagingFuture.get();

      System.out.println("Total: " + artistPaging.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}