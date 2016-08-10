package com.wrapper.spotify.methods;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.fail;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleAlbum;
import com.wrapper.spotify.models.SimplePlaylist;
import com.wrapper.spotify.models.SpotifyEntityType;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistSearchRequestTest {

	@Test
	public void shouldGetPlaylistsResult_async() throws Exception {
		final Api api = Api.DEFAULT_API;

		final PlaylistSearchRequest request = api.searchPlaylists("dog")
				.httpManager(TestUtil.MockedHttpManager.returningJson("search-playlist.json")).build();

		final CountDownLatch asyncCompleted = new CountDownLatch(1);

		final SettableFuture<Page<SimplePlaylist>> searchResultFuture = request.getAsync();

		Futures.addCallback(searchResultFuture, new FutureCallback<Page<SimplePlaylist>>() {
			@Override
			public void onSuccess(Page<SimplePlaylist> playlistSearchResult) {
				
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
		final Api api = Api.DEFAULT_API;

		final PlaylistSearchRequest request = api.searchPlaylists("dog")
				.httpManager(TestUtil.MockedHttpManager.returningJson("search-playlist.json")).build();

		final Page<SimplePlaylist> playlistSearchResult = request.get();
		validatePlayists(playlistSearchResult);
	}

	private void validatePlayists(final Page<SimplePlaylist> playlistSearchResult) {

		assertEquals("https://api.spotify.com/v1/search?query=tdog&offset=0&limit=20&type=playlist",
				playlistSearchResult.getHref());
		assertEquals(20, playlistSearchResult.getLimit());
		assertEquals(0, playlistSearchResult.getOffset());
		assertNull(playlistSearchResult.getNext());
		assertNull(playlistSearchResult.getPrevious());
		assertEquals(7, playlistSearchResult.getTotal());

		List<SimplePlaylist> playlists = playlistSearchResult.getItems();
		assertEquals(7, playlists.size());

		SimplePlaylist firstPlaylist = playlists.get(0);
		assertEquals("http://open.spotify.com/user/theodorduf/playlist/5W11lXKiV9B5RZluPrxmCS",
				firstPlaylist.getExternalUrls().get("spotify"));
		assertEquals("https://api.spotify.com/v1/users/theodorduf/playlists/5W11lXKiV9B5RZluPrxmCS",
				firstPlaylist.getHref());
		assertEquals("5W11lXKiV9B5RZluPrxmCS", firstPlaylist.getId());
		assertEquals("Tdog", firstPlaylist.getName());
		assertNotNull(firstPlaylist.getImages());
		assertNotNull(firstPlaylist.getOwner());
		assertEquals(SpotifyEntityType.PLAYLIST, firstPlaylist.getType());
		assertEquals("spotify:user:theodorduf:playlist:5W11lXKiV9B5RZluPrxmCS", firstPlaylist.getUri());
	}
}
