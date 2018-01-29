package data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.special.FeaturedPlaylists;
import com.wrapper.spotify.requests.data.browse.GetListOfFeaturedPlaylistsRequest;

import java.util.Date;
import java.util.concurrent.Future;

public class GetListOfFeaturedPlaylistsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetListOfFeaturedPlaylistsRequest getListOfFeaturedPlaylistsRequest = spotifyApi
          .getListOfFeaturedPlaylists()
          .country(CountryCode.SE)
          .limit(10)
          .offset(0)
          .timestamp(new Date(1414054800000L))
          .build();

  public static void getListOfFeaturedPlaylists_Sync() {
    try {
      final FeaturedPlaylists featuredPlaylists = getListOfFeaturedPlaylistsRequest.execute();

      System.out.println("Message: " + featuredPlaylists.getMessage());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getListOfFeaturedPlaylists_Async() {
    try {
      final Future<FeaturedPlaylists> featuredPlaylistsFuture = getListOfFeaturedPlaylistsRequest.executeAsync();

      // ...

      final FeaturedPlaylists featuredPlaylists = featuredPlaylistsFuture.get();

      System.out.println("Message: " + featuredPlaylists.getMessage());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}