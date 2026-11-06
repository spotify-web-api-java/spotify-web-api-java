package se.michaelthelin.spotify.requests.data.playlists;

import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static se.michaelthelin.spotify.Assertions.assertHasBodyParameter;
import static se.michaelthelin.spotify.Assertions.assertHasHeader;

public class AddItemsToPlaylistRequestTest extends AbstractDataTest<SnapshotResult> {
  private final AddItemsToPlaylistRequest defaultRequest = ITest.SPOTIFY_API
    .addItemsToPlaylist(ITest.ID_PLAYLIST)
    .uris(ITest.URIS)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/AddItemsToPlaylistRequest.json"))
    .position(ITest.POSITION)
    .build();

  public AddItemsToPlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/items",
      defaultRequest.getUri().toString());
    assertHasBodyParameter(defaultRequest, "uris", ITest.URIS);
    assertHasBodyParameter(defaultRequest, "position", ITest.POSITION);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
  }

  @Test
  public void shouldSendUrisAndPositionInBody() throws IOException, ParseException {
    defaultRequest.initializeBody();
    assertEquals(
      "{\"uris\":[\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\",\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"],\"position\":0}",
      EntityUtils.toString(defaultRequest.getBody()));
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final SnapshotResult snapshotResult) {
    assertEquals(
      "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+",
      snapshotResult.getSnapshotId());
  }
}
