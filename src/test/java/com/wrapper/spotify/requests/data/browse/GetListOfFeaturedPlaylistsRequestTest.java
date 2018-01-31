package com.wrapper.spotify.requests.data.browse;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.FeaturedPlaylists;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GetListOfFeaturedPlaylistsRequestTest extends AbstractDataTest<FeaturedPlaylists> {
  private final GetListOfFeaturedPlaylistsRequest defaultRequest = SPOTIFY_API.getListOfFeaturedPlaylists()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/browse/GetListOfFeaturedPlaylistsRequest.json"))
          .country(COUNTRY)
          .limit(LIMIT)
          .locale(LOCALE)
          .offset(OFFSET)
          .timestamp(TIMESTAMP)
          .build();

  public GetListOfFeaturedPlaylistsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/browse/featured-playlists?country=SE&limit=10&locale=sv_SE&offset=0&timestamp=2014-10-23T09%3A00%3A00",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((FeaturedPlaylists) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final FeaturedPlaylists featuredPlaylists) {
    assertEquals(
            "Monday morning music, coming right up!",
            featuredPlaylists.getMessage());
    assertNotNull(
            featuredPlaylists.getPlaylists());
  }
}
