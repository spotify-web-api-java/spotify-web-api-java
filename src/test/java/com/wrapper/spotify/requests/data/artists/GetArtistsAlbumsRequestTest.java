package com.wrapper.spotify.requests.data.artists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.AlbumType;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetArtistsAlbumsRequestTest {

  @Test
  public void shouldGetAlbumResultForArtistId_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetArtistsAlbumsRequest request = api.getArtistsAlbums("1vCWHaC5f2uS3yhpwWbIA6")
            .limit(2)
            .album_type(AlbumType.SINGLE.getType())
            .market(CountryCode.US)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/artists/GetArtistsAlbumsRequest.json"))
            .build();

    final Future<Paging<AlbumSimplified>> requestFuture = request.executeAsync();
    final Paging<AlbumSimplified> albumSimplifiedPaging = requestFuture.get();

    assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=0&limit=2&album_type=single&market=US", albumSimplifiedPaging.getHref());
    assertEquals(2, (int) albumSimplifiedPaging.getLimit());
    assertEquals(0, (int) albumSimplifiedPaging.getOffset());
    assertEquals(46, (int) albumSimplifiedPaging.getTotal());
    assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=2&limit=2&album_type=single&market=US", albumSimplifiedPaging.getNext());
    assertNull(albumSimplifiedPaging.getPrevious());

    AlbumSimplified[] albums = albumSimplifiedPaging.getItems();
    assertEquals(2, albums.length);

    AlbumSimplified firstAlbum = albums[0];
    assertEquals(AlbumType.SINGLE, firstAlbum.getAlbumType());
    assertEquals("https://open.spotify.com/album/31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/albums/31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getHref());
    assertEquals("31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getId());
    assertNotNull(firstAlbum.getImages());
  }

  @Test
  public void shouldGetAlbumResultForArtistId_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetArtistsAlbumsRequest request = api.getArtistsAlbums("1vCWHaC5f2uS3yhpwWbIA6")
            .limit(2)
            .album_type(AlbumType.SINGLE.getType())
            .market(CountryCode.US)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/artists/GetArtistsAlbumsRequest.json"))
            .build();

    final Paging<AlbumSimplified> albumSearchResult = request.execute();

    assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=0&limit=2&album_type=single&market=US", albumSearchResult.getHref());
    assertEquals(2, (int) albumSearchResult.getLimit());
    assertEquals(0, (int) albumSearchResult.getOffset());
    assertEquals(46, (int) albumSearchResult.getTotal());
    assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=2&limit=2&album_type=single&market=US", albumSearchResult.getNext());
    assertNull(albumSearchResult.getPrevious());

    final AlbumSimplified[] albums = albumSearchResult.getItems();
    assertEquals(2, albums.length);

    AlbumSimplified firstAlbum = albums[0];
    assertEquals(AlbumType.SINGLE, firstAlbum.getAlbumType());
    assertEquals("https://open.spotify.com/album/31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/albums/31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getHref());
    assertEquals("31gLK2SKwtqNogrwMRIyQ0", firstAlbum.getId());
    assertNotNull(firstAlbum.getImages());
  }

}
