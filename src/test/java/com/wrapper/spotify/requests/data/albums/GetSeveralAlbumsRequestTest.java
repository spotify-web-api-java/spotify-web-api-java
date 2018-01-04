package com.wrapper.spotify.requests.data.albums;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.AlbumType;
import com.wrapper.spotify.enums.ReleaseDatePrecision;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetSeveralAlbumsRequestTest {

  @Test
  public void shouldGetAlbumResultForIds_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetSeveralAlbumsRequest request = api.getSeveralAlbums("2hYe61Nd2oOoM6RYCwIma1")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetSeveralAlbumsRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final Future<Album[]> requestFuture = request.executeAsync();
    final Album[] albums = requestFuture.get();

    assertEquals(1, albums.length);

    Album firstAlbum = albums[0];
    assertEquals("2hYe61Nd2oOoM6RYCwIma1", firstAlbum.getId());
    assertEquals(AlbumType.ALBUM, firstAlbum.getAlbumType());
    assertEquals("2013-01-01", firstAlbum.getReleaseDate());
    assertEquals(ReleaseDatePrecision.DAY, firstAlbum.getReleaseDatePrecision());
    assertEquals(2, firstAlbum.getCopyrights().length);

    ArtistSimplified[] artists = firstAlbum.getArtists();
    ArtistSimplified firstArtist = artists[0];
    assertEquals("https://api.spotify.com/v1/artists/53A0W3U0s8diEn9RhXQhVz", firstArtist.getHref());
    assertEquals("53A0W3U0s8diEn9RhXQhVz", firstArtist.getId());

    Paging<TrackSimplified> tracksPage = firstAlbum.getTracks();
    assertEquals("https://api.spotify.com/v1/albums/2hYe61Nd2oOoM6RYCwIma1/tracks?offset=0&limit=50", tracksPage.getHref());
    assertEquals(0, (int) tracksPage.getOffset());
    assertEquals(50, (int) tracksPage.getLimit());
    assertEquals(20, (int) tracksPage.getTotal());
    assertEquals("52J94k3JBYbHlFyg7zAABB", tracksPage.getItems()[0].getId());
  }

  @Test
  public void shouldGetAlbumResultForIds_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetSeveralAlbumsRequest request = api.getSeveralAlbums("2hYe61Nd2oOoM6RYCwIma1")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetSeveralAlbumsRequest.json"))
            .build();

    Album[] albums = request.execute();

    assertEquals(1, albums.length);

    Album firstAlbum = albums[0];
    assertEquals("2hYe61Nd2oOoM6RYCwIma1", firstAlbum.getId());
    assertEquals(AlbumType.ALBUM, firstAlbum.getAlbumType());
    assertEquals("2013-01-01", firstAlbum.getReleaseDate());
    assertEquals(ReleaseDatePrecision.DAY, firstAlbum.getReleaseDatePrecision());

    ArtistSimplified[] artists = firstAlbum.getArtists();
    ArtistSimplified firstArtist = artists[0];
    assertEquals("https://api.spotify.com/v1/artists/53A0W3U0s8diEn9RhXQhVz", firstArtist.getHref());
    assertEquals("53A0W3U0s8diEn9RhXQhVz", firstArtist.getId());

    Paging<TrackSimplified> tracksPage = firstAlbum.getTracks();
    assertEquals("https://api.spotify.com/v1/albums/2hYe61Nd2oOoM6RYCwIma1/tracks?offset=0&limit=50", tracksPage.getHref());
    assertEquals(0, (int) tracksPage.getOffset());
    assertEquals(50, (int) tracksPage.getLimit());
    assertEquals(20, (int) tracksPage.getTotal());
    assertEquals("52J94k3JBYbHlFyg7zAABB", tracksPage.getItems()[0].getId());
  }

  @Test
  public void shouldFailForNotFound_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetSeveralAlbumsRequest request = api.getSeveralAlbums("idontexist")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetSeveralAlbumsRequest_None.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final Future<Album[]> requestFuture = request.executeAsync();
    final Album[] albums = requestFuture.get();

    assertEquals(1, albums.length);
    assertNull(albums[0]);
  }

}
