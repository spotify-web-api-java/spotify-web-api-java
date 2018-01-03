package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ReplacePlaylistsTracksRequestTest {

  @Test
  public void shouldCreatePlaylist_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("someAccessToken").build();

    final ReplacePlaylistsTracksRequest request = api.replacePlaylistsTracks(
            "userId", "5oEljuMoe9MXH6tBIPbd5e", new String[]{"spotify:track:4iV5W9uYEdYUVa79Axb7Rh"}
    ).setHttpManager(TestUtil.MockedHttpManager.returningString("")).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<String> result = request.putAsync();

    Futures.addCallback(result, new FutureCallback<String>() {
      @Override
      public void onSuccess(String result) {
        assertNotNull(result);
        assertEquals("", result);
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future: " + throwable.getMessage());
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldCreatePlaylist_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("someAccessToken").build();

    final ReplacePlaylistsTracksRequest request = api.replacePlaylistsTracks(
            "userId", "5oEljuMoe9MXH6tBIPbd5e", new String[]{"spotify:track:4iV5W9uYEdYUVa79Axb7Rh"}
    ).setHttpManager(TestUtil.MockedHttpManager.returningString("")).build();
    request.put();
  }
}
