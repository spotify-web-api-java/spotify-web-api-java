package se.michaelthelin.spotify.requests.data.browse;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetListOfNewReleasesRequestTest extends AbstractDataTest<Paging<AlbumSimplified>> {
  private final GetListOfNewReleasesRequest defaultRequest = SPOTIFY_API.getListOfNewReleases()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/browse/GetListOfNewReleasesRequest.json"))
    .country(COUNTRY)
    .limit(LIMIT)
    .offset(OFFSET)
    .build();

  public GetListOfNewReleasesRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/browse/new-releases?country=SE&limit=10&offset=0",
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

  public void shouldReturnDefault(final Paging<AlbumSimplified> albumSimplifiedPaging) {
    assertEquals(
      "https://api.spotify.com/v1/browse/new-releases?country=SE&offset=0&limit=20",
      albumSimplifiedPaging.getHref());
    assertEquals(
      2,
      albumSimplifiedPaging.getItems().length);
    assertEquals(
      20,
      (int) albumSimplifiedPaging.getLimit());
    assertEquals(
      "https://api.spotify.com/v1/browse/new-releases?country=SE&offset=20&limit=20",
      albumSimplifiedPaging.getNext());
    assertEquals(
      0,
      (int) albumSimplifiedPaging.getOffset());
    assertNull(
      albumSimplifiedPaging.getPrevious());
    assertEquals(
      500,
      (int) albumSimplifiedPaging.getTotal());
  }
}
