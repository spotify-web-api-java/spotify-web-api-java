package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.TestConfiguration;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.SimpleAlbum;
import se.michaelthelin.spotify.models.SpotifyEntityType;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class AlbumSearchRequestTest {

  @Test
  public void shouldGetAlbumsResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final AlbumSearchRequest.Builder requestBuilder = api.searchAlbums("tania bowra");
    if (TestConfiguration.USE_MOCK_RESPONSES) {
      requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("search-album.json"));
    }
    final AlbumSearchRequest request = requestBuilder.build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Page<SimpleAlbum>> searchResultFuture = request.getAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Page<SimpleAlbum>>() {
      @Override
      public void onSuccess(Page<SimpleAlbum> albumSearchResult) {
        assertEquals("https://api.spotify.com/v1/search?query=tania+bowra&offset=0&limit=20&type=album", albumSearchResult.getHref());
        assertEquals(20, albumSearchResult.getLimit());
        assertEquals(0, albumSearchResult.getOffset());
        assertEquals("null", albumSearchResult.getNext());
        assertEquals("null", albumSearchResult.getPrevious());
        assertEquals(1, albumSearchResult.getTotal());

        List<SimpleAlbum> albums = albumSearchResult.getItems();
        assertEquals(1, albums.size());

        SimpleAlbum firstAlbum = albums.get(0);
        assertEquals("https://open.spotify.com/album/6akEvsycLGftJxYudPjmqK", firstAlbum.getExternalUrls().get("spotify"));
        assertEquals("https://api.spotify.com/v1/albums/6akEvsycLGftJxYudPjmqK", firstAlbum.getHref());
        assertEquals("6akEvsycLGftJxYudPjmqK", firstAlbum.getId());
        assertEquals("Place In The Sun", firstAlbum.getName());
        assertEquals(SpotifyEntityType.ALBUM, firstAlbum.getType());
        assertEquals("spotify:album:6akEvsycLGftJxYudPjmqK", firstAlbum.getUri());

        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetAlbumsResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final AlbumSearchRequest.Builder requestBuilder = api.searchAlbums("tania bowra");
    if (TestConfiguration.USE_MOCK_RESPONSES) {
      requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("search-album.json")).build();
    }
    final AlbumSearchRequest request = requestBuilder.build();

    final Page<SimpleAlbum> albumSearchResult = request.get();
    assertEquals("https://api.spotify.com/v1/search?query=tania+bowra&offset=0&limit=20&type=album", albumSearchResult.getHref());
    assertEquals(20, albumSearchResult.getLimit());
    assertEquals(0, albumSearchResult.getOffset());
    assertEquals("null", albumSearchResult.getNext());
    assertEquals("null", albumSearchResult.getPrevious());
    assertEquals(1, albumSearchResult.getTotal());

    final List<SimpleAlbum> albums = albumSearchResult.getItems();
    assertEquals(1, albums.size());

    SimpleAlbum firstAlbum = albums.get(0);
    assertEquals("https://open.spotify.com/album/6akEvsycLGftJxYudPjmqK", firstAlbum.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/albums/6akEvsycLGftJxYudPjmqK", firstAlbum.getHref());
    assertEquals("6akEvsycLGftJxYudPjmqK", firstAlbum.getId());
    assertEquals("Place In The Sun", firstAlbum.getName());
    assertEquals(SpotifyEntityType.ALBUM, firstAlbum.getType());
    assertEquals("spotify:album:6akEvsycLGftJxYudPjmqK", firstAlbum.getUri());
  }

}
