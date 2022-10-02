package se.michaelthelin.spotify.requests.data.follow;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

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
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
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
  public void shouldReturnEmpty_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnEmpty(emptyRequest.execute());
  }

  @Test
  public void shouldReturnEmpty_async() throws ExecutionException, InterruptedException {
    shouldReturnEmpty(emptyRequest.executeAsync().get());
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
