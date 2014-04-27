package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.SpotifyProtos.Track;
import se.michaelthelin.spotify.TestUtil;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;


@RunWith(MockitoJUnitRunner.class)
public class TrackRequestTest {

  @Test
  public void shouldGetTrackResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("track.json");
    final TrackRequest request = api.track().id("0eGsygTp906u18L0Oimnem").httpManager(mockedHttpManager).build();

    final SettableFuture<Track> trackFuture = request.getTrackAsync();

    Futures.addCallback(trackFuture, new FutureCallback<Track>() {
      @Override
      public void onSuccess(Track track) {
        assertNotNull(track);
        assertEquals("0eGsygTp906u18L0Oimnem", track.getId());
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });
  }

  @Test
  public void shouldGetTrackResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("track.json");
    final TrackRequest request = api.track().id("0eGsygTp906u18L0Oimnem").httpManager(mockedHttpManager).build();

    final Track track = request.getTrack();

    assertNotNull(track);
    assertEquals("0eGsygTp906u18L0Oimnem", track.getId());
  }
}
