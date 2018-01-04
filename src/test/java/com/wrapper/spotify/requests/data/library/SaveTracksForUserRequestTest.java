package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class SaveTracksForUserRequestTest {

  @Test
  public void shouldAddToMySavedTracks_async() throws Exception {
    final String accessToken = "someAccessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final String[]
            tracksToAdd = {"4BYGxv4rxSNcTgT3DsFB9o", "0BG2iE6McPhmAEKIhfqy1X"};

    final SaveTracksForUserRequest request = api.saveTracksForUser(tracksToAdd)
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    final Future<String> requestFuture = request.executeAsync();
    final String string = requestFuture.get();

    assertEquals("", string);
  }

  @Test
  public void shouldAddToMySavedTracks_sync() throws Exception {
    final String accessToken = "someAccessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final String[]
            tracksToAdd = {"4BYGxv4rxSNcTgT3DsFB9o", "0BG2iE6McPhmAEKIhfqy1X"};

    final SaveTracksForUserRequest request = api.saveTracksForUser(tracksToAdd)
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    request.execute();
  }
}
