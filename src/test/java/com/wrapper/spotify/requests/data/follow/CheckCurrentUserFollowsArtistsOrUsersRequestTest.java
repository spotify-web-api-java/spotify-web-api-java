package com.wrapper.spotify.requests.data.follow;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CheckCurrentUserFollowsArtistsOrUsersRequestTest extends AbstractDataTest<Boolean[]> {
  private final CheckCurrentUserFollowsArtistsOrUsersRequest defaultRequest = SPOTIFY_API
          .checkCurrentUserFollowsArtistsOrUsers(ModelObjectType.ARTIST, new String[]{ID_ARTIST, ID_ARTIST})
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/CheckCurrentUserFollowsArtistsOrUsersRequest.json"))
          .build();

  public CheckCurrentUserFollowsArtistsOrUsersRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/me/following/contains?type=ARTIST&ids=0LcJLqbBmaGUft1e9Mm8HV%2C0LcJLqbBmaGUft1e9Mm8HV",
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
