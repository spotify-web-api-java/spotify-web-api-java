package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetPlaylistRequestTest extends AbstractDataTest<Playlist> {
  private final GetPlaylistRequest defaultRequest = SPOTIFY_API
          .getPlaylist(ID_PLAYLIST)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/GetPlaylistRequest.json"))
          .fields(FIELDS)
          .market(MARKET)
          .build();

  public GetPlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5?fields=description&market=SE",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Playlist) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Playlist playlist) {
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
