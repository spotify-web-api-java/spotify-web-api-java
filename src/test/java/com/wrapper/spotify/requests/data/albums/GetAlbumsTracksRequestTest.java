package com.wrapper.spotify.requests.data.albums;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAlbumsTracksRequestTest {

  @Test
  public void shouldTracksForAlbumId_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetAlbumsTracksRequest request = api.getAlbumsTracks("6TJmQnO44YE5BtTxH8pop1")
            .limit(2)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetAlbumsTracksRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final Future<Paging<TrackSimplified>> requestFuture = request.executeAsync();
    final Paging<TrackSimplified> trackSimplifiedPaging = requestFuture.get();

    assertEquals("https://api.spotify.com/v1/albums/6TJmQnO44YE5BtTxH8pop1/tracks?offset=0&limit=2", trackSimplifiedPaging.getHref());
    assertEquals(2, (int) trackSimplifiedPaging.getLimit());
    assertEquals(0, (int) trackSimplifiedPaging.getOffset());
    assertEquals(14, (int) trackSimplifiedPaging.getTotal());
    assertEquals("https://api.spotify.com/v1/albums/6TJmQnO44YE5BtTxH8pop1/tracks?offset=2&limit=2", trackSimplifiedPaging.getNext());
    assertNull(trackSimplifiedPaging.getPrevious());

    TrackSimplified[] tracks = trackSimplifiedPaging.getItems();
    assertEquals(2, tracks.length);

    TrackSimplified firstTrack = tracks[0];
    assertEquals("https://open.spotify.com/track/6dAGqW4jLTtUN1zGpfT7df", firstTrack.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/tracks/6dAGqW4jLTtUN1zGpfT7df", firstTrack.getHref());
    assertEquals("6dAGqW4jLTtUN1zGpfT7df", firstTrack.getId());
    assertNotNull(firstTrack.getArtists());
  }

  @Test
  public void shouldTracksForAlbumId_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetAlbumsTracksRequest request = api.getAlbumsTracks("6TJmQnO44YE5BtTxH8pop1")
            .limit(2)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetAlbumsTracksRequest.json"))
            .build();
    Paging<TrackSimplified> trackSearchResult = request.execute();
    assertNotNull(trackSearchResult);
    assertEquals("https://api.spotify.com/v1/albums/6TJmQnO44YE5BtTxH8pop1/tracks?offset=0&limit=2", trackSearchResult.getHref());
    assertEquals(2, (int) trackSearchResult.getLimit());
    assertEquals(0, (int) trackSearchResult.getOffset());
    assertEquals(14, (int) trackSearchResult.getTotal());
    assertEquals("https://api.spotify.com/v1/albums/6TJmQnO44YE5BtTxH8pop1/tracks?offset=2&limit=2", trackSearchResult.getNext());
    assertNull(trackSearchResult.getPrevious());

    TrackSimplified[] tracks = trackSearchResult.getItems();
    assertEquals(2, tracks.length);

    TrackSimplified firstTrack = tracks[0];
    assertEquals("https://open.spotify.com/track/6dAGqW4jLTtUN1zGpfT7df", firstTrack.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/tracks/6dAGqW4jLTtUN1zGpfT7df", firstTrack.getHref());
    assertEquals("6dAGqW4jLTtUN1zGpfT7df", firstTrack.getId());
    assertNotNull(firstTrack.getArtists());
  }
}
