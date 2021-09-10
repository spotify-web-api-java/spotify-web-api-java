package data.personalization;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.personalization.GetUsersTopArtistsAndTracksRequest;
import se.michaelthelin.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetUsersTopArtistsAndTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final ModelObjectType type = ModelObjectType.ARTIST;

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetUsersTopArtistsAndTracksRequest<? extends IArtistTrackModelObject> getUsersTopArtistsAndTracksRequest = spotifyApi
    .getUsersTopArtistsAndTracks(type)
//          .limit(10)
//          .offset(0)
//          .time_range("medium_term")
    .build();

  public static void getUsersTopArtistsAndTracks_Sync() {
    try {
      final Paging<? extends IArtistTrackModelObject> artistPaging = getUsersTopArtistsAndTracksRequest.execute();

      System.out.println("Total: " + artistPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getUsersTopArtistsAndTracks_Async() {
    try {
      final CompletableFuture<? extends Paging<? extends IArtistTrackModelObject>> pagingFuture = getUsersTopArtistsAndTracksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<? extends IArtistTrackModelObject> artistPaging = pagingFuture.join();

      System.out.println("Total: " + artistPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getUsersTopArtistsAndTracks_Sync();
    getUsersTopArtistsAndTracks_Async();
  }
}
