package com.wrapper.spotify.requests.data.users_profile;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersProfileRequestTest implements ITest<User> {
  private final GetUsersProfileRequest defaultRequest = SPOTIFY_API
          .getUsersProfile("user_id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/users_profile/GetUsersProfileRequest.json"))
          .build();

  public GetUsersProfileRequestTest() throws Exception {
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((User) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final User user) {
    assertEquals(
            "Lilla Namo",
            user.getDisplayName());
    assertNotNull(
            user.getExternalUrls());
    assertNotNull(
            user.getFollowers());
    assertEquals(
            "https://api.spotify.com/v1/users/tuggareutangranser",
            user.getHref());
    assertEquals(
            "tuggareutangranser",
            user.getId());
    assertEquals(
            1,
            user.getImages().length);
    assertEquals(
            ModelObjectType.USER,
            user.getType());
    assertEquals(
            "spotify:user:tuggareutangranser",
            user.getUri());
  }
}
