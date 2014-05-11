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

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class AlbumSearchRequestTest {

  @Test
  public void shouldGetAlbumsResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-album.json");
    final AlbumSearchRequest request = api.searchAlbums("The Best Of Keane").httpManager(mockedHttpManager).build();

    final SettableFuture<Page<Album>> searchResultFuture = request.getAlbumsPageAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Page<Album>>() {
      @Override
      public void onSuccess(Page<Album> albumSearchResult) {
        List<Album> albums = albumSearchResult.getItems();

        assertEquals(1, albums.size());

        Album firstAlbum = albums.get(0);
        assertEquals("68NlXKRuJ1YqrhIbwe864y", firstAlbum.getId());
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });

  }

  @Test
  public void shouldGetAlbumsResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-album.json");
    final AlbumSearchRequest request = api.searchAlbums("The Best Of Keane").httpManager(mockedHttpManager).build();

    final Page<Album> albumSearchResult = request.getAlbumsPage();

    final List<Album> albums = albumSearchResult.getItems();

    assertEquals(1, albums.size());

    final Album firstAlbum = albums.get(0);
    assertEquals("68NlXKRuJ1YqrhIbwe864y", firstAlbum.getId());
  }

}
