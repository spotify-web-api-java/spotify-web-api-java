package com.wrapper.spotify.requests.data.search;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistSearchRequestTest {

  @Test
  public void shouldGetPlaylistsResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final SearchPlaylistsRequest request = api.searchPlaylists("dog")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/search/PlaylistSearchRequest.json")).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Paging<ArtistSimplified.PlaylistSimplified>> searchResultFuture = request.getAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Paging<ArtistSimplified.PlaylistSimplified>>() {
      @Override
      public void onSuccess(Paging<ArtistSimplified.PlaylistSimplified> playlistSearchResult) {

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
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final SearchPlaylistsRequest request = api.searchPlaylists("dog")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/search/PlaylistSearchRequest.json")).build();

    final Paging<ArtistSimplified.PlaylistSimplified> playlistSearchResult = request.get();
    validatePlayists(playlistSearchResult);
  }

  private void validatePlayists(final Paging<ArtistSimplified.PlaylistSimplified> playlistSearchResult) {

    assertEquals("https://api.spotify.com/v1/search?query=%22doom+metal%22&type=playlist&market=DE&offset=0&limit=20",
            playlistSearchResult.getHref());
    assertEquals(20, playlistSearchResult.getLimit());
    assertEquals(0, playlistSearchResult.getOffset());
    assertEquals("https://api.spotify.com/v1/search?query=%22doom+metal%22&type=playlist&market=DE&offset=20&limit=20", playlistSearchResult.getNext());
    assertNull(playlistSearchResult.getPrevious());
    assertEquals(575, playlistSearchResult.getTotal());

    ArtistSimplified.PlaylistSimplified[] playlists = playlistSearchResult.getItems();
    assertEquals(20, playlists.length);

    ArtistSimplified.PlaylistSimplified firstPlaylist = playlists[0];
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
