package com.wrapper.spotify.requests.data.library;

import com.google.gson.JsonParser;
import com.wrapper.spotify.TestUtil;
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
public class SaveAlbumsForCurrentUserRequestTest extends AbstractDataTest<String> {
  private final SaveAlbumsForCurrentUserRequest defaultRequest = SPOTIFY_API
          .saveAlbumsForCurrentUser(ID_ALBUM, ID_ALBUM)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/library/SaveAlbumsForCurrentUserRequest.json"))
          .build();
  private final SaveAlbumsForCurrentUserRequest bodyRequest = SPOTIFY_API
          .saveAlbumsForCurrentUser(new JsonParser()
                  .parse("[\"" + ID_ALBUM + "\",\"" + ID_ALBUM + "\"]").getAsJsonArray())
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/FollowArtistsOrUsersRequestTest.json"))
          .build();

  public SaveAlbumsForCurrentUserRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/me/albums?ids=5zT1JLIj9E57p3e1rFm9Uq%2C5zT1JLIj9E57p3e1rFm9Uq",
            defaultRequest.getUri().toString());

    assertHasAuthorizationHeader(bodyRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
            bodyRequest,
            "ids",
            "[\"" + ID_ALBUM + "\",\"" + ID_ALBUM + "\"]");
    assertEquals(
            "https://api.spotify.com:443/v1/me/albums",
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
