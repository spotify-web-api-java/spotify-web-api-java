package com.wrapper.spotify.requests.data.follow;

import com.google.gson.JsonParser;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.wrapper.spotify.Assertions.assertHasBodyParameter;
import static com.wrapper.spotify.Assertions.assertHasHeader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class FollowArtistsOrUsersRequestTest extends AbstractDataTest<String> {
  private final FollowArtistsOrUsersRequest defaultRequest = SPOTIFY_API
          .followArtistsOrUsers(ModelObjectType.ARTIST, new String[]{ID_ARTIST, ID_ARTIST})
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/FollowArtistsOrUsersRequestTest.json"))
          .build();
  private final FollowArtistsOrUsersRequest bodyRequest = SPOTIFY_API
          .followArtistsOrUsers(ModelObjectType.ARTIST, new JsonParser()
                  .parse("[\"" + ID_ARTIST + "\",\"" + ID_ARTIST + "\"]").getAsJsonArray())
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/FollowArtistsOrUsersRequestTest.json"))
          .build();

  public FollowArtistsOrUsersRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/me/following?type=ARTIST&ids=0LcJLqbBmaGUft1e9Mm8HV%2C0LcJLqbBmaGUft1e9Mm8HV",
            defaultRequest.getUri().toString());

    assertHasAuthorizationHeader(bodyRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
            bodyRequest,
            "ids",
            "[\"" + ID_ARTIST + "\",\"" + ID_ARTIST + "\"]");
    assertEquals(
            "https://api.spotify.com:443/v1/me/following?type=ARTIST",
            bodyRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((String) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final String string) {
    assertNull(
            string);
  }
}
