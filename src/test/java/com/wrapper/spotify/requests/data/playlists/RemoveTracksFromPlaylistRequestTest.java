package com.wrapper.spotify.requests.data.playlists;

import com.google.gson.JsonParser;
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
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RemoveTracksFromPlaylistRequestTest extends AbstractDataTest<SnapshotResult> {
  private final RemoveTracksFromPlaylistRequest defaultRequest = SPOTIFY_API
          .removeTracksFromPlaylist(ID_PLAYLIST, new JsonParser()
                  .parse("[{\"uri\":\"" + ID_TRACK + "\"},{\"uri\":\"" + ID_TRACK + "\"}]").getAsJsonArray())
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/RemoveTracksFromPlaylistRequest.json"))
          .snapshotId(SNAPSHOT_ID)
          .build();

  public RemoveTracksFromPlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasBodyParameter(
            defaultRequest,
            "tracks",
            "[{\"uri\":\"" + ID_TRACK + "\"},{\"uri\":\"" + ID_TRACK + "\"}]");
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
            "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+",
            snapshotResult.getSnapshotId());
  }
}
