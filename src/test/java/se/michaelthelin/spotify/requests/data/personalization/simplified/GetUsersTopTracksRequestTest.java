package se.michaelthelin.spotify.requests.data.personalization.simplified;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetUsersTopTracksRequestTest extends AbstractDataTest<Paging<Track>> {
  private final GetUsersTopTracksRequest defaultRequest = ITest.SPOTIFY_API.getUsersTopTracks()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/personalization/simplified/GetUsersTopTracksRequest.json"))
    .limit(ITest.LIMIT)
    .offset(ITest.OFFSET)
    .time_range(ITest.TIME_RANGE)
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
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
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
