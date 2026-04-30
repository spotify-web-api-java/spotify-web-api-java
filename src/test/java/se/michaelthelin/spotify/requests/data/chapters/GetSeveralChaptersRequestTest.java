package se.michaelthelin.spotify.requests.data.chapters;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Chapter;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetSeveralChaptersRequestTest extends AbstractDataTest<Chapter[]> {
  private final GetSeveralChaptersRequest defaultRequest = ITest.SPOTIFY_API
    .getSeveralChapters(ITest.ID_CHAPTER)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/chapters/GetSeveralChaptersRequest.json"))
    .build();

  public GetSeveralChaptersRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/chapters?ids=2kFJSKFOkDUEXFOh7WFJUv",
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

  public void shouldReturnDefault(final Chapter[] chapters) {
    assertNotNull(chapters);
    assertEquals(1, chapters.length);
    assertEquals("2kFJSKFOkDUEXFOh7WFJUv", chapters[0].getId());
  }
}
