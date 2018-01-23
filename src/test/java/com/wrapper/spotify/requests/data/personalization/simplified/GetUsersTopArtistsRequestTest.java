package com.wrapper.spotify.requests.data.personalization.simplified;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersTopArtistsRequestTest implements ITest<Paging<Artist>> {
  private final GetUsersTopArtistsRequest successRequest = SPOTIFY_API
          .getUsersTopArtists()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/personalization/simplified/GetUsersTopArtistsRequest.json"))
          .build();

  public GetUsersTopArtistsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Paging<Artist>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Paging<Artist> artistPaging) {
    assertEquals(
            10,
            artistPaging.getItems().length);
    assertEquals(
            50,
            (int) artistPaging.getTotal());
    assertEquals(
            10,
            (int) artistPaging.getLimit());
    assertEquals(
            5,
            (int) artistPaging.getOffset());
    assertEquals(
            "https://api.spotify.com/v1/me/top/artists?limit=10&offset=5",
            artistPaging.getHref());
    assertNull(
            artistPaging.getPrevious());
    assertEquals(
            "https://api.spotify.com/v1/me/top/artists?limit=10&offset=15",
            artistPaging.getNext());
  }
}
