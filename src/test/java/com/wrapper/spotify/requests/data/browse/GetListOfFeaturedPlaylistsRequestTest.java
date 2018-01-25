package com.wrapper.spotify.requests.data.browse;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.FeaturedPlaylists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GetListOfFeaturedPlaylistsRequestTest implements ITest<FeaturedPlaylists> {
  private final GetListOfFeaturedPlaylistsRequest successRequest = SPOTIFY_API.getListOfFeaturedPlaylists()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/browse/GetListOfFeaturedPlaylistsRequest.json"))
          .build();

  public GetListOfFeaturedPlaylistsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((FeaturedPlaylists) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final FeaturedPlaylists featuredPlaylists) {
    assertEquals(
            "Monday morning music, coming right up!",
            featuredPlaylists.getMessage());
    assertNotNull(
            featuredPlaylists.getPlaylists());
  }
}
