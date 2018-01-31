package com.wrapper.spotify.requests.data.playlists;

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
public class GetListOfUsersPlaylistsRequestTest extends AbstractDataTest<Paging<PlaylistSimplified>> {
  private final GetListOfUsersPlaylistsRequest defaultRequest = SPOTIFY_API
          .getListOfUsersPlaylists(ID_USER)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/GetListOfUsersPlaylistsRequest.json"))
          .limit(LIMIT)
          .offset(OFFSET)
          .build();

  public GetListOfUsersPlaylistsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists?limit=10&offset=0",
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
