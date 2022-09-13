package se.michaelthelin.spotify.requests.data.playlists;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetListOfCurrentUsersPlaylistsRequestTest extends AbstractDataTest<Paging<PlaylistSimplified>> {
  private final GetListOfCurrentUsersPlaylistsRequest defaultRequest = ITest.SPOTIFY_API
    .getListOfCurrentUsersPlaylists()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/GetListOfCurrentUsersPlaylistsRequest.json"))
    .limit(ITest.LIMIT)
    .offset(ITest.OFFSET)
    .build();

  public GetListOfCurrentUsersPlaylistsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/playlists?limit=10&offset=0",
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

  public void shouldReturnDefault(final Paging<PlaylistSimplified> playlistSimplifiedPaging) {
    assertEquals(
      "https://api.spotify.com/v1/users/wizzler/playlists",
      playlistSimplifiedPaging.getHref());
    assertEquals(
      2,
      playlistSimplifiedPaging.getItems().length);
    assertEquals(
      9,
      (int) playlistSimplifiedPaging.getLimit());
    assertNull(
      playlistSimplifiedPaging.getNext());
    assertEquals(
      0,
      (int) playlistSimplifiedPaging.getOffset());
    assertNull(
      playlistSimplifiedPaging.getPrevious());
    assertEquals(
      9,
      (int) playlistSimplifiedPaging.getTotal());
  }
}
