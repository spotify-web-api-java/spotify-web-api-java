package com.wrapper.spotify.requests.data.search;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.PlaylistSimplified;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistSearchRequestTest {

  @Test
  public void shouldGetPlaylistsResult_async() throws Exception {
    final Api api = Api.builder().accessToken("AccessToken").build();

    final SearchPlaylistRequest request = api.searchPlaylists("dog")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/search/PlaylistSearchRequest.json")).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Paging<PlaylistSimplified>> searchResultFuture = request.getAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Paging<PlaylistSimplified>>() {
      @Override
      public void onSuccess(Paging<PlaylistSimplified> playlistSearchResult) {

        validatePlayists(playlistSearchResult);
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetAlbumsResult_sync() throws Exception {
    final Api api = Api.builder().accessToken("AccessToken").build();

    final SearchPlaylistRequest request = api.searchPlaylists("dog")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/search/PlaylistSearchRequest.json")).build();

    final Paging<PlaylistSimplified> playlistSearchResult = request.get();
    validatePlayists(playlistSearchResult);
  }

  private void validatePlayists(final Paging<PlaylistSimplified> playlistSearchResult) {

    assertEquals("https://api.spotify.com/v1/search?query=%22doom+metal%22&type=playlist&market=DE&offset=0&limit=20",
            playlistSearchResult.getHref());
    assertEquals(20, playlistSearchResult.getLimit());
    assertEquals(0, playlistSearchResult.getOffset());
    assertEquals("https://api.spotify.com/v1/search?query=%22doom+metal%22&type=playlist&market=DE&offset=20&limit=20", playlistSearchResult.getNext());
    assertNull(playlistSearchResult.getPrevious());
    assertEquals(575, playlistSearchResult.getTotal());

    PlaylistSimplified[] playlists = playlistSearchResult.getItems();
    assertEquals(20, playlists.length);

    PlaylistSimplified firstPlaylist = playlists[0];
    assertEquals("https://open.spotify.com/user/holgar_the_red/playlist/5Lzif2bIMW8RiRLtbYJHU0",
            firstPlaylist.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/users/holgar_the_red/playlists/5Lzif2bIMW8RiRLtbYJHU0",
            firstPlaylist.getHref());
    assertEquals("5Lzif2bIMW8RiRLtbYJHU0", firstPlaylist.getId());
    assertEquals("Doom Metal", firstPlaylist.getName());
    assertNotNull(firstPlaylist.getImages());
    assertNotNull(firstPlaylist.getOwner());
    assertEquals(ModelObjectType.PLAYLIST, firstPlaylist.getType());
    assertEquals("spotify:user:holgar_the_red:playlist:5Lzif2bIMW8RiRLtbYJHU0", firstPlaylist.getUri());
  }
}
