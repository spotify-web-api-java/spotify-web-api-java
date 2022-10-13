package se.michaelthelin.spotify.requests.data.users_profile;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetUsersProfileRequestTest extends AbstractDataTest<User> {
  private final GetUsersProfileRequest defaultRequest = ITest.SPOTIFY_API
    .getUsersProfile(ITest.ID_USER_NON_ASCII)
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
