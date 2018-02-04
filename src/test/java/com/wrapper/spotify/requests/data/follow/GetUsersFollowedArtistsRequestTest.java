package com.wrapper.spotify.requests.data.follow;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersFollowedArtistsRequestTest extends AbstractDataTest<PagingCursorbased<Artist>> {
  private final GetUsersFollowedArtistsRequest defaultRequest = SPOTIFY_API.getUsersFollowedArtists(TYPE)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/GetUsersFollowedArtistsRequest.json"))
          .after(ID_ARTIST)
          .limit(LIMIT)
          .build();

  private final GetUsersFollowedArtistsRequest emptyRequest = SPOTIFY_API.getUsersFollowedArtists(TYPE)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/GetUsersFollowedArtistsRequest_None.json"))
          .after(ID_ARTIST)
          .limit(LIMIT)
          .build();

  public GetUsersFollowedArtistsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/me/following?type=ARTIST&after=0LcJLqbBmaGUft1e9Mm8HV&limit=10",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((PagingCursorbased<Artist>) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final PagingCursorbased<Artist> artistPagingCursorbased) {
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
  public void shouldReturnEmpty_sync() throws IOException, SpotifyWebApiException {
    shouldReturnEmpty(emptyRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnEmpty_async() throws ExecutionException, InterruptedException {
    shouldReturnEmpty((PagingCursorbased<Artist>) emptyRequest.executeAsync().get());
  }

  public void shouldReturnEmpty(final PagingCursorbased<Artist> artistPagingCursorbased) {
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
