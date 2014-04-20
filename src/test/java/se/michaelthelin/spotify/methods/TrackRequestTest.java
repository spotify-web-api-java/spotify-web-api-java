package se.michaelthelin.spotify.methods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrackRequestTest {

  @Test
  public void shouldGetTrackResult() throws Exception {
    Api api = Api.DEFAULT_API;
    TrackRequest request = api.track().id("0eGsygTp906u18L0Oimnem").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("track.json");
    TrackRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    SpotifyProtos.Track track = spy.getTrack();
    assertNotNull(track);
    assertEquals("0eGsygTp906u18L0Oimnem", track.getId());
  }
}
