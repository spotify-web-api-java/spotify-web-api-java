package com.wrapper.spotify.requests.data.playlists;

import com.google.gson.Gson;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.apache.hc.core5.http.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.wrapper.spotify.Assertions.assertHasBodyParameter;
import static com.wrapper.spotify.Assertions.assertHasHeader;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AddItemsToPlaylistRequestTest extends AbstractDataTest<SnapshotResult> {
  private final AddItemsToPlaylistRequest defaultRequest = SPOTIFY_API
    .addItemsToPlaylist(ID_PLAYLIST, new Gson().fromJson(URIS, String[].class))
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/AddItemsToPlaylistRequest.json"))
    .position(POSITION)
    .build();
  private final AddItemsToPlaylistRequest bodyRequest = SPOTIFY_API
    .addItemsToPlaylist(ID_PLAYLIST, URIS)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/AddItemsToPlaylistRequest.json"))
    .position(POSITION, true)
    .build();

  public AddItemsToPlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks?uris=spotify%3Atrack%3A01iyCAUm8EvOFqVWYJ3dVX%2Cspotify%3Atrack%3A01iyCAUm8EvOFqVWYJ3dVX&position=0",
      defaultRequest.getUri().toString());

    assertHasAuthorizationHeader(bodyRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
      bodyRequest,
      "uris",
      URIS);
    assertHasBodyParameter(
      bodyRequest,
      "position",
      POSITION);
    assertEquals(
      "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks",
      bodyRequest.getUri().toString());
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
