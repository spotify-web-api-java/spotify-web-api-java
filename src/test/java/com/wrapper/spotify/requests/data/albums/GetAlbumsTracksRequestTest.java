package com.wrapper.spotify.requests.data.albums;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

    final SettableFuture<Paging<TrackSimplified>> tracksFuture = request.getAsync();

    Futures.addCallback(tracksFuture, new FutureCallback<Paging<TrackSimplified>>() {
      @Override
      public void onSuccess(Paging<TrackSimplified> trackSearchResult) {
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
  public void shouldTracksForAlbumId_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetAlbumsTracksRequest request = api.getAlbumsTracks("6TJmQnO44YE5BtTxH8pop1")
            .limit(2)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetAlbumsTracksRequest.json"))
            .build();
    Paging<TrackSimplified> trackSearchResult = request.get();
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
