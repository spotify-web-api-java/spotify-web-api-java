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

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class TrackSearchRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-track-page1.json");
    final TrackSearchRequest request = api.searchTracks("Mr. Brightside").httpManager(mockedHttpManager).build();

    SettableFuture<Page<Track>> searchResultFuture = request.getTracksPageAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Page<Track>>() {
      @Override
      public void onSuccess(Page<Track> trackSearchResult) {
        List<Track> tracks = trackSearchResult.getItems();

        assertEquals(20, tracks.size());

        Track firstTrack = tracks.get(0);
        assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

        Track secondTrack = tracks.get(1);
        assertEquals("5zvJ6DUahHHjeknQPn7iAH", secondTrack.getId());
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });
  }

  @Test
  public void shouldGetTracksResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-track-page1.json");
    final TrackSearchRequest request = api.searchTracks("Mr. Brightside").httpManager(mockedHttpManager).build();

    final Page<Track> trackSearchResult = request.getTracksPage();

    final List<Track> tracks = trackSearchResult.getItems();

    assertEquals(20, tracks.size());

    final Track firstTrack = tracks.get(0);
    assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

    final Track secondTrack = tracks.get(1);
    assertEquals("5zvJ6DUahHHjeknQPn7iAH", secondTrack.getId());
  }

}
