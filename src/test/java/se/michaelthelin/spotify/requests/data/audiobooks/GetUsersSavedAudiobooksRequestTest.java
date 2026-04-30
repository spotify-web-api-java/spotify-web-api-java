package se.michaelthelin.spotify.requests.data.audiobooks;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.SavedAudiobook;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetUsersSavedAudiobooksRequestTest extends AbstractDataTest<Paging<SavedAudiobook>> {
  private final GetUsersSavedAudiobooksRequest defaultRequest = ITest.SPOTIFY_API
    .getUsersSavedAudiobooks()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/audiobooks/GetUsersSavedAudiobooksRequest.json"))
    .limit(ITest.LIMIT)
    .offset(ITest.OFFSET)
    .build();

  public GetUsersSavedAudiobooksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/audiobooks?limit=10&offset=0",
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

  public void shouldReturnDefault(final Paging<SavedAudiobook> savedAudiobookPaging) {
    assertNotNull(savedAudiobookPaging);
    assertEquals(
      "https://api.spotify.com/v1/me/audiobooks?offset=0&limit=20",
      savedAudiobookPaging.getHref());
    assertEquals(1, savedAudiobookPaging.getItems().length);
    assertEquals(20, (int) savedAudiobookPaging.getLimit());
    assertNull(savedAudiobookPaging.getNext());
    assertEquals(0, (int) savedAudiobookPaging.getOffset());
    assertNull(savedAudiobookPaging.getPrevious());
    assertEquals(1, (int) savedAudiobookPaging.getTotal());
  }
}
