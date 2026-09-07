package se.michaelthelin.spotify.requests.data.tracks;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("deprecation")
public class SaveTracksForCurrentUserRequestTest extends AbstractDataTest<String> {
  private final SaveTracksForCurrentUserRequest defaultRequest = ITest.SPOTIFY_API
    .saveTracksForCurrentUser(ITest.ID_TRACK)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .build();

  public SaveTracksForCurrentUserRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/tracks?ids=01iyCAUm8EvOFqVWYJ3dVX",
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

  public void shouldReturnDefault(final String string) {
    assertNull(string);
  }
}
