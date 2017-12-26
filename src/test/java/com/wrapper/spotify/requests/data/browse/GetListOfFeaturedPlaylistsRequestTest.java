package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.FeaturedPlaylists;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlaylistSimplified;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class GetListOfFeaturedPlaylistsRequestTest {

  @Test
  public void shouldGetNewReleases_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    Calendar calendar = Calendar.getInstance();
    calendar.set(2014, Calendar.OCTOBER, 23, 9, 0, 0);
    Date timestamp = calendar.getTime();

    final GetListOfFeaturedPlaylistsRequest request = api.getFeaturedPlaylists()
            .limit(1)
            .offset(1)
            .country(CountryCode.SE)
            .timestamp(timestamp)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("featured-playlists.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<FeaturedPlaylists> future = request.getAsync();

    Futures.addCallback(future, new FutureCallback<FeaturedPlaylists>() {
      @Override
      public void onSuccess(FeaturedPlaylists featuredPlaylists) {
        assertEquals("Sleepy?", featuredPlaylists.getMessage());

        Paging<PlaylistSimplified> playlistPage = featuredPlaylists.getPlaylists();

        assertEquals(23, playlistPage.getTotal());
        assertEquals(0, playlistPage.getOffset());
        assertEquals(1, playlistPage.getLimit());
        assertEquals("https://api.spotify.com/v1/browse/featured-playlists?timestamp=2017-12-03T01%3A40%3A49&offset=1&limit=1",
                playlistPage.getNext());
        assertNull(playlistPage.getPrevious());

        PlaylistSimplified[] items = playlistPage.getItems();
        assertEquals(1, items.length);

        PlaylistSimplified playlist = items[0];
        assertEquals("37i9dQZF1DWStLt4f1zJ6I", playlist.getId());
        assertEquals("Songs For Sleeping", playlist.getName());
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
  public void shouldGetArtistsResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    Calendar calendar = Calendar.getInstance();
    calendar.set(2014, Calendar.OCTOBER, 23, 9, 0, 0);
    Date timestamp = calendar.getTime();

    final GetListOfFeaturedPlaylistsRequest request = api.getFeaturedPlaylists()
            .limit(1)
            .offset(1)
            .country(CountryCode.SE)
            .timestamp(timestamp)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("featured-playlists.json"))
            .build();

    FeaturedPlaylists featuredPlaylists = request.get();

    assertEquals("Sleepy?", featuredPlaylists.getMessage());

    Paging<PlaylistSimplified> playlistPage = featuredPlaylists.getPlaylists();

    assertEquals(23, playlistPage.getTotal());
    assertEquals(0, playlistPage.getOffset());
    assertEquals(1, playlistPage.getLimit());
    assertEquals("https://api.spotify.com/v1/browse/featured-playlists?timestamp=2017-12-03T01%3A40%3A49&offset=1&limit=1",
            playlistPage.getNext());
    assertNull(playlistPage.getPrevious());

    PlaylistSimplified[] items = playlistPage.getItems();
    assertEquals(1, items.length);

    PlaylistSimplified playlist = items[0];
    assertEquals("37i9dQZF1DWStLt4f1zJ6I", playlist.getId());
    assertEquals("Songs For Sleeping", playlist.getName());

  }

}
