package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import org.junit.Test;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckUsersSavedTracksRequestTest {

  @Test
  public void shouldCheckContains_Async() throws Exception {
    final String accessToken = "someAccessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    CheckUsersSavedTracksRequest request = api.checkUsersSavedTracks(
            "0udZHhCi7p1YzMlvI4fXoK", "1e1VmyiAuPyM4SHhySP1oU")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/library/CheckUsersSavedTracksRequest.json"))
            .build();

    final Future<Boolean[]> searchResultFuture = request.executeAsync();
    final Boolean[] result = searchResultFuture.get(1, TimeUnit.SECONDS);

    assertFalse(result[0]);
    assertTrue(result[1]);
  }

  @Test
  public void shouldCheckContains_sync() throws Exception {
    final String accessToken = "someAccessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    CheckUsersSavedTracksRequest request = api.checkUsersSavedTracks(
            "0udZHhCi7p1YzMlvI4fXoK", "1e1VmyiAuPyM4SHhySP1oU")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/library/CheckUsersSavedTracksRequest.json"))
            .build();

    Boolean[] response = request.execute();
    assertFalse(response[0]);
    assertTrue(response[1]);
  }

}
