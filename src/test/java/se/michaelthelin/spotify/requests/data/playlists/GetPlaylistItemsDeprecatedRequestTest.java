package se.michaelthelin.spotify.requests.data.playlists;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @deprecated Use {@link GetPlaylistItemsRequestTest} instead.
 */
@Deprecated
public class GetPlaylistItemsDeprecatedRequestTest extends AbstractDataTest<Paging<PlaylistTrack>> {
  private final GetPlaylistItemsDeprecatedRequest defaultRequest = ITest.SPOTIFY_API
    .getPlaylistItemsDeprecated(ITest.ID_PLAYLIST)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/GetPlaylistsItemsRequest.json"))
    .fields(ITest.FIELDS)
    .limit(ITest.LIMIT)
    .offset(ITest.OFFSET)
    .market(ITest.MARKET)
    .additionalTypes(ITest.ADDITIONAL_TYPES)
    .build();

  public GetPlaylistItemsDeprecatedRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks?fields=description&limit=10&offset=0&market=SE&additional_types=track%2Cepisode",
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

  public void shouldReturnDefault(final Paging<PlaylistTrack> playlistTrackPaging) {
    assertNotNull(playlistTrackPaging);
    assertEquals(2, playlistTrackPaging.getItems().length);
    assertEquals(100, (int) playlistTrackPaging.getLimit());
    assertNull(playlistTrackPaging.getNext());
    assertEquals(0, (int) playlistTrackPaging.getOffset());
    assertNull(playlistTrackPaging.getPrevious());
    assertEquals(58, (int) playlistTrackPaging.getTotal());
  }
}
