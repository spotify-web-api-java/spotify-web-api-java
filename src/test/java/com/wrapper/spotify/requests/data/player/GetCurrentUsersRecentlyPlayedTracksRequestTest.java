package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import com.wrapper.spotify.model_objects.specification.PlayHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetCurrentUsersRecentlyPlayedTracksRequestTest implements ITest<PagingCursorbased<PlayHistory>> {
  private final GetCurrentUsersRecentlyPlayedTracksRequest successRequest = SPOTIFY_API
          .getCurrentUsersRecentlyPlayedTracks()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetCurrentUsersRecentlyPlayedTracksRequest.json"))
          .build();

  public GetCurrentUsersRecentlyPlayedTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((PagingCursorbased<PlayHistory>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final PagingCursorbased<PlayHistory> playHistoryPagingCursorbased) {
    assertEquals(
            10,
            playHistoryPagingCursorbased.getItems().length);
    assertEquals(
            "https://api.spotify.com/v1/me/player/recently-played?after=1515558610865&limit=10&type=track",
            playHistoryPagingCursorbased.getNext());
    assertEquals(
            1,
            playHistoryPagingCursorbased.getCursors().length);
    assertEquals(
            10,
            (int) playHistoryPagingCursorbased.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/me/player/recently-played?after=1484811043508&limit=10&type=track",
            playHistoryPagingCursorbased.getHref());
  }
}
