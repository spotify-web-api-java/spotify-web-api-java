package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.Playlist;
import com.wrapper.spotify.models.SpotifyEntityType;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.*;

public class PlaylistCreationRequestTest {

  @Test
  public void shouldCreatePlaylist_async() throws Exception {
    final Api api = Api.builder().accessToken("someAccessToken").build();

    final PlaylistCreationRequest request = api.createPlaylist("thelinmichael","Coolest playlist")
            .publicAccess(true)
            .httpManager(TestUtil.MockedHttpManager.returningJson("created-playlist.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Playlist> playlistFuture = request.getAsync();

    Futures.addCallback(playlistFuture, new FutureCallback<Playlist>() {
      @Override
      public void onSuccess(Playlist playlist) {
        assertNotNull(playlist);
        assertFalse(playlist.isCollaborative());
        assertNull(playlist.getDescription());
        assertEquals("http://open.spotify.com/user/thelinmichael/playlist/2LfixThJPNO9DAreghF2WK", playlist.getExternalUrls().get("spotify"));
        assertNull(playlist.getFollowers());
        assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/2LfixThJPNO9DAreghF2WK", playlist.getHref());
        assertEquals("2LfixThJPNO9DAreghF2WK", playlist.getId());
        assertEquals(1, playlist.getImages().size());
        assertNull(playlist.getImages().get(0));
        assertEquals("Coolest Playlist", playlist.getName());
        assertNotNull(playlist.getOwner());
        assertTrue(playlist.isPublicAccess());
        assertNull(playlist.getTracks());
        assertEquals(SpotifyEntityType.PLAYLIST, playlist.getType());
        assertEquals("spotify:user:thelinmichael:playlist:2LfixThJPNO9DAreghF2WK", playlist.getUri());

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
    final Api api = Api.builder().accessToken("someAccessToken").build();

    final PlaylistCreationRequest request = api.createPlaylist("thelinmichael","title")
            .publicAccess(true)
            .httpManager(TestUtil.MockedHttpManager.returningJson("created-playlist.json"))
            .build();

    final Playlist playlist = request.get();

    assertFalse(playlist.isCollaborative());
    assertNull(playlist.getDescription());
    assertEquals("http://open.spotify.com/user/thelinmichael/playlist/2LfixThJPNO9DAreghF2WK", playlist.getExternalUrls().get("spotify"));
    assertNull(playlist.getFollowers());
    assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/2LfixThJPNO9DAreghF2WK", playlist.getHref());
    assertEquals("2LfixThJPNO9DAreghF2WK", playlist.getId());
    assertEquals(1, playlist.getImages().size());
    assertNull(playlist.getImages().get(0));
    assertEquals("Coolest Playlist", playlist.getName());
    assertNotNull(playlist.getOwner());
    assertTrue(playlist.isPublicAccess());
    assertNull(playlist.getTracks());
    assertEquals(SpotifyEntityType.PLAYLIST, playlist.getType());
    assertEquals("spotify:user:thelinmichael:playlist:2LfixThJPNO9DAreghF2WK", playlist.getUri());
  }

}
