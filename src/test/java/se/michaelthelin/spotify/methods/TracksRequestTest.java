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

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TracksRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    Api api = Api.DEFAULT_API;
    TracksRequest request = api.tracks().id("0eGsygTp906u18L0Oimnem","1lDWb6b6ieDQ2xT7ewTC3G").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("tracks.json");
    TracksRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    ListenableFuture<List<Track>> tracksFuture = spy.getTracksAsync();

    Futures.addCallback(tracksFuture, new FutureCallback<List<Track>>() {
      @Override
      public void onSuccess(List<Track> tracks) {
        assertEquals(2, tracks.size());

        Track firstTrack = tracks.get(0);
        assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

        Track secondTrack = tracks.get(1);
        assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });
  }

  @Test
  public void shouldGetTracksResult_sync() throws Exception {
    Api api = Api.DEFAULT_API;
    TracksRequest request = api.tracks().id("0eGsygTp906u18L0Oimnem","1lDWb6b6ieDQ2xT7ewTC3G").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("tracks.json");
    TracksRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    List<Track> tracks = spy.getTracks();

    assertEquals(2, tracks.size());

    Track firstTrack = tracks.get(0);
    assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

    Track secondTrack = tracks.get(1);
    assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());
  }
}
