package se.michaelthelin.spotify.requests.data.audiobooks;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ChapterSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetAudiobookChaptersRequestTest extends AbstractDataTest<Paging<ChapterSimplified>> {
  private final GetAudiobookChaptersRequest defaultRequest = ITest.SPOTIFY_API
    .getAudiobookChapters(ITest.ID_AUDIOBOOK)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/audiobooks/GetAudiobookChaptersRequest.json"))
    .limit(ITest.LIMIT)
    .offset(ITest.OFFSET)
    .build();

  public GetAudiobookChaptersRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/audiobooks/7isl4tFkclqCygPqGxX8FV/chapters?limit=10&offset=0",
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

  public void shouldReturnDefault(final Paging<ChapterSimplified> chapterPaging) {
    assertNotNull(chapterPaging);
    assertEquals(
      "https://api.spotify.com/v1/audiobooks/7isl4tFkclqCygPqGxX8FV/chapters?offset=0&limit=20",
      chapterPaging.getHref());
    assertEquals(1, chapterPaging.getItems().length);
    assertEquals(20, (int) chapterPaging.getLimit());
    assertNull(chapterPaging.getNext());
    assertEquals(0, (int) chapterPaging.getOffset());
    assertNull(chapterPaging.getPrevious());
    assertEquals(135, (int) chapterPaging.getTotal());
  }
}
