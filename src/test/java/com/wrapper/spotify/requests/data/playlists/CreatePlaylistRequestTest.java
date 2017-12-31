package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.Playlist;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CreatePlaylistRequestTest {

  @Test
  public void shouldCreatePlaylist_async() throws Exception {
    final Api api = Api.builder().accessToken("someAccessToken").build();

    final CreatePlaylistRequest request = api.createPlaylist("thelinmichael", "Coolest playlist")
            .public_(true)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/CreatePlaylistRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Playlist> playlistFuture = request.postAsync();

    Futures.addCallback(playlistFuture, new FutureCallback<Playlist>() {
      @Override
      public void onSuccess(Playlist playlist) {
        assertNotNull(playlist);
        assertFalse(playlist.getIsCollaborative());
        assertEquals("New playlist description", playlist.getDescription());
        assertEquals("http://open.spotify.com/user/1153065250/playlist/2oo3exZoJwBXwdYJDoe0Ru", playlist.getExternalUrls().get("spotify"));
        assertEquals(0, playlist.getFollowers().getTotal());
        assertEquals("https://api.spotify.com/v1/users/1153065250/playlists/2oo3exZoJwBXwdYJDoe0Ru", playlist.getHref());
        assertEquals("2oo3exZoJwBXwdYJDoe0Ru", playlist.getId());
        assertEquals(0, playlist.getImages().length);
        assertEquals("New Playlist", playlist.getName());
        assertNotNull(playlist.getOwner());
        assertFalse(playlist.getIsPublicAccess());
        assertEquals(0, playlist.getTracks().getTotal());
        assertEquals(ModelObjectType.PLAYLIST, playlist.getType());
        assertEquals("spotify:user:1153065250:playlist:2oo3exZoJwBXwdYJDoe0Ru", playlist.getUri());

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

    final CreatePlaylistRequest request = api.createPlaylist("thelinmichael", "title")
            .public_(true)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/CreatePlaylistRequest.json"))
            .build();

    final Playlist playlist = request.post();

    assertNotNull(playlist);
    assertFalse(playlist.getIsCollaborative());
    assertEquals("New playlist description", playlist.getDescription());
    assertEquals("http://open.spotify.com/user/1153065250/playlist/2oo3exZoJwBXwdYJDoe0Ru", playlist.getExternalUrls().get("spotify"));
    assertEquals(0, playlist.getFollowers().getTotal());
    assertEquals("https://api.spotify.com/v1/users/1153065250/playlists/2oo3exZoJwBXwdYJDoe0Ru", playlist.getHref());
    assertEquals("2oo3exZoJwBXwdYJDoe0Ru", playlist.getId());
    assertEquals(0, playlist.getImages().length);
    assertEquals("New Playlist", playlist.getName());
    assertNotNull(playlist.getOwner());
    assertFalse(playlist.getIsPublicAccess());
    assertEquals(0, playlist.getTracks().getTotal());
    assertEquals(ModelObjectType.PLAYLIST, playlist.getType());
    assertEquals("spotify:user:1153065250:playlist:2oo3exZoJwBXwdYJDoe0Ru", playlist.getUri());
  }

}
