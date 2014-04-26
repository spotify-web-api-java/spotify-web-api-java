package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos.Album;
import se.michaelthelin.spotify.exceptions.BadFieldException;
import se.michaelthelin.spotify.exceptions.NotFoundException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlbumRequestTest {

  @Test
  public void shouldGetAlbumResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final AlbumRequest request = api.album().id("7e0ij2fpWaxOEHv5fUYZjd").build();

    // Mock response
    final String albumResponseFixture = JsonUtilTest.readTestData("album.json");
    final AlbumRequest spy = spy(request);
    when(spy.getJson()).thenReturn(albumResponseFixture);

    final CountDownLatch latch = new CountDownLatch(1);

    final ListenableFuture<Album> albumFuture = spy.getAlbumAsync();

    Futures.addCallback(albumFuture, new FutureCallback<Album>() {
      @Override
      public void onSuccess(Album album) {
        assertNotNull(album);
        assertEquals("0sNOF9WDwhWunNAHPD3Baj", album.getId());
        latch.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Call to get album failed");
      }

    });

    latch.await(2, TimeUnit.SECONDS);
  }

  @Test
  public void shouldFailForNonExistingAlbumId_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final AlbumRequest request = api.album().id("7e0ij2fpWaxOEHv5fUYZjd").build();

    // Mock response
    final String albumResponseFixture = JsonUtilTest.readTestData("error_id-not-found.json");
    final AlbumRequest spy = spy(request);
    when(spy.getJson()).thenReturn(albumResponseFixture);

    final CountDownLatch latch = new CountDownLatch(1);

    final ListenableFuture<Album> albumFuture = spy.getAlbumAsync();

    Futures.addCallback(albumFuture, new FutureCallback<Album>() {
      @Override
      public void onSuccess(Album album) {
        fail("Expected call to get album to fail");
      }

      @Override
      public void onFailure(Throwable throwable) {
        assertEquals(throwable.getClass(), NotFoundException.class);
        latch.countDown();
      }

    });

    latch.await(2, TimeUnit.SECONDS);
  }

  @Test
  public void shouldFailForBadField_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final AlbumRequest request = api.album().id("7e0ij2fpWaxOEHv5fUYZjd").build();

    // Mock response
    final String albumResponseFixture = JsonUtilTest.readTestData("error_bad-field.json");
    final AlbumRequest spy = spy(request);
    when(spy.getJson()).thenReturn(albumResponseFixture);

    final CountDownLatch latch = new CountDownLatch(1);

    final ListenableFuture<Album> albumFuture = spy.getAlbumAsync();

    Futures.addCallback(albumFuture, new FutureCallback<Album>() {
      @Override
      public void onSuccess(Album album) {
        fail();
      }

      @Override
      public void onFailure(Throwable throwable) {
        assertEquals(throwable.getClass(), BadFieldException.class);
        latch.countDown();
      }

    });

    latch.await(2, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetAlbumResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;
    final AlbumRequest request = api.album().id("7e0ij2fpWaxOEHv5fUYZjd").build();

    // Mock response
    final String albumResponseFixture = JsonUtilTest.readTestData("album.json");
    AlbumRequest spy = spy(request);
    when(spy.getJson()).thenReturn(albumResponseFixture);

    Album album = spy.getAlbum();
    assertNotNull(album);
    assertEquals("0sNOF9WDwhWunNAHPD3Baj", album.getId());
  }
}
