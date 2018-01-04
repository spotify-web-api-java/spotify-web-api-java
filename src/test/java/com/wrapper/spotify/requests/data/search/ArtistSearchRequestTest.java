package com.wrapper.spotify.requests.data.search;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ArtistSearchRequestTest {

  @Test
  public void shouldGetArtistsResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final SearchArtistsRequest request = api.searchArtists("tania bowra")
            .limit(20)
            .offset(0)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/search/ArtistSearchRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final Future<Paging<Artist>> requestFuture = request.executeAsync();
    final Paging<Artist> artistPaging = requestFuture.get();

    assertEquals(20, (int) artistPaging.getLimit());
    assertEquals(0, (int) artistPaging.getOffset());
    assertTrue(artistPaging.getTotal() > 0);
    assertNull(artistPaging.getNext());
    assertNull(artistPaging.getPrevious());
    assertEquals("https://api.spotify.com/v1/search?query=tania+bowra&type=artist&market=DE&offset=0&limit=20", artistPaging.getHref());

    Artist[] artists = artistPaging.getItems();

    Artist firstArtist = artists[0];
    assertEquals("08td7MxkoHQkXnWAYD8d6Q", firstArtist.getId());
    assertEquals("https://open.spotify.com/artist/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getExternalUrls().get("spotify"));
    assertNotNull(firstArtist.getGenres());
    assertEquals("https://api.spotify.com/v1/artists/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getHref());
    assertNotNull(firstArtist.getImages());
    assertEquals("Tania Bowra", firstArtist.getName());
    assertTrue(firstArtist.getPopularity() >= 0 && firstArtist.getPopularity() <= 100);
    assertEquals(ModelObjectType.ARTIST, firstArtist.getType());
    assertEquals("spotify:artist:08td7MxkoHQkXnWAYD8d6Q", firstArtist.getUri());
  }

  @Test
  public void shouldGetArtistsResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final SearchArtistsRequest request = api.searchArtists("tania bowra")
            .limit(20)
            .offset(0)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/search/ArtistSearchRequest.json"))
            .build();

    final Paging<Artist> artistSearchResult = request.execute();

    assertEquals(20, (int) artistSearchResult.getLimit());
    assertEquals(0, (int) artistSearchResult.getOffset());
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
    assertEquals(ModelObjectType.ARTIST, firstArtist.getType());
    assertEquals("spotify:artist:08td7MxkoHQkXnWAYD8d6Q", firstArtist.getUri());
  }

}
