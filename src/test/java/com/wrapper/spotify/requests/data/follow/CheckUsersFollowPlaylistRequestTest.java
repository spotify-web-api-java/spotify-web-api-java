package com.wrapper.spotify.requests.data.follow;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CheckUsersFollowPlaylistRequestTest extends AbstractDataTest<Boolean[]> {
  private final CheckUsersFollowPlaylistRequest defaultRequest = SPOTIFY_API
          .checkUsersFollowPlaylist(ID_USER, ID_PLAYLIST, new String[]{ID_USER, ID_USER})
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/CheckUsersFollowPlaylistRequest.json"))
          .build();

  public CheckUsersFollowPlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists/3AGOiaoRXMSjswCLtuNqv5/followers/contains?ids=abbaspotify%2Cabbaspotify",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Boolean[]) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Boolean[] booleans) {
    assertEquals(
            2,
            booleans.length);
  }
}
