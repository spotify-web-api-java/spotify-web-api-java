package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class GetListOfUsersPlaylistsRequestTest {

  @Test
  public void shouldGetPlaylistsForUser_async() throws Exception {
    final String accessToken = "someAccessToken";
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetListOfUsersPlaylistsRequest request = api
            .getListOfUsersPlaylists("wizzler")
            .limit(10)
            .offset(2)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetListOfUsersPlaylistsRequest.json"))
            .build();

    final Future<Paging<PlaylistSimplified>> playlistsPageFuture = request.executeAsync();
    final Paging<PlaylistSimplified> playlistSimplifiedPaging = playlistsPageFuture.get();

    assertTrue(playlistSimplifiedPaging.getTotal() >= 0);
    assertNull(playlistSimplifiedPaging.getNext());
    assertEquals("https://api.spotify.com/v1/users/wizzler/playlists?offset=0&limit=10", playlistSimplifiedPaging.getPrevious());
    assertEquals(10, (int) playlistSimplifiedPaging.getLimit());
    assertEquals(2, (int) playlistSimplifiedPaging.getOffset());
    assertEquals("https://api.spotify.com/v1/users/wizzler/playlists?offset=2&limit=10", playlistSimplifiedPaging.getHref());

    final PlaylistSimplified simplePlaylist = playlistSimplifiedPaging.getItems()[0];
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
    assertEquals(ModelObjectType.PLAYLIST, simplePlaylist.getType());
    assertEquals("spotify:user:wizzler:playlist:" + playlistId, simplePlaylist.getUri());
    assertEquals(1, simplePlaylist.getImages().length);
    assertEquals("https://i.scdn.co/image/418ce596327dc3a0f4d377db80421bffb3b94a9a", simplePlaylist.getImages()[0].getUrl());
    assertEquals(300, (int) simplePlaylist.getImages()[0].getWidth());
    assertEquals(300, (int) simplePlaylist.getImages()[0].getHeight());
  }

  @Test
  public void shouldGetPlaylistsForUser_sync() throws Exception {
    final String accessToken = "myVeryLongAccessToken";
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetListOfUsersPlaylistsRequest request = api
            .getListOfUsersPlaylists("wizzler")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetListOfUsersPlaylistsRequest.json"))
            .build();

    final Paging<PlaylistSimplified> playlistsPage = request.execute();

    assertTrue(playlistsPage.getTotal() >= 0);
    assertNull(playlistsPage.getNext());
    assertEquals("https://api.spotify.com/v1/users/wizzler/playlists?offset=0&limit=10", playlistsPage.getPrevious());
    assertEquals(10, (int) playlistsPage.getLimit());
    assertEquals(2, (int) playlistsPage.getOffset());
    assertEquals("https://api.spotify.com/v1/users/wizzler/playlists?offset=2&limit=10", playlistsPage.getHref());

    final PlaylistSimplified simplePlaylist = playlistsPage.getItems()[0];
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
    assertEquals(ModelObjectType.PLAYLIST, simplePlaylist.getType());
    assertEquals("spotify:user:wizzler:playlist:" + playlistId, simplePlaylist.getUri());
    assertEquals(1, simplePlaylist.getImages().length);
    assertEquals("https://i.scdn.co/image/418ce596327dc3a0f4d377db80421bffb3b94a9a", simplePlaylist.getImages()[0].getUrl());
    assertEquals(300, (int) simplePlaylist.getImages()[0].getWidth());
    assertEquals(300, (int) simplePlaylist.getImages()[0].getHeight());
  }

}
