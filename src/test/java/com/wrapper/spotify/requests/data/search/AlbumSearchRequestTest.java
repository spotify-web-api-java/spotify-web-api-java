package com.wrapper.spotify.requests.data.search;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AlbumSearchRequestTest {

  @Test
  public void shouldGetAlbumsResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final SearchAlbumsRequest request = api.searchAlbums("tania bowra")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/search/AlbumSearchRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final Future<Paging<AlbumSimplified>> requestFuture = request.executeAsync();
    final Paging<AlbumSimplified> albumSimplifiedPaging = requestFuture.get();

    assertEquals("https://api.spotify.com/v1/search?query=tania+bowra&type=album&market=DE&offset=0&limit=20", albumSimplifiedPaging.getHref());
    assertEquals(20, (int) albumSimplifiedPaging.getLimit());
    assertEquals(0, (int) albumSimplifiedPaging.getOffset());
    assertNull(albumSimplifiedPaging.getNext());
    assertNull(albumSimplifiedPaging.getPrevious());
    assertEquals(1, (int) albumSimplifiedPaging.getTotal());

    AlbumSimplified[] albums = albumSimplifiedPaging.getItems();
    assertEquals(1, albums.length);

    AlbumSimplified firstAlbum = albums[0];
    assertEquals("https://open.spotify.com/album/6akEvsycLGftJxYudPjmqK", firstAlbum.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/albums/6akEvsycLGftJxYudPjmqK", firstAlbum.getHref());
    assertEquals("6akEvsycLGftJxYudPjmqK", firstAlbum.getId());
    assertEquals("Place In The Sun", firstAlbum.getName());
    assertEquals(ModelObjectType.ALBUM, firstAlbum.getType());
    assertEquals("spotify:album:6akEvsycLGftJxYudPjmqK", firstAlbum.getUri());
    assertNotNull(firstAlbum.getAvailableMarkets());
    assertFalse(firstAlbum.getAvailableMarkets().length == 0);
  }

  @Test
  public void shouldGetAlbumsResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final SearchAlbumsRequest request = api.searchAlbums("tania bowra")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/search/AlbumSearchRequest.json"))
            .build();

    final Paging<AlbumSimplified> albumSearchResult = request.execute();
    assertEquals("https://api.spotify.com/v1/search?query=tania+bowra&type=album&market=DE&offset=0&limit=20", albumSearchResult.getHref());
    assertEquals(20, (int) albumSearchResult.getLimit());
    assertEquals(0, (int) albumSearchResult.getOffset());
    assertNull(albumSearchResult.getNext());
    assertNull(albumSearchResult.getPrevious());
    assertEquals(1, (int) albumSearchResult.getTotal());

    final AlbumSimplified[] albums = albumSearchResult.getItems();
    assertEquals(1, albums.length);

    AlbumSimplified firstAlbum = albums[0];
    assertEquals("https://open.spotify.com/album/6akEvsycLGftJxYudPjmqK", firstAlbum.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/albums/6akEvsycLGftJxYudPjmqK", firstAlbum.getHref());
    assertEquals("6akEvsycLGftJxYudPjmqK", firstAlbum.getId());
    assertEquals("Place In The Sun", firstAlbum.getName());
    assertEquals(ModelObjectType.ALBUM, firstAlbum.getType());
    assertEquals("spotify:album:6akEvsycLGftJxYudPjmqK", firstAlbum.getUri());
    assertNotNull(firstAlbum.getAvailableMarkets());
    assertFalse(firstAlbum.getAvailableMarkets().length == 0);
  }

}
