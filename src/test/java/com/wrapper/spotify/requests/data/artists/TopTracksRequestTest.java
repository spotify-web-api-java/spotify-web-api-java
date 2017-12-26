package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.ModelObjectType;
import com.wrapper.spotify.model_objects.Track;
import com.wrapper.spotify.requests.data.artists.TopTracksRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TopTracksRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final TopTracksRequest request = api.getTopTracksForArtist("43ZHCT0cAZBISjO8DG9PnE", "GB")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("tracks-for-artist.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Track[]> tracksFuture = request.getAsync();

    Futures.addCallback(tracksFuture, new FutureCallback<Track[]>() {
      @Override
      public void onSuccess(Track[] tracks) {
        assertTrue(tracks.length > 0);

        Track firstTrack = tracks[0];

        assertNotNull(firstTrack.getAlbum());
        assertNotNull(firstTrack.getArtists());
        assertNotNull(firstTrack.getAvailableMarkets());
        assertTrue(firstTrack.getDiscNumber() > 0);
        assertTrue(firstTrack.getDurationMs() > 0);
        assertNotNull(firstTrack.getIsExplicit());
        assertNotNull(firstTrack.getExternalIds());

        String id = firstTrack.getId();
        assertNotNull(firstTrack.getId());
        assertEquals("https://open.spotify.com/track/" + id, firstTrack.getExternalUrls().get("spotify"));
        assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());
        assertTrue(firstTrack.getPopularity() >= 0 && firstTrack.getPopularity() <= 100);
        assertNotNull(firstTrack.getPreviewUrl());
        assertTrue(firstTrack.getTrackNumber() >= 0);
        assertEquals(ModelObjectType.TRACK, firstTrack.getType());
        assertEquals("spotify:track:" + id, firstTrack.getUri());

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
    final Api api = Api.DEFAULT_API;

    final TopTracksRequest request = api.getTopTracksForArtist("43ZHCT0cAZBISjO8DG9PnE", "GB")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("tracks-for-artist.json"))
            .build();

    final Track[] tracks = request.get();

    assertTrue(tracks.length > 0);

    Track firstTrack = tracks[0];

    assertNotNull(firstTrack.getAlbum());
    assertNotNull(firstTrack.getArtists());
    assertNotNull(firstTrack.getAvailableMarkets());
    assertTrue(firstTrack.getDiscNumber() > 0);
    assertTrue(firstTrack.getDurationMs() > 0);
    assertNotNull(firstTrack.getIsExplicit());
    assertNotNull(firstTrack.getExternalIds());

    String id = firstTrack.getId();
    assertNotNull(firstTrack.getId());
    assertEquals("https://open.spotify.com/track/" + id, firstTrack.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());
    assertTrue(firstTrack.getPopularity() >= 0 && firstTrack.getPopularity() <= 100);
    assertNotNull(firstTrack.getPreviewUrl());
    assertTrue(firstTrack.getTrackNumber() >= 0);
    assertEquals(ModelObjectType.TRACK, firstTrack.getType());
    assertEquals("spotify:track:" + id, firstTrack.getUri());
  }

}
