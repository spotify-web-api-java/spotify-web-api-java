package com.wrapper.spotify.requests.data.personalization.simplified;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersTopTracksRequestTest extends AbstractDataTest<Paging<Track>> {
  private final GetUsersTopTracksRequest defaultRequest = SPOTIFY_API.getUsersTopTracks()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/personalization/simplified/GetUsersTopTracksRequest.json"))
          .limit(LIMIT)
          .offset(OFFSET)
          .time_range(TIME_RANGE)
          .build();

  public GetUsersTopTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/me/top/tracks?limit=10&offset=0&time_range=medium_term",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<Track>) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<Track> trackPaging) {
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
