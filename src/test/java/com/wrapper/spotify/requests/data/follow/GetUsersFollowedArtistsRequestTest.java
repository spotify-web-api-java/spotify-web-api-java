package com.wrapper.spotify.requests.data.follow;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersFollowedArtistsRequestTest implements ITest<PagingCursorbased<Artist>> {
  private final GetUsersFollowedArtistsRequest successRequest = SPOTIFY_API.getUsersFollowedArtists(ModelObjectType.ARTIST)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/GetUsersFollowedArtistsRequest.json"))
          .build();

  private final GetUsersFollowedArtistsRequest failureRequest = SPOTIFY_API.getUsersFollowedArtists(ModelObjectType.ARTIST)
          .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/follow/GetUsersFollowedArtistsRequest_None.json"))
          .build();

  public GetUsersFollowedArtistsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((PagingCursorbased<Artist>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final PagingCursorbased<Artist> artistPagingCursorbased) {
    assertEquals(
            1,
            artistPagingCursorbased.getItems().length);
    assertNull(
            artistPagingCursorbased.getNext());
    assertEquals(
            0,
            (int) artistPagingCursorbased.getTotal());
    assertNotNull(
            artistPagingCursorbased.getCursors());
    assertEquals(
            10,
            (int) artistPagingCursorbased.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/me/following?type=artist&after=0I2XqVXqHScXjHhk6AYYRe&limit=10",
            artistPagingCursorbased.getHref());
  }

  @Test
  public void shouldFail() throws Exception {
    final PagingCursorbased<Artist> artistPagingCursorbased = failureRequest.execute();

    assertEquals(
            0,
            artistPagingCursorbased.getItems().length);
    assertNull(
            artistPagingCursorbased.getNext());
    assertEquals(
            0,
            (int) artistPagingCursorbased.getTotal());
    assertNotNull(
            artistPagingCursorbased.getCursors());
    assertEquals(
            10,
            (int) artistPagingCursorbased.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/me/following?type=artist&after=0I2XqVXqHScXjHhk6AYYRe&limit=10",
            artistPagingCursorbased.getHref());
  }
}
