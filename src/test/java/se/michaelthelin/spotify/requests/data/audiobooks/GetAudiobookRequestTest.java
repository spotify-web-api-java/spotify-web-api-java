package se.michaelthelin.spotify.requests.data.audiobooks;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Audiobook;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetAudiobookRequestTest extends AbstractDataTest<Audiobook> {
  private final GetAudiobookRequest defaultRequest = ITest.SPOTIFY_API
    .getAudiobook(ITest.ID_AUDIOBOOK)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/audiobooks/GetAudiobookRequest.json"))
    .build();

  public GetAudiobookRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/audiobooks/7isl4tFkclqCygPqGxX8FV",
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

  public void shouldReturnDefault(final Audiobook audiobook) {
    assertNotNull(audiobook);
    assertEquals("7isl4tFkclqCygPqGxX8FV", audiobook.getId());
    assertEquals("Moby-Dick", audiobook.getName());
    assertNotNull(audiobook.getAuthors());
    assertEquals(1, audiobook.getAuthors().length);
    assertEquals("Herman Melville", audiobook.getAuthors()[0].getName());
  }
}
