package se.michaelthelin.spotify.requests.data.playlists;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static se.michaelthelin.spotify.Assertions.*;

public class CreatePlaylistRequestTest extends AbstractDataTest<Playlist> {
  private final CreatePlaylistRequest defaultRequest = ITest.SPOTIFY_API
    .createPlaylist(ITest.ID_USER, ITest.NAME)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/CreatePlaylistRequest.json"))
    .collaborative(ITest.COLLABORATIVE)
    .description(ITest.DESCRIPTION)
    .public_(ITest.PUBLIC)
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
      ITest.NAME);
    assertHasBodyParameter(
      defaultRequest,
      "public",
      ITest.PUBLIC);
    assertHasBodyParameter(
      defaultRequest,
      "collaborative",
      ITest.COLLABORATIVE);
    assertHasBodyParameter(
      defaultRequest,
      "description",
      ITest.DESCRIPTION);
    assertEquals(
      "https://api.spotify.com:443/v1/users/abbaspotify/playlists",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
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
