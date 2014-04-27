package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.SpotifyProtos.Album;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.BadFieldException;
import se.michaelthelin.spotify.exceptions.NotFoundException;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class AlbumsRequestTest {

  @Test
  public void shouldGetAlbumResultForIds_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("albums.json");
    final AlbumsRequest request = api.albums().id("41MnTivkwTO3UUJ8DrqEJJ", "6JWc4iAiJ9FjyK0B59ABb4").httpManager(mockedHttpManager).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<List<Album>> albumsFuture = request.getAlbumsAsync();
    Futures.addCallback(albumsFuture, new FutureCallback<List<Album>>() {

      @Override
      public void onSuccess(List<Album> albums) {
        assertEquals(2, albums.size());

        Album firstAlbum = albums.get(0);
        assertEquals("41MnTivkwTO3UUJ8DrqEJJ", firstAlbum.getId());

        Album secondAlbum = albums.get(1);
        assertEquals("6JWc4iAiJ9FjyK0B59ABb4", secondAlbum.getId());

        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail();
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetAlbumResultForIds_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("albums.json");
    final AlbumsRequest request = api.albums().id("41MnTivkwTO3UUJ8DrqEJJ", "6JWc4iAiJ9FjyK0B59ABb4").httpManager(mockedHttpManager).build();

    List<Album> albums = request.getAlbums();

    assertEquals(2, albums.size());

    Album firstAlbum = albums.get(0);
    assertEquals("41MnTivkwTO3UUJ8DrqEJJ", firstAlbum.getId());

    Album secondAlbum = albums.get(1);
    assertEquals("6JWc4iAiJ9FjyK0B59ABb4", secondAlbum.getId());
  }

  @Test
  public void shouldGetAlbumResultForArtistId_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("albums.json");
    final AlbumsRequest request = api.albums().forArtist("0oSGxfWSnnOXhD2fKuz2Gy").httpManager(mockedHttpManager).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<List<Album>> albumsFuture = request.getAlbumsAsync();

    Futures.addCallback(albumsFuture, new FutureCallback<List<Album>>() {
      @Override
      public void onSuccess(List<Album> albums) {
        assertEquals(2, albums.size());

        Album firstAlbum = albums.get(0);
        assertEquals("41MnTivkwTO3UUJ8DrqEJJ", firstAlbum.getId());

        Album secondAlbum = albums.get(1);
        assertEquals("6JWc4iAiJ9FjyK0B59ABb4", secondAlbum.getId());

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

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("albums.json");
    final AlbumsRequest request = api.albums().forArtist("0oSGxfWSnnOXhD2fKuz2Gy").httpManager(mockedHttpManager).build();

    final List<Album> albums = request.getAlbums();

    assertEquals(2, albums.size());

    final Album firstAlbum = albums.get(0);
    assertEquals("41MnTivkwTO3UUJ8DrqEJJ", firstAlbum.getId());

    final Album secondAlbum = albums.get(1);
    assertEquals("6JWc4iAiJ9FjyK0B59ABb4", secondAlbum.getId());
  }

  @Test
  public void shouldFailForBadField_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("error_bad-field.json");
    final AlbumsRequest request = api.albums().id("41MnTivkwTO3UUJ8DrqEJJ", "6JWc4iAiJ9FjyK0zz").httpManager(mockedHttpManager).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<List<Album>> albumFuture = request.getAlbumsAsync();

    Futures.addCallback(albumFuture, new FutureCallback<List<Album>>() {
      @Override
      public void onSuccess(List<Album> album) {
        fail();
      }

      @Override
      public void onFailure(Throwable throwable) {
        TestCase.assertEquals(throwable.getClass(), BadFieldException.class);
        asyncCompleted.countDown();
      }

    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldFailForNotFound_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("error_id-not-found.json");
    final AlbumsRequest request = api.albums().id("41MnTivkwTO3UUJ8DrqEJJ", "41MnTivkwTO3UUJ8DrqEJA").httpManager(mockedHttpManager).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<List<Album>> albumFuture = request.getAlbumsAsync();

    Futures.addCallback(albumFuture, new FutureCallback<List<Album>>() {
      @Override
      public void onSuccess(List<Album> album) {
        fail();
      }

      @Override
      public void onFailure(Throwable throwable) {
        TestCase.assertEquals(throwable.getClass(), NotFoundException.class);
        asyncCompleted.countDown();
      }

    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

}
