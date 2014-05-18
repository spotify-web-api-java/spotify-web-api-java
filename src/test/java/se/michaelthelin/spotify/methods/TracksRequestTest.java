package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.TestConfiguration;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.models.Track;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class TracksRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final TracksRequest.Builder requestBuilder = api.getTracks("0eGsygTp906u18L0Oimnem", "1lDWb6b6ieDQ2xT7ewTC3G");
    if (TestConfiguration.USE_MOCK_RESPONSES) {
      requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("tracks.json"));
    }
    final TracksRequest request = requestBuilder.build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<List<Track>> tracksFuture = request.getAsync();

    Futures.addCallback(tracksFuture, new FutureCallback<List<Track>>() {
      @Override
      public void onSuccess(List<Track> tracks) {
        assertEquals(2, tracks.size());

        Track firstTrack = tracks.get(0);
        assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

        Track secondTrack = tracks.get(1);
        assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());

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

    final TracksRequest.Builder requestBuilder = api.getTracks("0eGsygTp906u18L0Oimnem", "1lDWb6b6ieDQ2xT7ewTC3G");
    if (TestConfiguration.USE_MOCK_RESPONSES) {
      requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("tracks.json"));
    }
    final TracksRequest request = requestBuilder.build();

    final List<Track> tracks = request.get();

    assertEquals(2, tracks.size());

    final Track firstTrack = tracks.get(0);
    assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

    final Track secondTrack = tracks.get(1);
    assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());
  }
}
