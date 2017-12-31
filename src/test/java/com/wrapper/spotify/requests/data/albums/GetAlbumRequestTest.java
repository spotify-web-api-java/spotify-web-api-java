package com.wrapper.spotify.requests.data.albums;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAlbumRequestTest {

  @Test
  public void shouldGetAlbumResult_async() throws Exception {
    final Api api = Api.builder().accessToken("AccessToken").build();

    final GetAlbumRequest request = api.getAlbum("4pox3k0CGuwwAknR9GtcoX")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetAlbumRequest.json"))
            .build();

    final SettableFuture<Album> albumFuture = request.getAsync();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    Futures.addCallback(albumFuture, new FutureCallback<Album>() {
      @Override
      public void onSuccess(Album album) {
        assertNotNull(album);
        assertEquals("4pox3k0CGuwwAknR9GtcoX", album.getId());
        assertNotNull(album.getCopyrights());
        assertFalse(album.getCopyrights().length == 0);

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
  public void shouldGetAlbumResult_sync() throws Exception {
    final Api api = Api.builder().accessToken("AccessToken").build();

    final GetAlbumRequest request = api.getAlbum("4pox3k0CGuwwAknR9GtcoX")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetAlbumRequest.json"))
            .build();

    Album album = request.get();

    assertNotNull(album);
    assertEquals("4pox3k0CGuwwAknR9GtcoX", album.getId());
    assertNotNull(album.getCopyrights());
    assertFalse(album.getCopyrights().length == 0);
  }
}
