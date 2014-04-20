package se.michaelthelin.spotify.methods;

import org.junit.Test;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.SpotifyProtos.Track;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

public class SearchRequestTest {

  @Test
  public void test() {
    Api api = Api.DEFAULT_API;
    SearchRequest request = api.search().query("elvis").type("track").build();
    List<Track> tracks = request.getTracks();
    assertNotNull(tracks);
  }
}
