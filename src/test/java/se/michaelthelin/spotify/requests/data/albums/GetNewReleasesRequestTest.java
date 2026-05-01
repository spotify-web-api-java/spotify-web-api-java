package se.michaelthelin.spotify.requests.data.albums;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("deprecation")
public class GetNewReleasesRequestTest extends AbstractDataTest<Paging<AlbumSimplified>> {
  private final GetNewReleasesRequest defaultRequest = ITest.SPOTIFY_API
    .getNewReleases()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/albums/GetNewReleasesRequest.json"))
    .limit(ITest.LIMIT)
    .offset(ITest.OFFSET)
    .build();

  public GetNewReleasesRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/browse/new-releases?limit=10&offset=0",
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
      "https://api.spotify.com/v1/browse/new-releases?country=SE&offset=0&limit=2",
      albumSimplifiedPaging.getHref());
    assertEquals(
      1,
      albumSimplifiedPaging.getItems().length);
    assertEquals(
      1,
      (int) albumSimplifiedPaging.getTotal());
  }
}
