package se.michaelthelin.spotify.requests.data.playlists;

import org.apache.hc.core5.http.ParseException;
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

public class ReorderPlaylistsItemsRequestTest extends AbstractDataTest<SnapshotResult> {
  private final ReorderPlaylistsItemsRequest defaultRequest = ITest.SPOTIFY_API
    .reorderPlaylistsItems(ITest.ID_PLAYLIST, ITest.RANGE_START, ITest.INSERT_BEFORE)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/ReorderPlaylistsItemsRequest.json"))
    .range_length(ITest.RANGE_LENGTH)
    .snapshot_id(ITest.SNAPSHOT_ID)
    .build();

  public ReorderPlaylistsItemsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
      defaultRequest,
      "range_start",
      ITest.RANGE_START);
    assertHasBodyParameter(
      defaultRequest,
      "range_length",
      ITest.RANGE_LENGTH);
    assertHasBodyParameter(
      defaultRequest,
      "insert_before",
      ITest.INSERT_BEFORE);
    assertHasBodyParameter(
      defaultRequest,
      "snapshot_id",
      ITest.SNAPSHOT_ID);
    assertEquals(
      "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks",
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

  public void shouldReturnDefault(final SnapshotResult snapshotResult) {
    assertEquals(
      "KsWY41k+zLqbx7goYX9zr+2IUZQtqbBNfk4ZOgEpIurvab4VSHhEL2L4za8HW6D0",
      snapshotResult.getSnapshotId());
  }
}
