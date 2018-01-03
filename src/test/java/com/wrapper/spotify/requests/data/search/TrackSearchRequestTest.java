package com.wrapper.spotify.requests.data.search;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.IHttpManager;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTrackRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrackSearchRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final SearchTrackRequest request = api.searchTracks("tania bowra")
            .offset(0)
            .limit(20)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/search/TrackSearchRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    SettableFuture<Paging<Track>> searchResultFuture = request.getAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Paging<Track>>() {
      @Override
      public void onSuccess(Paging<Track> trackSearchResult) {
        assertTrue(trackSearchResult.getTotal() > 0);
        assertEquals(20, trackSearchResult.getLimit());
        assertEquals(0, trackSearchResult.getOffset());

        Track[] tracks = trackSearchResult.getItems();

        Track firstTrack = tracks[0];
        assertNotNull(firstTrack.getId());

        String id = firstTrack.getId();
        assertNotNull(firstTrack.getAlbum());
        assertNotNull(firstTrack.getArtists());
        assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());

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
  public void shouldGetTracksResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();
    final IHttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("requests/data/search/TrackSearchRequest.json");
    final SearchTrackRequest request = api.searchTracks("Mr. Brightside").setHttpManager(mockedHttpManager).build();

    final Paging<Track> trackSearchResult = request.get();

    assertTrue(trackSearchResult.getTotal() > 0);
    assertEquals(20, trackSearchResult.getLimit());
    assertEquals(0, trackSearchResult.getOffset());

    Track[] tracks = trackSearchResult.getItems();

    Track firstTrack = tracks[0];
    assertNotNull(firstTrack.getId());

    String id = firstTrack.getId();
    assertNotNull(firstTrack.getAlbum());
    assertNotNull(firstTrack.getArtists());
    assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());
  }

}
