package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;

import org.junit.Ignore;
import org.junit.Test;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.Playlist;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

/*
 * TODO: Add negative tests
 */
public class PlaylistRequestTest {

  @Test
  public void shouldCreatePlaylistPage_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final PlaylistRequest request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .httpManager(TestUtil.MockedHttpManager.returningJson("playlist-response.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Playlist> playlistFuture = request.getAsync();

    Futures.addCallback(playlistFuture, new FutureCallback<Playlist>() {
      @Override
      public void onSuccess(Playlist playlist) {
        assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn", playlist.getHref());
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
  public void shouldCreatePlaylistPage() throws Exception {
    final Api api = Api.DEFAULT_API;

    final PlaylistRequest request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .httpManager(TestUtil.MockedHttpManager.returningJson("playlist-response.json"))
            .build();

    final Playlist playlist = request.get();

    assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn", playlist.getHref());
  }

  @Test
  public void shouldBeAbleToHandlePlaylistsWithLocalFiles() throws Exception {
    final Api api = Api.DEFAULT_API;

    final PlaylistRequest request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
        .httpManager(TestUtil.MockedHttpManager.returningJson("playlist-localfiles-response.json"))
        .build();

    Playlist playlist = request.get();
    
    assertTrue(playlist.getTracks().getItems().get(0).getTrack().getAlbum().getAlbumType() == null);

    assertNotNull(playlist);
  }
  
  @Ignore
  @Test
  public void shouldFailFutureIfPlaylistIsNotFound() throws Exception {
  }

  @Test
  @Ignore
  public void shouldFailFutureIfNotAllowedAccess() throws Exception {
  }

  @Test
  @Ignore
  public void shouldFailFutureIfUserDoesNotExist() throws Exception {
  }

  @Test
  @Ignore
  public void shouldThrowExceptionIfPlaylistIsNotFound() throws Exception {
  }

  @Test
  @Ignore
  public void shouldThrowExceptionIfNotAllowedAccess() throws Exception {
  }

}
