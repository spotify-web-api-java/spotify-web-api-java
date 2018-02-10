package data.albums;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.requests.data.albums.GetSeveralAlbumsRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetSeveralAlbumsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String[] ids = new String[]{"0LcJLqbBmaGUft1e9Mm8HV"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetSeveralAlbumsRequest getSeveralAlbumsRequest = spotifyApi.getSeveralAlbums(ids)
          .market(CountryCode.SE)
          .build();

  public static void getSeveralAlbums_Sync() {
    try {
      final Album[] albums = getSeveralAlbumsRequest.execute();

      System.out.println("Length: " + albums.length);
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getSeveralAlbums_Async() {
    try {
      final Future<Album[]> albumsFuture = getSeveralAlbumsRequest.executeAsync();

      // ...

      final Album[] albums = albumsFuture.get();

      System.out.println("Length: " + albums.length);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}