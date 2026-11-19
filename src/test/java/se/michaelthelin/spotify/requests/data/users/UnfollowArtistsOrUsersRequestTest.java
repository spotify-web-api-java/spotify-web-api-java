package se.michaelthelin.spotify.requests.data.users;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static se.michaelthelin.spotify.Assertions.assertHasBodyParameter;

@SuppressWarnings("deprecation")
public class UnfollowArtistsOrUsersRequestTest extends AbstractDataTest<String> {
  private final UnfollowArtistsOrUsersRequest defaultRequest = SPOTIFY_API
    .unfollowArtistsOrUsers(ModelObjectType.ARTIST, Arrays.asList(ID_ARTIST, ID_ARTIST))
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .build();

  public UnfollowArtistsOrUsersRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/following?type=ARTIST",
      defaultRequest.getUri().toString());
    assertHasBodyParameter(defaultRequest, "ids", Arrays.asList(ID_ARTIST, ID_ARTIST));
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final String string) {
    assertNull(
      string);
  }
}
