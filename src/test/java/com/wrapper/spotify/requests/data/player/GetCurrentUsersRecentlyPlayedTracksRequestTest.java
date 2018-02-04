package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import com.wrapper.spotify.model_objects.specification.PlayHistory;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetCurrentUsersRecentlyPlayedTracksRequestTest extends AbstractDataTest<PagingCursorbased<PlayHistory>> {
  private final GetCurrentUsersRecentlyPlayedTracksRequest defaultRequest = SPOTIFY_API
          .getCurrentUsersRecentlyPlayedTracks()
          .after(AFTER)
          .before(BEFORE)
          .limit(LIMIT)
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
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((PagingCursorbased<PlayHistory>) defaultRequest.executeAsync().get());
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
            "https://api.spotify.com/v1/me/player/recently-played?before=1481661737016&limit=2",
            playHistoryPagingCursorbased.getNext());
    assertEquals(
            1,
            playHistoryPagingCursorbased.getCursors().length);
    assertNull(
            playHistoryPagingCursorbased.getTotal());
  }
}
