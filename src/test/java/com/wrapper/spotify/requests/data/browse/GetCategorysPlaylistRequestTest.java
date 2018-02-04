package com.wrapper.spotify.requests.data.browse;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetCategorysPlaylistRequestTest extends AbstractDataTest<Paging<PlaylistSimplified>> {
  private final GetCategorysPlaylistsRequest defaultRequest = SPOTIFY_API.getCategorysPlaylists(CATEGORY_ID)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/browse/GetCategorysPlaylistsRequest.json"))
          .country(COUNTRY)
          .limit(LIMIT)
          .offset(OFFSET)
          .build();

  public GetCategorysPlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/browse/categories/dinner/playlists?country=SE&limit=10&offset=0",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<PlaylistSimplified>) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<PlaylistSimplified> playlistSimplifiedPaging) {
    assertEquals(
            "https://api.spotify.com/v1/browse/categories/party/playlists?country=BR&offset=0&limit=2",
            playlistSimplifiedPaging.getHref());
    assertEquals(
            2,
            playlistSimplifiedPaging.getItems().length);
    assertEquals(
            2,
            (int) playlistSimplifiedPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/browse/categories/party/playlists?country=BR&offset=2&limit=2",
            playlistSimplifiedPaging.getNext());
    assertEquals(
            0,
            (int) playlistSimplifiedPaging.getOffset());
    assertNull(
            playlistSimplifiedPaging.getPrevious());
    assertEquals(
            86,
            (int) playlistSimplifiedPaging.getTotal());
  }
}
