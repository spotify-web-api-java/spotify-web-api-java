package se.michaelthelin.spotify.requests.data.library;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.SavedTrack;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetUsersSavedTracksRequestTest extends AbstractDataTest<Paging<SavedTrack>> {
  private final GetUsersSavedTracksRequest defaultRequest = ITest.SPOTIFY_API.getUsersSavedTracks()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/library/GetUsersSavedTracksRequest.json"))
    .limit(ITest.LIMIT)
    .market(ITest.MARKET)
    .offset(ITest.OFFSET)
    .build();

  public GetUsersSavedTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/tracks?limit=10&market=SE&offset=0",
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

  public void shouldReturnDefault(final Paging<SavedTrack> savedTrackPaging) {
    assertEquals(
      "https://api.spotify.com/v1/me/tracks?offset=0&limit=20",
      savedTrackPaging.getHref());
    assertEquals(
      1,
      savedTrackPaging.getItems().length);
    assertEquals(
      20,
      (int) savedTrackPaging.getLimit());
    assertEquals(
      "https://api.spotify.com/v1/me/tracks?offset=20&limit=20",
      savedTrackPaging.getNext());
    assertEquals(
      0,
      (int) savedTrackPaging.getOffset());
    assertNull(
      savedTrackPaging.getPrevious());
    assertEquals(
      53,
      (int) savedTrackPaging.getTotal());
  }
}
