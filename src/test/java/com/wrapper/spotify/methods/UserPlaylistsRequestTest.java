package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.Paging;
import com.wrapper.spotify.models.PlaylistSimplified;
import com.wrapper.spotify.models.ObjectType;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.*;

public class UserPlaylistsRequestTest {

  @Test
  public void shouldGetPlaylistsForUser_async() throws Exception {
    final String accessToken = "someAccessToken";
    final Api api = Api.builder().build();

    final UserPlaylistsRequest request = api
            .getPlaylistsForUser("wizzler")
            .accessToken(accessToken)
            .limit(10)
            .offset(2)
            .httpManager(TestUtil.MockedHttpManager.returningJson("user-playlists.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Paging<PlaylistSimplified>> playlistsPageFuture = request.getAsync();

    Futures.addCallback(playlistsPageFuture, new FutureCallback<Paging<PlaylistSimplified>>() {
      @Override
      public void onSuccess(Paging<PlaylistSimplified> playlistsPage) {
        assertTrue(playlistsPage.getTotal() >= 0);
        assertNull(playlistsPage.getNext());
        assertEquals("https://api.spotify.com/v1/users/wizzler/playlists?offset=0&limit=10", playlistsPage.getPrevious());
        assertEquals(10, playlistsPage.getLimit());
        assertEquals(2, playlistsPage.getOffset());
        assertEquals("https://api.spotify.com/v1/users/wizzler/playlists?offset=2&limit=10", playlistsPage.getHref());

        final PlaylistSimplified simplePlaylist = playlistsPage.getItems().get(0);
        final String playlistId = simplePlaylist.getId();
        assertNotNull(playlistId);
        assertTrue(playlistId.length() > 0);
        assertEquals(false, simplePlaylist.getIsCollaborative());
        assertEquals("http://open.spotify.com/user/wizzler/playlist/" + playlistId, simplePlaylist.getExternalUrls().get("spotify"));
        assertNotNull(simplePlaylist.getName());
        assertNotNull(simplePlaylist.getOwner());
        assertNotNull(simplePlaylist.getIsPublicAccess());
        assertNotNull(simplePlaylist.getTracks().getHref());
        assertNotNull(simplePlaylist.getTracks().getTotal());
        assertEquals(ObjectType.PLAYLIST, simplePlaylist.getType());
        assertEquals("spotify:user:wizzler:playlist:" + playlistId, simplePlaylist.getUri());
        assertEquals(1, simplePlaylist.getImages().size());
        assertEquals("https://i.scdn.co/image/418ce596327dc3a0f4d377db80421bffb3b94a9a", simplePlaylist.getImages().get(0).getUrl());
        assertNull(simplePlaylist.getImages().get(0).getWidth());
        assertNull(simplePlaylist.getImages().get(0).getHeight());

        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future" + throwable.getMessage());
      }

    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetPlaylistsForUser_sync() throws Exception {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().build();

    final UserPlaylistsRequest request = api
            .getPlaylistsForUser("wizzler")
            .accessToken(accessToken)
            .httpManager(TestUtil.MockedHttpManager.returningJson("user-playlists.json"))
            .build();

    final Paging<PlaylistSimplified> playlistsPage = request.get();

    assertTrue(playlistsPage.getTotal() >= 0);
    assertNull(playlistsPage.getNext());
    assertEquals("https://api.spotify.com/v1/users/wizzler/playlists?offset=0&limit=10", playlistsPage.getPrevious());
    assertEquals(10, playlistsPage.getLimit());
    assertEquals(2, playlistsPage.getOffset());
    assertEquals("https://api.spotify.com/v1/users/wizzler/playlists?offset=2&limit=10", playlistsPage.getHref());

    final PlaylistSimplified simplePlaylist = playlistsPage.getItems().get(0);
    final String playlistId = simplePlaylist.getId();
    assertNotNull(playlistId);
    assertTrue(playlistId.length() > 0);
    assertEquals(false, simplePlaylist.getIsCollaborative());
    assertEquals("http://open.spotify.com/user/wizzler/playlist/" + playlistId, simplePlaylist.getExternalUrls().get("spotify"));
    assertNotNull(simplePlaylist.getName());
    assertNotNull(simplePlaylist.getOwner());
    assertNotNull(simplePlaylist.getIsPublicAccess());
    assertNotNull(simplePlaylist.getTracks().getHref());
    assertNotNull(simplePlaylist.getTracks().getTotal());
    assertEquals(ObjectType.PLAYLIST, simplePlaylist.getType());
    assertEquals("spotify:user:wizzler:playlist:" + playlistId, simplePlaylist.getUri());
    assertEquals(1, simplePlaylist.getImages().size());
    assertEquals("https://i.scdn.co/image/418ce596327dc3a0f4d377db80421bffb3b94a9a", simplePlaylist.getImages().get(0).getUrl());
    assertNull(simplePlaylist.getImages().get(0).getWidth());
    assertNull(simplePlaylist.getImages().get(0).getHeight());
  }

}
