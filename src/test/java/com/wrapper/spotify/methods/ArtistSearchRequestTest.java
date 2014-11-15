package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SpotifyEntityType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class ArtistSearchRequestTest {

  @Test
  public void shouldGetArtistsResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final ArtistSearchRequest request = api.searchArtists("tania bowra")
        .limit(20)
        .offset(0)
        .httpManager(TestUtil.MockedHttpManager.returningJson("search-artist.json"))
        .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Page<Artist>> searchResultFuture = request.getAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Page<Artist>>() {
      @Override
      public void onSuccess(Page<Artist> artistSearchResult) {
        assertEquals(20, artistSearchResult.getLimit());
        assertEquals(0, artistSearchResult.getOffset());
        assertTrue(artistSearchResult.getTotal() > 0);
        assertNull(artistSearchResult.getNext());
        assertNull(artistSearchResult.getPrevious());
        assertEquals("https://api.spotify.com/v1/search?query=tania+bowra&offset=0&limit=20&type=artist", artistSearchResult.getHref());

        List<Artist> artists = artistSearchResult.getItems();

        Artist firstArtist = artists.get(0);
        assertEquals("08td7MxkoHQkXnWAYD8d6Q", firstArtist.getId());
        assertEquals("https://open.spotify.com/artist/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getExternalUrls().get("spotify"));
        assertNotNull(firstArtist.getGenres());
        assertEquals("https://api.spotify.com/v1/artists/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getHref());
        assertNotNull(firstArtist.getImages());
        assertEquals("Tania Bowra", firstArtist.getName());
        assertTrue(firstArtist.getPopularity() >= 0 && firstArtist.getPopularity() <= 100);
        assertEquals(SpotifyEntityType.ARTIST, firstArtist.getType());
        assertEquals("spotify:artist:08td7MxkoHQkXnWAYD8d6Q", firstArtist.getUri());

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

    final ArtistSearchRequest request = api.searchArtists("tania bowra")
        .limit(20)
        .offset(0)
        .httpManager(TestUtil.MockedHttpManager.returningJson("search-artist.json"))
        .build();

    final Page<Artist> artistSearchResult = request.get();

    assertEquals(20, artistSearchResult.getLimit());
    assertEquals(0, artistSearchResult.getOffset());
    assertTrue(artistSearchResult.getTotal() > 0);
    assertNull(artistSearchResult.getNext());
    assertNull(artistSearchResult.getPrevious());
    assertEquals("https://api.spotify.com/v1/search?query=tania+bowra&offset=0&limit=20&type=artist", artistSearchResult.getHref());

    List<Artist> artists = artistSearchResult.getItems();

    Artist firstArtist = artists.get(0);
    assertEquals("08td7MxkoHQkXnWAYD8d6Q", firstArtist.getId());
    assertEquals("https://open.spotify.com/artist/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getExternalUrls().get("spotify"));
    assertNotNull(firstArtist.getGenres());
    assertEquals("https://api.spotify.com/v1/artists/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getHref());
    assertNotNull(firstArtist.getImages());
    assertEquals("Tania Bowra", firstArtist.getName());
    assertTrue(firstArtist.getPopularity() >= 0 && firstArtist.getPopularity() <= 100);
    assertEquals(SpotifyEntityType.ARTIST, firstArtist.getType());
    assertEquals("spotify:artist:08td7MxkoHQkXnWAYD8d6Q", firstArtist.getUri());
  }

}
