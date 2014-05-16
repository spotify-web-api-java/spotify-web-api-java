package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.Track;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class TrackSearchRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-track.json");
    final TrackSearchRequest request = api.searchTracks("Mr. Brightside").httpManager(mockedHttpManager).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    SettableFuture<Page<Track>> searchResultFuture = request.getAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Page<Track>>() {
      @Override
      public void onSuccess(Page<Track> trackSearchResult) {
        List<Track> tracks = trackSearchResult.getItems();

        assertEquals(12, tracks.size());

        Track firstTrack = tracks.get(0);
        assertEquals("2TpxZ7JUBn3uw46aR7qd6V", firstTrack.getId());

        Track secondTrack = tracks.get(1);
        assertEquals("4PjcfyZZVE10TFd9EKA72r", secondTrack.getId());

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
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-track.json");
    final TrackSearchRequest request = api.searchTracks("Mr. Brightside").httpManager(mockedHttpManager).build();

    final Page<Track> trackSearchResult = request.get();

    final List<Track> tracks = trackSearchResult.getItems();

    assertEquals(12, tracks.size());

    final Track firstTrack = tracks.get(0);
    assertEquals("2TpxZ7JUBn3uw46aR7qd6V", firstTrack.getId());

    final Track secondTrack = tracks.get(1);
    assertEquals("4PjcfyZZVE10TFd9EKA72r", secondTrack.getId());
  }

}
