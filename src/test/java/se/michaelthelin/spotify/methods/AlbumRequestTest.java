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
import se.michaelthelin.spotify.testutils.AsyncMethodHandler;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
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

    // Use so that the test doesnt finish without checking the results from the asynchronous method
    final AsyncMethodHandler asyncMethodHandler = new AsyncMethodHandler();

    final ListenableFuture<Album> albumFuture = spy.getAlbumAsync();

    Futures.addCallback(albumFuture, new FutureCallback<Album>() {
      @Override
      public void onSuccess(Album album) {
        Throwable potentialError = null;
        try {
          assertNotNull(album);
          assertEquals("0sNOF9WDwhWunNAHPD3Baj", album.getId());
        } catch (Throwable throwable) {
          potentialError = throwable;
        } finally {
          asyncMethodHandler.done(potentialError);
        }
      }

      @Override
      public void onFailure(Throwable throwable) {
        asyncMethodHandler.done(throwable);
      }

    });

    asyncMethodHandler.wait(5, TimeUnit.SECONDS);
    asyncMethodHandler.assertNoErrors();
  }

  @Test
  public void shouldFailForNonExistingAlbumId_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final AlbumRequest request = api.album().id("7e0ij2fpWaxOEHv5fUYZjd").build();

    // Mock response
    final String albumResponseFixture = JsonUtilTest.readTestData("error_id-not-found.json");
    final AlbumRequest spy = spy(request);
    when(spy.getJson()).thenReturn(albumResponseFixture);

    // Use so that the test doesnt finish without checking the results from the asynchronous method
    final AsyncMethodHandler asyncMethodHandler = new AsyncMethodHandler();

    final ListenableFuture<Album> albumFuture = spy.getAlbumAsync();

    Futures.addCallback(albumFuture, new FutureCallback<Album>() {
      @Override
      public void onSuccess(Album album) {
        asyncMethodHandler.done();
      }

      @Override
      public void onFailure(Throwable throwable) {
        asyncMethodHandler.done(throwable);
      }

    });

    asyncMethodHandler.wait(5, TimeUnit.SECONDS);
    asyncMethodHandler.assertErrors();
    asyncMethodHandler.assertErrorType(NotFoundException.class);
  }

  @Test
  public void shouldFailForBadField_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final AlbumRequest request = api.album().id("7e0ij2fpWaxOEHv5fUYZjd").build();

    // Mock response
    final String albumResponseFixture = JsonUtilTest.readTestData("error_bad-field.json");
    final AlbumRequest spy = spy(request);
    when(spy.getJson()).thenReturn(albumResponseFixture);

    // Use so that the test doesnt finish without checking the results from the asynchronous method
    final AsyncMethodHandler asyncMethodHandler = new AsyncMethodHandler();

    final ListenableFuture<Album> albumFuture = spy.getAlbumAsync();

    Futures.addCallback(albumFuture, new FutureCallback<Album>() {
      @Override
      public void onSuccess(Album album) {
        asyncMethodHandler.done();
      }

      @Override
      public void onFailure(Throwable throwable) {
        asyncMethodHandler.done(throwable);
      }

    });

    asyncMethodHandler.wait(5, TimeUnit.SECONDS);
    asyncMethodHandler.assertErrors();
    asyncMethodHandler.assertErrorType(BadFieldException.class);
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
