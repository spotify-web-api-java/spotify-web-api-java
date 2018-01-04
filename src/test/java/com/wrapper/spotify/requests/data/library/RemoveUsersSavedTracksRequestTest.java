package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class RemoveUsersSavedTracksRequestTest {

  @Test
  public void removeFromMySavedTracks_async() throws Exception {
    final String accessToken = "accessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken)
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    final String[] tracksToAdd = new String[]{"5xFF6wNcoRwx7N3cDTgVWP", "13zm8XhfM4RBtQpjdqY44e"};

    final RemoveUsersSavedTracksRequest request = api.removeUsersSavedTracks(tracksToAdd).build();

    final Future<String> requestFuture = request.executeAsync();
    final String string = requestFuture.get();

    assertEquals("", string);
  }

  @Test
  public void removeFromMySavedTracks_sync() throws Exception {
    final String accessToken = "accessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken)
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    final String[] tracksToAdd = new String[]{"5xFF6wNcoRwx7N3cDTgVWP", "13zm8XhfM4RBtQpjdqY44e"};

    final RemoveUsersSavedTracksRequest request = api.removeUsersSavedTracks(tracksToAdd).build();

    request.execute();
  }
}
