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

public class GetChapterRequestTest extends AbstractDataTest<Chapter> {
  private final GetChapterRequest defaultRequest = ITest.SPOTIFY_API
    .getChapter(ITest.ID_CHAPTER)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/chapters/GetChapterRequest.json"))
    .build();

  public GetChapterRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/chapters/2kFJSKFOkDUEXFOh7WFJUv",
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

  public void shouldReturnDefault(final Chapter chapter) {
    assertNotNull(chapter);
    assertEquals("2kFJSKFOkDUEXFOh7WFJUv", chapter.getId());
    assertEquals("Chapter 1: Loomings.", chapter.getName());
    assertNotNull(chapter.getAudiobook());
    assertEquals("7isl4tFkclqCygPqGxX8FV", chapter.getAudiobook().getId());
  }
}
