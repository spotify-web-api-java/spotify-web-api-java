package data.artists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.artists.GetArtistsAlbumsRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetArtistsAlbumsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "0LcJLqbBmaGUft1e9Mm8HV";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetArtistsAlbumsRequest getArtistsAlbumsRequest = spotifyApi.getArtistsAlbums(id)
          .album_type("album")
          .limit(10)
          .offset(0)
          .market(CountryCode.SE)
          .build();

  public static void getArtistsAlbums_Sync() {
    try {
      final Paging<AlbumSimplified> albumSimplifiedPaging = getArtistsAlbumsRequest.execute();

      System.out.println("Total: " + albumSimplifiedPaging.getTotal());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getArtistsAlbums_Async() {
    try {
      final Future<Paging<AlbumSimplified>> pagingFuture = getArtistsAlbumsRequest.executeAsync();

      // ...

      final Paging<AlbumSimplified> albumSimplifiedPaging = pagingFuture.get();

      System.out.println("Total: " + albumSimplifiedPaging.getTotal());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}