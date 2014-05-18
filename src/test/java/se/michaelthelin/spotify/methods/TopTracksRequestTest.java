package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.TestConfiguration;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.models.SpotifyEntityType;
import se.michaelthelin.spotify.models.Track;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class TopTracksRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final TopTracksRequest.Builder requestBuilder = api.getTopTracksForArtist("43ZHCT0cAZBISjO8DG9PnE", "GB");
    if (TestConfiguration.USE_MOCK_RESPONSES) {
      requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("tracks-for-artist.json"));
    }
    final TopTracksRequest request = requestBuilder.build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<List<Track>> tracksFuture = request.getAsync();

    Futures.addCallback(tracksFuture, new FutureCallback<List<Track>>() {
      @Override
      public void onSuccess(List<Track> tracks) {
        assertTrue(tracks.size() > 0);

        Track firstTrack = tracks.get(0);

        assertNotNull(firstTrack.getAlbum());
        assertNotNull(firstTrack.getArtists());
        assertNotNull(firstTrack.getAvailableMarkets());
        assertTrue(firstTrack.getDiscNumber() > 0);
        assertTrue(firstTrack.getDuration() > 0);
        assertNotNull(firstTrack.isExplicit());
        assertNotNull(firstTrack.getExternalIds());

        String id = firstTrack.getId();
        assertNotNull(firstTrack.getId());
        assertEquals("https://open.spotify.com/track/" + id, firstTrack.getExternalUrls().get("spotify"));
        assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());
        assertTrue(firstTrack.getPopularity() >= 0 && firstTrack.getPopularity() <= 100);
        assertNotNull(firstTrack.getPreviewUrl());
        assertTrue(firstTrack.getTrackNumber() >= 0);
        assertEquals(SpotifyEntityType.TRACK, firstTrack.getType());
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

    final TopTracksRequest.Builder requestBuilder = api.getTopTracksForArtist("43ZHCT0cAZBISjO8DG9PnE", "GB");
    if (TestConfiguration.USE_MOCK_RESPONSES) {
      requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("tracks-for-artist.json"));
    }
    final TopTracksRequest request = requestBuilder.build();

    final List<Track> tracks = request.get();

    assertTrue(tracks.size() > 0);

    Track firstTrack = tracks.get(0);

    assertNotNull(firstTrack.getAlbum());
    assertNotNull(firstTrack.getArtists());
    assertNotNull(firstTrack.getAvailableMarkets());
    assertTrue(firstTrack.getDiscNumber() > 0);
    assertTrue(firstTrack.getDuration() > 0);
    assertNotNull(firstTrack.isExplicit());
    assertNotNull(firstTrack.getExternalIds());

    String id = firstTrack.getId();
    assertNotNull(firstTrack.getId());
    assertEquals("https://open.spotify.com/track/" + id, firstTrack.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());
    assertTrue(firstTrack.getPopularity() >= 0 && firstTrack.getPopularity() <= 100);
    assertNotNull(firstTrack.getPreviewUrl());
    assertTrue(firstTrack.getTrackNumber() >= 0);
    assertEquals(SpotifyEntityType.TRACK, firstTrack.getType());
    assertEquals("spotify:track:" + id, firstTrack.getUri());
  }

}
