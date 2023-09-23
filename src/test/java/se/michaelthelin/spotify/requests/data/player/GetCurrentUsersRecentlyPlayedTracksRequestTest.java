package se.michaelthelin.spotify.requests.data.player;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;
import se.michaelthelin.spotify.model_objects.specification.PlayHistory;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetCurrentUsersRecentlyPlayedTracksRequestTest extends AbstractDataTest<PagingCursorbased<PlayHistory>> {
  private final GetCurrentUsersRecentlyPlayedTracksRequest defaultRequest = ITest.SPOTIFY_API
    .getCurrentUsersRecentlyPlayedTracks()
    .after(ITest.AFTER)
    .before(ITest.BEFORE)
    .limit(ITest.LIMIT)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/player/GetCurrentUsersRecentlyPlayedTracksRequest.json"))
    .build();

  public GetCurrentUsersRecentlyPlayedTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/player/recently-played?after=2018-01-27T21%3A07%3A10&before=2016-01-27T22%3A07%3A00&limit=10",
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

  public void shouldReturnDefault(final PagingCursorbased<PlayHistory> playHistoryPagingCursorbased) {
    assertEquals(
      "https://api.spotify.com/v1/me/player/recently-played?limit=2",
      playHistoryPagingCursorbased.getHref());
    assertEquals(
      2,
      playHistoryPagingCursorbased.getItems().length);
    assertEquals(
      2,
      (int) playHistoryPagingCursorbased.getLimit());
    assertEquals(
      "https://api.spotify.com/v1/me/player/recently-played?before=1695396194294&limit=2",
      playHistoryPagingCursorbased.getNext());
    assertEquals(
      1,
      playHistoryPagingCursorbased.getCursors().length);
    assertNull(
      playHistoryPagingCursorbased.getTotal());
  }
}
