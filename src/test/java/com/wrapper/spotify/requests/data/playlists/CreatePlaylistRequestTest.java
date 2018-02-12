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

import static com.wrapper.spotify.Assertions.assertHasBodyParameter;
import static com.wrapper.spotify.Assertions.assertHasHeader;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreatePlaylistRequestTest extends AbstractDataTest<Playlist> {
  private final CreatePlaylistRequest defaultRequest = SPOTIFY_API
          .createPlaylist(ID_USER, NAME)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/CreatePlaylistRequest.json"))
          .collaborative(COLLABORATIVE)
          .description(DESCRIPTION)
          .public_(PUBLIC)
          .build();

  public CreatePlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
            defaultRequest,
            "name",
            NAME);
    assertHasBodyParameter(
            defaultRequest,
            "public",
            PUBLIC);
    assertHasBodyParameter(
            defaultRequest,
            "collaborative",
            COLLABORATIVE);
    assertHasBodyParameter(
            defaultRequest,
            "description",
            DESCRIPTION);
    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists",
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
