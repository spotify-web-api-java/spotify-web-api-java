package se.michaelthelin.spotify.methods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos.Artist;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistsRequestTest {

  @Test
  public void shouldGetArtistsResult() throws Exception {
    Api api = Api.DEFAULT_API;
    ArtistsRequest request = api.artists().id("0oSGxfWSnnOXhD2fKuz2Gy","3dBVyJ7JuOMt4GE9607Qin").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("artists.json");
    ArtistsRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    List<Artist> artists = spy.getArtists();
    assertEquals(2, artists.size());

    Artist firstArtist = artists.get(0);
    Artist secondArtist = artists.get(1);

    assertEquals("0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getId());
    assertEquals("3dBVyJ7JuOMt4GE9607Qin", secondArtist.getId());
  }

}
