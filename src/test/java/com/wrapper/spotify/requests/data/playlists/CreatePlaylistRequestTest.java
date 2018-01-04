package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.Playlist;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class CreatePlaylistRequestTest {

  @Test
  public void shouldCreatePlaylist_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("someAccessToken").build();

    final CreatePlaylistRequest request = api.createPlaylist("thelinmichael", "Coolest playlist")
            .public_(true)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/CreatePlaylistRequest.json"))
            .build();

    final Future<Playlist> requestFuture = request.executeAsync();
    final Playlist playlist = requestFuture.get();

    assertNotNull(playlist);
    assertFalse(playlist.getIsCollaborative());
    assertEquals("New playlist description", playlist.getDescription());
    assertEquals("http://open.spotify.com/user/1153065250/playlist/2oo3exZoJwBXwdYJDoe0Ru", playlist.getExternalUrls().get("spotify"));
    assertEquals(0, (int) playlist.getFollowers().getTotal());
    assertEquals("https://api.spotify.com/v1/users/1153065250/playlists/2oo3exZoJwBXwdYJDoe0Ru", playlist.getHref());
    assertEquals("2oo3exZoJwBXwdYJDoe0Ru", playlist.getId());
    assertEquals(0, playlist.getImages().length);
    assertEquals("New Playlist", playlist.getName());
    assertNotNull(playlist.getOwner());
    assertFalse(playlist.getIsPublicAccess());
    assertEquals(0, (int) playlist.getTracks().getTotal());
    assertEquals(ModelObjectType.PLAYLIST, playlist.getType());
    assertEquals("spotify:user:1153065250:playlist:2oo3exZoJwBXwdYJDoe0Ru", playlist.getUri());
  }

  @Test
  public void shouldCreatePlaylist_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("someAccessToken").build();

    final CreatePlaylistRequest request = api.createPlaylist("thelinmichael", "title")
            .public_(true)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/CreatePlaylistRequest.json"))
            .build();

    final Playlist playlist = request.execute();

    assertNotNull(playlist);
    assertFalse(playlist.getIsCollaborative());
    assertEquals("New playlist description", playlist.getDescription());
    assertEquals("http://open.spotify.com/user/1153065250/playlist/2oo3exZoJwBXwdYJDoe0Ru", playlist.getExternalUrls().get("spotify"));
    assertEquals(0, (int) playlist.getFollowers().getTotal());
    assertEquals("https://api.spotify.com/v1/users/1153065250/playlists/2oo3exZoJwBXwdYJDoe0Ru", playlist.getHref());
    assertEquals("2oo3exZoJwBXwdYJDoe0Ru", playlist.getId());
    assertEquals(0, playlist.getImages().length);
    assertEquals("New Playlist", playlist.getName());
    assertNotNull(playlist.getOwner());
    assertFalse(playlist.getIsPublicAccess());
    assertEquals(0, (int) playlist.getTracks().getTotal());
    assertEquals(ModelObjectType.PLAYLIST, playlist.getType());
    assertEquals("spotify:user:1153065250:playlist:2oo3exZoJwBXwdYJDoe0Ru", playlist.getUri());
  }

}
