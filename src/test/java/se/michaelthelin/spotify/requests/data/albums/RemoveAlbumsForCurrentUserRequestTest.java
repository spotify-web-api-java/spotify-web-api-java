package se.michaelthelin.spotify.requests.data.albums;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static se.michaelthelin.spotify.Assertions.assertHasBodyParameter;

@SuppressWarnings("deprecation")
public class RemoveAlbumsForCurrentUserRequestTest extends AbstractDataTest<String> {
  private final RemoveAlbumsForCurrentUserRequest defaultRequest = ITest.SPOTIFY_API
    .removeAlbumsForCurrentUser(Collections.singletonList(ITest.ID_ALBUM))
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .build();

  public RemoveAlbumsForCurrentUserRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/albums",
      defaultRequest.getUri().toString());
    assertHasBodyParameter(defaultRequest, "ids", Collections.singletonList(ITest.ID_ALBUM));
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
