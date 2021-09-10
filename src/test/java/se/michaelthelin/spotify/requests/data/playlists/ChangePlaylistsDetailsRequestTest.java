package se.michaelthelin.spotify.requests.data.playlists;

import org.apache.hc.core5.http.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Assertions;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
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
    Assertions.assertHasHeader(defaultRequest, "Content-Type", "application/json");
    Assertions.assertHasBodyParameter(
      defaultRequest,
      "name",
      ITest.NAME);
    Assertions.assertHasBodyParameter(
      defaultRequest,
      "public",
      ITest.PUBLIC);
    Assertions.assertHasBodyParameter(
      defaultRequest,
      "collaborative",
      ITest.COLLABORATIVE);
    Assertions.assertHasBodyParameter(
      defaultRequest,
      "description",
      ITest.DESCRIPTION);
    Assert.assertEquals(
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
