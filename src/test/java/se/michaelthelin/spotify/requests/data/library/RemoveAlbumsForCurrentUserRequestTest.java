package se.michaelthelin.spotify.requests.data.library;

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
public class RemoveAlbumsForCurrentUserRequestTest extends AbstractDataTest<String> {
  private final RemoveAlbumsForCurrentUserRequest defaultRequest = ITest.SPOTIFY_API
    .removeAlbumsForCurrentUser(ITest.ID_ALBUM, ITest.ID_ALBUM)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .build();
  private final RemoveAlbumsForCurrentUserRequest bodyRequest = ITest.SPOTIFY_API
    .removeAlbumsForCurrentUser(ITest.ALBUMS)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .build();

  public RemoveAlbumsForCurrentUserRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    Assert.assertEquals(
      "https://api.spotify.com:443/v1/me/albums?ids=5zT1JLIj9E57p3e1rFm9Uq%2C5zT1JLIj9E57p3e1rFm9Uq",
      defaultRequest.getUri().toString());

    assertHasAuthorizationHeader(bodyRequest);
    Assertions.assertHasHeader(defaultRequest, "Content-Type", "application/json");
    Assertions.assertHasBodyParameter(bodyRequest,
      "ids",
      ITest.ALBUMS);
    Assert.assertEquals("https://api.spotify.com:443/v1/me/albums",
      bodyRequest.getUri().toString());
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
