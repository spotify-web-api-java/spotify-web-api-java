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
import se.michaelthelin.spotify.exceptions.BadFieldException;
import se.michaelthelin.spotify.exceptions.NotFoundException;
import se.michaelthelin.spotify.models.Album;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class AlbumRequestTest {

  @Test
  public void shouldGetAlbumResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("album.json");
    final AlbumRequest request = api.getAlbum("7e0ij2fpWaxOEHv5fUYZjd").httpManager(mockedHttpManager).build();

    final SettableFuture<Album> albumFuture = request.getAsync();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    Futures.addCallback(albumFuture, new FutureCallback<Album>() {
      @Override
      public void onSuccess(Album album) {
        assertNotNull(album);
        assertEquals("0sNOF9WDwhWunNAHPD3Baj", album.getId());
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Call to get album failed");
      }

    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldFailForNonExistingAlbumId_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("error_id-not-found.json");
    final AlbumRequest request = api.getAlbum("7e0ij2fpWaxOEHv5fUYZjd").httpManager(mockedHttpManager).build();

    final SettableFuture<Album> albumFuture = request.getAsync();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    Futures.addCallback(albumFuture, new FutureCallback<Album>() {
      @Override
      public void onSuccess(Album album) {
        fail("Expected call to get album to fail");
      }

      @Override
      public void onFailure(Throwable throwable) {
        assertEquals(throwable.getClass(), NotFoundException.class);
        asyncCompleted.countDown();
      }

    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldFailForBadField_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("error_bad-field.json");
    final AlbumRequest request = api.getAlbum("7e0ij2fpWaxOEHv5fUYZjd").httpManager(mockedHttpManager).build();

    final SettableFuture<Album> albumFuture = request.getAsync();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    Futures.addCallback(albumFuture, new FutureCallback<Album>() {
      @Override
      public void onSuccess(Album album) {
        fail();
      }

      @Override
      public void onFailure(Throwable throwable) {
        assertEquals(throwable.getClass(), BadFieldException.class);
        asyncCompleted.countDown();
      }

    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetAlbumResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("album.json");
    final AlbumRequest request = api.getAlbum("7e0ij2fpWaxOEHv5fUYZjd").httpManager(mockedHttpManager).build();

    Album album = request.get();
    assertNotNull(album);
    assertEquals("0sNOF9WDwhWunNAHPD3Baj", album.getId());
  }
}
