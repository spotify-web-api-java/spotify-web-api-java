package se.michaelthelin.spotify.requests.data.users;

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
    .getUsersProfile(ITest.ID_USER)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/users/GetUsersProfileRequest.json"))
    .build();

  public GetUsersProfileRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/users/abbaspotify",
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
      "JM Wizzler",
      user.getDisplayName());
    assertNotNull(
      user.getExternalUrls());
    assertEquals(
      "https://api.spotify.com/v1/users/wizzler",
      user.getHref());
    assertEquals(
      "wizzler",
      user.getId());
    assertEquals(
      ModelObjectType.USER,
      user.getType());
    assertEquals(
      "spotify:user:wizzler",
      user.getUri());
  }
}
