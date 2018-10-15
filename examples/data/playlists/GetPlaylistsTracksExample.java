package data.playlists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetPlaylistsTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetPlaylistsTracksRequest getPlaylistsTracksRequest = spotifyApi
          .getPlaylistsTracks(playlistId)
          .fields("description")
          .limit(10)
          .offset(0)
          .market(CountryCode.SE)
          .build();

  public static void getPlaylistsTracks_Sync() {
    try {
      final Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsTracksRequest.execute();

      System.out.println("Total: " + playlistTrackPaging.getTotal());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getPlaylistsTracks_Async() {
    try {
      final Future<Paging<PlaylistTrack>> pagingFuture = getPlaylistsTracksRequest.executeAsync();

      // ...

      final Paging<PlaylistTrack> playlistTrackPaging = pagingFuture.get();

      System.out.println("Total: " + playlistTrackPaging.getTotal());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}