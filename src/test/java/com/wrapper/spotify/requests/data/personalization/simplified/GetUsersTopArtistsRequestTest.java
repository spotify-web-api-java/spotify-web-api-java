package com.wrapper.spotify.requests.data.personalization.simplified;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersTopArtistsRequestTest extends AbstractDataTest<Paging<Artist>> {
  private final GetUsersTopArtistsRequest defaultRequest = SPOTIFY_API.getUsersTopArtists()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/personalization/simplified/GetUsersTopArtistsRequest.json"))
          .limit(LIMIT)
          .offset(OFFSET)
          .time_range(TIME_RANGE)
          .build();

  public GetUsersTopArtistsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/me/top/artists?limit=10&offset=0&time_range=medium_term",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<Artist>) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<Artist> artistPaging) {
    assertEquals(
            "https://api.spotify.com/v1/me/top/artists?limit=10&offset=5",
            artistPaging.getHref());
    assertEquals(
            10,
            artistPaging.getItems().length);
    assertEquals(
            10,
            (int) artistPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/me/top/artists?limit=10&offset=15",
            artistPaging.getNext());
    assertEquals(
            5,
            (int) artistPaging.getOffset());
    assertNull(
            artistPaging.getPrevious());
    assertEquals(
            50,
            (int) artistPaging.getTotal());
  }
}
