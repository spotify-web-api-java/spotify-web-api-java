package com.wrapper.spotify.requests.data.browse;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.AlbumType;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class GetListOfNewReleasesRequestTest {

  @Test
  public void shouldGetNewReleases_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetListOfNewReleasesRequest request = api.getListOfNewReleases()
            .limit(1)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/browse/GetListOfNewReleasesRequest.json"))
            .build();

    final Future<Paging<AlbumSimplified>> requestFuture = request.executeAsync();
    final Paging<AlbumSimplified> albumSimplifiedPaging = requestFuture.get();

    assertNotNull(albumSimplifiedPaging.getItems());

    assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=0&limit=1",
            albumSimplifiedPaging.getHref());

    assertEquals(1, (int) albumSimplifiedPaging.getLimit());
    assertEquals(0, (int) albumSimplifiedPaging.getOffset());
    assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=1&limit=1",
            albumSimplifiedPaging.getNext());
    assertNull(albumSimplifiedPaging.getPrevious());
    assertEquals(500, (int) albumSimplifiedPaging.getTotal());

    AlbumSimplified firstItem = albumSimplifiedPaging.getItems()[0];
    assertEquals(AlbumType.SINGLE, firstItem.getAlbumType());
    assertEquals(62, firstItem.getAvailableMarkets().length);
    assertNotNull(firstItem.getExternalUrls());
    assertEquals("spotify:album:52kvZcbEDm0v2kWZQXjuuA", firstItem.getUri());
  }

  @Test
  public void shouldGetArtistsResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetListOfNewReleasesRequest request = api.getListOfNewReleases()
            .limit(1)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/browse/GetListOfNewReleasesRequest.json"))
            .build();

    Paging<AlbumSimplified> newReleases = request.execute();

    assertNotNull(newReleases.getItems());

    assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=0&limit=1",
            newReleases.getHref());

    assertEquals(1, (int) newReleases.getLimit());
    assertEquals(0, (int) newReleases.getOffset());
    assertEquals("https://api.spotify.com/v1/browse/new-releases?offset=1&limit=1",
            newReleases.getNext());
    assertNull(newReleases.getPrevious());
    assertEquals(500, (int) newReleases.getTotal());

    AlbumSimplified firstItem = newReleases.getItems()[0];
    assertEquals(AlbumType.SINGLE, firstItem.getAlbumType());
    assertEquals(62, firstItem.getAvailableMarkets().length);
    assertNotNull(firstItem.getExternalUrls());
    assertEquals("spotify:album:52kvZcbEDm0v2kWZQXjuuA", firstItem.getUri());

  }
}
