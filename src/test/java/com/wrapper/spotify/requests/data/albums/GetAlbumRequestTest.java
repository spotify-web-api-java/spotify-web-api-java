package com.wrapper.spotify.requests.data.albums;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAlbumRequestTest {

  @Test
  public void shouldGetAlbumResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetAlbumRequest request = api.getAlbum("4pox3k0CGuwwAknR9GtcoX")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetAlbumRequest.json"))
            .build();

    final Future<Album> requestFuture = request.executeAsync();
    final Album album = requestFuture.get();
    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    assertNotNull(album);
    assertEquals("4pox3k0CGuwwAknR9GtcoX", album.getId());
    assertNotNull(album.getCopyrights());
    assertFalse(album.getCopyrights().length == 0);
  }

  @Test
  public void shouldGetAlbumResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetAlbumRequest request = api.getAlbum("4pox3k0CGuwwAknR9GtcoX")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetAlbumRequest.json"))
            .build();

    Album album = request.execute();

    assertNotNull(album);
    assertEquals("4pox3k0CGuwwAknR9GtcoX", album.getId());
    assertNotNull(album.getCopyrights());
    assertFalse(album.getCopyrights().length == 0);
  }
}
