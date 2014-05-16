package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.models.Album;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.SimpleAlbum;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class AlbumsForArtistsRequestTest {

  @Test
  public void shouldGetAlbumResultForArtistId_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-album.json");
    final AlbumsForArtistRequest request = api.getAlbumsForArtist("53A0W3U0s8diEn9RhXQhVz").httpManager(mockedHttpManager).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Page<SimpleAlbum>> albumsFuture = request.getAsync();

    Futures.addCallback(albumsFuture, new FutureCallback<Page<SimpleAlbum>>() {
      @Override
      public void onSuccess(Page<SimpleAlbum> albumSearchResult) {
        List<SimpleAlbum> albums = albumSearchResult.getItems();

        assertEquals(1, albums.size());

        SimpleAlbum firstAlbum = albums.get(0);
        assertEquals("6akEvsycLGftJxYudPjmqK", firstAlbum.getId());
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
  public void shouldGetAlbumResultForArtistId_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-album.json");
    final AlbumsForArtistRequest request = api.getAlbumsForArtist("53A0W3U0s8diEn9RhXQhVz").httpManager(mockedHttpManager).build();

    final Page<SimpleAlbum> albumsPage = request.get();

    final List<SimpleAlbum> albums = albumsPage.getItems();

    assertEquals(1, albums.size());

    final SimpleAlbum firstAlbum = albums.get(0);
    assertEquals("6akEvsycLGftJxYudPjmqK", firstAlbum.getId());
  }

}
