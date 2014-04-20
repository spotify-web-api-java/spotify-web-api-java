package se.michaelthelin.spotify.methods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos.Album;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class AlbumRequestTest {

  @Test
  public void shouldGetAlbumResult() throws Exception {
    Api api = Api.DEFAULT_API;
    AlbumRequest request = api.album().id("7e0ij2fpWaxOEHv5fUYZjd").build();

    // Mock response
    String albumResponseFixture = JsonUtilTest.readTestData("album.json");
    AlbumRequest spy = spy(request);
    when(spy.getJson()).thenReturn(albumResponseFixture);

    Album album = spy.getAlbum();
    assertNotNull(album);
    assertEquals("0sNOF9WDwhWunNAHPD3Baj", album.getId());
  }
}
