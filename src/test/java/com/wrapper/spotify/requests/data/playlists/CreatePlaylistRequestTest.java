package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreatePlaylistRequestTest implements ITest<Playlist> {
  private final CreatePlaylistRequest successRequest = SPOTIFY_API
          .createPlaylist("user_id", "playlist_id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/CreatePlaylistRequest.json"))
          .build();

  public CreatePlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Playlist) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Playlist playlist) {
    assertFalse(
            playlist.getIsCollaborative());
    assertNull(
            playlist.getDescription());
    assertNotNull(
            playlist.getExternalUrls());
    assertNotNull(
            playlist.getFollowers());
    assertEquals(
            "https://api.spotify.com/v1/users/thelinmichael/playlists/7d2D2S200NyUE5KYs80PwO",
            playlist.getHref());
    assertEquals(
            "7d2D2S200NyUE5KYs80PwO",
            playlist.getId());
    assertEquals(
            0,
            playlist.getImages().length);
    assertEquals(
            "A New Playlist",
            playlist.getName());
    assertNotNull(
            playlist.getOwner());
    assertFalse(
            playlist.getIsPublicAccess());
    assertEquals(
            "s0o3TSuYnRLl2jch+oA4OEbKwq/fNxhGBkSPnvhZdmWjNV0q3uCAWuGIhEx8SHIx",
            playlist.getSnapshotId());
    assertNotNull(
            playlist.getTracks());
    assertEquals(
            ModelObjectType.PLAYLIST,
            playlist.getType());
    assertEquals(
            "spotify:user:thelinmichael:playlist:7d2D2S200NyUE5KYs80PwO",
            playlist.getUri());
  }
}
