package com.wrapper.spotify.requests.data.follow;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CheckUsersFollowPlaylistRequestTest implements ITest<Boolean[]> {
  private final CheckUsersFollowPlaylistRequest successRequest = SPOTIFY_API
          .checkUsersFollowPlaylist("owner_id", "playlist_id", new String[]{"id"})
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/CheckUsersFollowPlaylistRequest.json"))
          .build();

  public CheckUsersFollowPlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Boolean[]) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Boolean[] booleans) {
    assertEquals(
            2,
            booleans.length);
  }
}
