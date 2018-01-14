package com.wrapper.spotify.requests.data.browse;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetListOfNewReleasesRequestTest implements ITest<Paging<AlbumSimplified>> {
  private final GetListOfNewReleasesRequest successRequest = SPOTIFY_API.getListOfNewReleases()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/browse/GetListOfNewReleasesRequest.json"))
          .build();

  public GetListOfNewReleasesRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Paging<AlbumSimplified>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Paging<AlbumSimplified> albumSimplifiedPaging) {
    assertEquals(
            "https://api.spotify.com/v1/browse/new-releases?country=SE&offset=5&limit=10",
            albumSimplifiedPaging.getHref());
    assertEquals(
            10,
            albumSimplifiedPaging.getItems().length);
    assertEquals(
            10,
            (int) albumSimplifiedPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/browse/new-releases?country=SE&offset=15&limit=10",
            albumSimplifiedPaging.getNext());
    assertEquals(
            5,
            (int) albumSimplifiedPaging.getOffset());
    assertEquals(
            "https://api.spotify.com/v1/browse/new-releases?country=SE&offset=0&limit=10",
            albumSimplifiedPaging.getPrevious());
    assertEquals(
            500,
            (int) albumSimplifiedPaging.getTotal());
  }
}
