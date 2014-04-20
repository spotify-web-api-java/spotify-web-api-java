package se.michaelthelin.spotify.methods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos.Artist;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistRequestTest {

  @Test
  public void shouldGetArtistResult() throws Exception {
    Api api = Api.DEFAULT_API;
    ArtistRequest request = api.artist().id("0LcJLqbBmaGUft1e9Mm8HV").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("artist.json");
    ArtistRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    Artist artist = spy.getArtist();
    assertNotNull(artist);
    assertEquals("0LcJLqbBmaGUft1e9Mm8HV", artist.getId());
  }

}
