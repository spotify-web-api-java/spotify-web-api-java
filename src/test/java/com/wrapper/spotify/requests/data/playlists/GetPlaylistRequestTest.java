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
public class GetPlaylistRequestTest implements ITest<Playlist> {
  private final GetPlaylistRequest successRequest = SPOTIFY_API
          .getPlaylist("user_id", "playlist_id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/GetPlaylistRequest.json"))
          .build();

  public GetPlaylistRequestTest() throws Exception {
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
    assertEquals(
            "Having friends over for dinner? Here´s the perfect playlist.",
            playlist.getDescription());
    assertNotNull(
            playlist.getExternalUrls());
    assertNotNull(
            playlist.getFollowers());
    assertEquals(
            "https://api.spotify.com/v1/users/spotify/playlists/59ZbFPES4DQwEjBpWHzrtC",
            playlist.getHref());
    assertEquals(
            "59ZbFPES4DQwEjBpWHzrtC",
            playlist.getId());
    assertEquals(
            1,
            playlist.getImages().length);
    assertEquals(
            "Dinner with Friends",
            playlist.getName());
    assertNotNull(
            playlist.getOwner());
    assertNull(
            playlist.getIsPublicAccess());
    assertEquals(
            "bNLWdmhh+HDsbHzhckXeDC0uyKyg4FjPI/KEsKjAE526usnz2LxwgyBoMShVL+z+",
            playlist.getSnapshotId());
    assertNotNull(
            playlist.getTracks());
    assertEquals(
            ModelObjectType.PLAYLIST,
            playlist.getType());
    assertEquals(
            "spotify:user:spotify:playlist:59ZbFPES4DQwEjBpWHzrtC",
            playlist.getUri());
  }
}
