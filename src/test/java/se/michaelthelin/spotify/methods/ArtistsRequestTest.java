package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos.Artist;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;
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

    ListenableFuture<List<Artist>> artistsFuture = spy.getArtists();

    Futures.addCallback(artistsFuture, new FutureCallback<List<Artist>>() {
      @Override
      public void onSuccess(List<Artist> artists) {
        assertEquals(2, artists.size());

        Artist firstArtist = artists.get(0);
        Artist secondArtist = artists.get(1);

        assertEquals("0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getId());
        assertEquals("3dBVyJ7JuOMt4GE9607Qin", secondArtist.getId());
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });
  }

}
