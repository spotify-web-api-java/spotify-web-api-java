package se.michaelthelin.spotify.methods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos.Track;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TracksRequestTest {

  @Test
  public void shouldGetTracksResult() throws Exception {
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
