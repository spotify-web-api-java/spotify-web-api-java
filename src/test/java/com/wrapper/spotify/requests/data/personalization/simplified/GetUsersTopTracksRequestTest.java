package com.wrapper.spotify.requests.data.personalization.simplified;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersTopTracksRequestTest implements ITest<Paging<Track>> {
  private final GetUsersTopTracksRequest successRequest = SPOTIFY_API
          .getUsersTopTracks()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/personalization/simplified/GetUsersTopTracksRequest.json"))
          .build();

  public GetUsersTopTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Paging<Track>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Paging<Track> trackPaging) {
    assertEquals(
            "https://api.spotify.com/v1/me/top/tracks?limit=10&offset=5",
            trackPaging.getHref());
    assertEquals(
            10,
            trackPaging.getItems().length);
    assertEquals(
            10,
            (int) trackPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/me/top/tracks?limit=10&offset=15",
            trackPaging.getNext());
    assertEquals(
            5,
            (int) trackPaging.getOffset());
    assertNull(
            trackPaging.getPrevious());
    assertEquals(
            50,
            (int) trackPaging.getTotal());
  }
}
