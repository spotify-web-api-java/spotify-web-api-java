package com.wrapper.spotify.requests.data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.special.FeaturedPlaylists;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GetListOfFeaturedPlaylistsRequestTest {

  @Test
  public void shouldGetNewReleases_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    Calendar calendar = Calendar.getInstance();
    calendar.set(2014, Calendar.OCTOBER, 23, 9, 0, 0);
    Date timestamp = calendar.getTime();

    final GetListOfFeaturedPlaylistsRequest request = api.getListOfFeaturedPlaylists()
            .limit(1)
            .offset(1)
            .country(CountryCode.SE)
            .timestamp(timestamp)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/browse/GetListOfFeaturedPlaylistsRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final Future<FeaturedPlaylists> requestFuture = request.executeAsync();
    final FeaturedPlaylists featuredPlaylists = requestFuture.get();

    assertEquals("Sleepy?", featuredPlaylists.getMessage());

    Paging<PlaylistSimplified> playlistPage = featuredPlaylists.getPlaylists();

    assertEquals(23, (int) playlistPage.getTotal());
    assertEquals(0, (int) playlistPage.getOffset());
    assertEquals(1, (int) playlistPage.getLimit());
    assertEquals("https://api.spotify.com/v1/browse/featured-playlists?timestamp=2017-12-03T01%3A40%3A49&offset=1&limit=1",
            playlistPage.getNext());
    assertNull(playlistPage.getPrevious());

    PlaylistSimplified[] items = playlistPage.getItems();
    assertEquals(1, items.length);

    PlaylistSimplified playlist = items[0];
    assertEquals("37i9dQZF1DWStLt4f1zJ6I", playlist.getId());
    assertEquals("Songs For Sleeping", playlist.getName());
  }

  @Test
  public void shouldGetArtistsResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    Calendar calendar = Calendar.getInstance();
    calendar.set(2014, Calendar.OCTOBER, 23, 9, 0, 0);
    Date timestamp = calendar.getTime();

    final GetListOfFeaturedPlaylistsRequest request = api.getListOfFeaturedPlaylists()
            .limit(1)
            .offset(1)
            .country(CountryCode.SE)
            .timestamp(timestamp)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/browse/GetListOfFeaturedPlaylistsRequest.json"))
            .build();

    FeaturedPlaylists featuredPlaylists = request.execute();

    assertEquals("Sleepy?", featuredPlaylists.getMessage());

    Paging<PlaylistSimplified> playlistPage = featuredPlaylists.getPlaylists();

    assertEquals(23, (int) playlistPage.getTotal());
    assertEquals(0, (int) playlistPage.getOffset());
    assertEquals(1, (int) playlistPage.getLimit());
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
