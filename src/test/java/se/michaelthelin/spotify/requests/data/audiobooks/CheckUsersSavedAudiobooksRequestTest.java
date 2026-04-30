package se.michaelthelin.spotify.requests.data.audiobooks;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("deprecation")
public class CheckUsersSavedAudiobooksRequestTest extends AbstractDataTest<Boolean[]> {
  private final CheckUsersSavedAudiobooksRequest defaultRequest = ITest.SPOTIFY_API
    .checkUsersSavedAudiobooks(ITest.ID_AUDIOBOOK)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/audiobooks/CheckUsersSavedAudiobooksRequest.json"))
    .build();

  public CheckUsersSavedAudiobooksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/audiobooks/contains?ids=7isl4tFkclqCygPqGxX8FV",
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

  public void shouldReturnDefault(final Boolean[] booleans) {
    assertEquals(2, booleans.length);
  }
}
