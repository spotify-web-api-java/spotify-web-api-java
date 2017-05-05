package com.wrapper.spotify.methods;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleTrack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.fail;


@RunWith(MockitoJUnitRunner.class)
public class TracksForAlbumRequestTest {

    @Test
    public void shouldTracksForAlbumId_async() throws Exception {
        final Api api = Api.DEFAULT_API;

        final TracksForAlbumRequest request = api.getTracksForAlbum("6TJmQnO44YE5BtTxH8pop1")
            .limit(2)
            .httpManager(TestUtil.MockedHttpManager.returningJson("album-track.json"))
            .build();

        final CountDownLatch asyncCompleted = new CountDownLatch(1);

        final SettableFuture<Page<SimpleTrack>> tracksFuture = request.getAsync();

        Futures.addCallback(tracksFuture, new FutureCallback<Page<SimpleTrack>>() {
            @Override
            public void onSuccess(Page<SimpleTrack> trackSearchResult) {
                assertEquals("https://api.spotify.com/v1/albums/6TJmQnO44YE5BtTxH8pop1/tracks?offset=0&limit=2", trackSearchResult.getHref());
                assertEquals(2, trackSearchResult.getLimit());
                assertEquals(0, trackSearchResult.getOffset());
                assertEquals(14, trackSearchResult.getTotal());
                assertEquals("https://api.spotify.com/v1/albums/6TJmQnO44YE5BtTxH8pop1/tracks?offset=2&limit=2", trackSearchResult.getNext());
                assertNull(trackSearchResult.getPrevious());

                List<SimpleTrack> tracks = trackSearchResult.getItems();
                assertEquals(2, tracks.size());

                SimpleTrack firstTrack = tracks.get(0);
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
        final Api api = Api.DEFAULT_API;

        final TracksForAlbumRequest request = api.getTracksForAlbum("6TJmQnO44YE5BtTxH8pop1")
            .limit(2)
            .httpManager(TestUtil.MockedHttpManager.returningJson("album-track.json"))
            .build();
        Page<SimpleTrack> trackSearchResult = request.get();
        assertNotNull(trackSearchResult);
        assertEquals("https://api.spotify.com/v1/albums/6TJmQnO44YE5BtTxH8pop1/tracks?offset=0&limit=2", trackSearchResult.getHref());
        assertEquals(2, trackSearchResult.getLimit());
        assertEquals(0, trackSearchResult.getOffset());
        assertEquals(14, trackSearchResult.getTotal());
        assertEquals("https://api.spotify.com/v1/albums/6TJmQnO44YE5BtTxH8pop1/tracks?offset=2&limit=2", trackSearchResult.getNext());
        assertNull(trackSearchResult.getPrevious());

        List<SimpleTrack> tracks = trackSearchResult.getItems();
        assertEquals(2, tracks.size());

        SimpleTrack firstTrack = tracks.get(0);
        assertEquals("https://open.spotify.com/track/6dAGqW4jLTtUN1zGpfT7df", firstTrack.getExternalUrls().get("spotify"));
        assertEquals("https://api.spotify.com/v1/tracks/6dAGqW4jLTtUN1zGpfT7df", firstTrack.getHref());
        assertEquals("6dAGqW4jLTtUN1zGpfT7df", firstTrack.getId());
        assertNotNull(firstTrack.getArtists());
    }
}
