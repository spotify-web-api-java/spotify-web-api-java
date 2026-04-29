package se.michaelthelin.spotify.requests.data.playlists;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static se.michaelthelin.spotify.Assertions.assertHasBodyParameter;
import static se.michaelthelin.spotify.Assertions.assertHasHeader;

public class CreatePlaylistRequestTest extends AbstractDataTest<Playlist> {
  private final CreatePlaylistRequest defaultRequest = ITest.SPOTIFY_API
    .createPlaylist(ITest.NAME)
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
      "https://api.spotify.com:443/v1/me/playlists",
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
    assertEquals(
      "New playlist description",
      playlist.getDescription());
    assertNotNull(
      playlist.getExternalUrls());
    assertEquals(
      "https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n",
      playlist.getHref());
    assertEquals(
      "3cEYpjA9oz9GiPac4AsH4n",
      playlist.getId());
    assertEquals(
      "New Playlist",
      playlist.getName());
    assertNotNull(
      playlist.getOwner());
    assertFalse(
      playlist.getIsPublicAccess());
    assertEquals(
      "JbtmHBDBAkMzFjnFzSP0aeYCCMP1XSIY5VHZT_jUGrFTzNTa6tnPiSzeBMFIcH2",
      playlist.getSnapshotId());
    assertNotNull(
      playlist.getItems());
  }
}
