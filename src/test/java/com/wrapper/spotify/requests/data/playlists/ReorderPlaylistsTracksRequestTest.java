package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.wrapper.spotify.Assertions.assertHasBodyParameter;
import static com.wrapper.spotify.Assertions.assertHasHeader;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReorderPlaylistsTracksRequestTest extends AbstractDataTest<SnapshotResult> {
  private final ReorderPlaylistsTracksRequest defaultRequest = SPOTIFY_API
          .reorderPlaylistsTracks(ID_PLAYLIST, RANGE_START, INSERT_BEFORE)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/ReorderPlaylistsTracksRequest.json"))
          .range_length(RANGE_LENGTH)
          .snapshot_id(SNAPSHOT_ID)
          .build();

  public ReorderPlaylistsTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
            defaultRequest,
            "range_start",
            RANGE_START);
    assertHasBodyParameter(
            defaultRequest,
            "range_length",
            RANGE_LENGTH);
    assertHasBodyParameter(
            defaultRequest,
            "insert_before",
            INSERT_BEFORE);
    assertHasBodyParameter(
            defaultRequest,
            "snapshot_id",
            SNAPSHOT_ID);
    assertEquals(
            "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((SnapshotResult) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final SnapshotResult snapshotResult) {
    assertEquals(
            "KsWY41k+zLqbx7goYX9zr+2IUZQtqbBNfk4ZOgEpIurvab4VSHhEL2L4za8HW6D0",
            snapshotResult.getSnapshotId());
  }
}
