package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.objects.Artist;
import com.wrapper.spotify.objects.ObjectType;
import com.wrapper.spotify.objects.Paging;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class ArtistSearchRequestTest {

  @Test
  public void shouldGetArtistsResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final ArtistSearchRequest request = api.searchArtists("tania bowra")
            .limit(20)
            .offset(0)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("search-artist.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Paging<Artist>> searchResultFuture = request.getAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Paging<Artist>>() {
      @Override
      public void onSuccess(Paging<Artist> artistSearchResult) {
        assertEquals(20, artistSearchResult.getLimit());
        assertEquals(0, artistSearchResult.getOffset());
        assertTrue(artistSearchResult.getTotal() > 0);
        assertNull(artistSearchResult.getNext());
        assertNull(artistSearchResult.getPrevious());
        assertEquals("https://api.spotify.com/v1/search?query=tania+bowra&type=artist&market=DE&offset=0&limit=20", artistSearchResult.getHref());

        Artist[] artists = artistSearchResult.getItems();

        Artist firstArtist = artists[0];
        assertEquals("08td7MxkoHQkXnWAYD8d6Q", firstArtist.getId());
        assertEquals("https://open.spotify.com/artist/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getExternalUrls().get("spotify"));
        assertNotNull(firstArtist.getGenres());
        assertEquals("https://api.spotify.com/v1/artists/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getHref());
        assertNotNull(firstArtist.getImages());
        assertEquals("Tania Bowra", firstArtist.getName());
        assertTrue(firstArtist.getPopularity() >= 0 && firstArtist.getPopularity() <= 100);
        assertEquals(ObjectType.ARTIST, firstArtist.getType());
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
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("search-artist.json"))
            .build();

    final Paging<Artist> artistSearchResult = request.get();

    assertEquals(20, artistSearchResult.getLimit());
    assertEquals(0, artistSearchResult.getOffset());
    assertTrue(artistSearchResult.getTotal() > 0);
    assertNull(artistSearchResult.getNext());
    assertNull(artistSearchResult.getPrevious());
    assertEquals("https://api.spotify.com/v1/search?query=tania+bowra&type=artist&market=DE&offset=0&limit=20", artistSearchResult.getHref());

    Artist[] artists = artistSearchResult.getItems();

    Artist firstArtist = artists[0];
    assertEquals("08td7MxkoHQkXnWAYD8d6Q", firstArtist.getId());
    assertEquals("https://open.spotify.com/artist/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getExternalUrls().get("spotify"));
    assertNotNull(firstArtist.getGenres());
    assertEquals("https://api.spotify.com/v1/artists/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getHref());
    assertNotNull(firstArtist.getImages());
    assertEquals("Tania Bowra", firstArtist.getName());
    assertTrue(firstArtist.getPopularity() >= 0 && firstArtist.getPopularity() <= 100);
    assertEquals(ObjectType.ARTIST, firstArtist.getType());
    assertEquals("spotify:artist:08td7MxkoHQkXnWAYD8d6Q", firstArtist.getUri());
  }

}
