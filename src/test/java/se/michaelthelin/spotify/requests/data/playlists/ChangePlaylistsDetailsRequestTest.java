package se.michaelthelin.spotify.requests.data.playlists;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static se.michaelthelin.spotify.Assertions.assertHasBodyParameter;
import static se.michaelthelin.spotify.Assertions.assertHasHeader;

public class ChangePlaylistsDetailsRequestTest extends AbstractDataTest<String> {
  private final ChangePlaylistsDetailsRequest defaultRequest = ITest.SPOTIFY_API
    .changePlaylistsDetails(ITest.ID_PLAYLIST)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .collaborative(ITest.COLLABORATIVE)
    .description(ITest.DESCRIPTION)
    .name(ITest.NAME)
    .public_(ITest.PUBLIC)
    .build();

  public ChangePlaylistsDetailsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
      defaultRequest,
      "name",
      ITest.NAME);
    assertHasBodyParameter(
      defaultRequest,
      "public",
      ITest.PUBLIC);
    assertHasBodyParameter(
      defaultRequest,
      "collaborative",
      ITest.COLLABORATIVE);
    assertHasBodyParameter(
      defaultRequest,
      "description",
      ITest.DESCRIPTION);
    assertEquals(
      "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5",
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
    assertNull(
      string);
  }
}
