package com.wrapper.spotify.requests.data.users_profile;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.apache.hc.core5.http.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersProfileRequestTest extends AbstractDataTest<User> {
  private final GetUsersProfileRequest defaultRequest = SPOTIFY_API
    .getUsersProfile(ID_USER_NON_ASCII)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/users_profile/GetUsersProfileRequest.json"))
    .build();

  public GetUsersProfileRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      // Yes, ABBA is not spelled that way ;)
      // But it should be ensured non-ASCII characters are handled properly as well.
      // Therefore, "abbaspötify" is used instead of "abbaspotify".
      // "abbaspötify" should become abbasp%C3%B6tify after URL encoding.
      // These characters do exist, for example there are plenty of them in the German speaking area,
      // they are used in profile names and Spotify permits them.
      // Additionally, the URL encoding is not only applied to non-ASCII characters as the set of characters allowed to be used in an URL
      // is just a subset of ASCII.
      // Only used ids and category ids are prone to this as the other ids used by spotify are Base62 encoded and
      // therefore do not contain characters requiring to be encoded via Percent/URL encoding.
      "https://api.spotify.com:443/v1/users/abbasp%C3%B6tify",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
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
