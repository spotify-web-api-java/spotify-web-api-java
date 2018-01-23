package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ReplacePlaylistsTracksRequestTest {

  @Test
  public void shouldCreatePlaylist_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("someAccessToken").build();

    final ReplacePlaylistsTracksRequest request = api.replacePlaylistsTracks(
            "userId", "5oEljuMoe9MXH6tBIPbd5e", new String[]{"spotify:track:4iV5W9uYEdYUVa79Axb7Rh"}
    ).setHttpManager(TestUtil.MockedHttpManager.returningString("")).build();

    final Future<String> requestFuture = request.executeAsync();
    final String string = requestFuture.get();

    assertNull(string);
  }

  @Test
  public void shouldCreatePlaylist_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("someAccessToken").build();

    final ReplacePlaylistsTracksRequest request = api.replacePlaylistsTracks(
            "userId", "5oEljuMoe9MXH6tBIPbd5e", new String[]{"spotify:track:4iV5W9uYEdYUVa79Axb7Rh"}
    ).setHttpManager(TestUtil.MockedHttpManager.returningString("")).build();
    request.execute();
  }
}
