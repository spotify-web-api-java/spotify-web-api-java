package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos.Track;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrackRequestTest {

  @Test
  public void shouldGetTrackResult_async() throws Exception {
    Api api = Api.DEFAULT_API;
    TrackRequest request = api.track().id("0eGsygTp906u18L0Oimnem").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("track.json");
    TrackRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    ListenableFuture<Track> trackFuture = spy.getTrackAsync();

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
    Api api = Api.DEFAULT_API;
    TrackRequest request = api.track().id("0eGsygTp906u18L0Oimnem").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("track.json");
    TrackRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    Track track = spy.getTrack();

    assertNotNull(track);
    assertEquals("0eGsygTp906u18L0Oimnem", track.getId());
  }
}
