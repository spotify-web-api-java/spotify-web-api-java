package se.michaelthelin.spotify.requests.data.personalization.simplified;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetUsersTopArtistsRequestTest extends AbstractDataTest<Paging<Artist>> {
  private final GetUsersTopArtistsRequest defaultRequest = ITest.SPOTIFY_API.getUsersTopArtists()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/personalization/simplified/GetUsersTopArtistsRequest.json"))
    .limit(ITest.LIMIT)
    .offset(ITest.OFFSET)
    .time_range(ITest.TIME_RANGE)
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
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
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
