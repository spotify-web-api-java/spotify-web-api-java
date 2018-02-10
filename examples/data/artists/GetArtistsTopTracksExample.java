package data.artists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.artists.GetArtistsTopTracksRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetArtistsTopTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "0LcJLqbBmaGUft1e9Mm8HV";
  private static final CountryCode countryCode = CountryCode.SE;

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetArtistsTopTracksRequest getArtistsTopTracksRequest = spotifyApi
          .getArtistsTopTracks(id, countryCode)
          .build();

  public static void getArtistsTopTracks_Sync() {
    try {
      final Track[] tracks = getArtistsTopTracksRequest.execute();

      System.out.println("Length: " + tracks.length);
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getArtistsTopTracks_Async() {
    try {
      final Future<Track[]> artistsFuture = getArtistsTopTracksRequest.executeAsync();

      // ...

      final Track[] tracks = artistsFuture.get();

      System.out.println("Length: " + tracks.length);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}