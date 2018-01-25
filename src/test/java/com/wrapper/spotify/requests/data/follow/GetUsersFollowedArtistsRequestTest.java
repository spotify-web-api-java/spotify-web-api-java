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
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/GetUsersFollowedArtistsRequest_None.json"))
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
            "https://api.spotify.com/v1/users/thelinmichael/following?type=artist&limit=20",
            artistPagingCursorbased.getHref());
    assertEquals(
            1,
            artistPagingCursorbased.getItems().length);
    assertEquals(
            20,
            (int) artistPagingCursorbased.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/users/thelinmichael/following?type=artist&after=0aV6DOiouImYTqrR5YlIqx&limit=20",
            artistPagingCursorbased.getNext());
    assertNotNull(
            artistPagingCursorbased.getCursors());
    assertEquals(
            183,
            (int) artistPagingCursorbased.getTotal());
  }

  @Test
  public void shouldFail_sync() throws IOException, SpotifyWebApiException {
    shouldFail(failureRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldFail_async() throws ExecutionException, InterruptedException {
    shouldFail((PagingCursorbased<Artist>) failureRequest.executeAsync().get());
  }

  public void shouldFail(final PagingCursorbased<Artist> artistPagingCursorbased) {
    assertEquals(
            "https://api.spotify.com/v1/me/following?type=artist&limit=10",
            artistPagingCursorbased.getHref());
    assertEquals(
            0,
            artistPagingCursorbased.getItems().length);
    assertEquals(
            10,
            (int) artistPagingCursorbased.getLimit());
    assertNull(
            artistPagingCursorbased.getNext());
    assertNotNull(
            artistPagingCursorbased.getCursors());
    assertEquals(
            0,
            (int) artistPagingCursorbased.getTotal());
  }
}
