package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.SpotifyProtos.Artist;
import se.michaelthelin.spotify.TestUtil;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class ArtistsRequestTest {

  @Test
  public void shouldGetArtistsResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("artists.json");
    final ArtistsRequest request = api.artists().id("0oSGxfWSnnOXhD2fKuz2Gy", "3dBVyJ7JuOMt4GE9607Qin").httpManager(mockedHttpManager).build();

    final SettableFuture<List<Artist>> artistsFuture = request.getArtistsAsync();

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

  @Test
  public void shouldGetArtistsResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("artists.json");
    final ArtistsRequest request = api.artists().id("0oSGxfWSnnOXhD2fKuz2Gy","3dBVyJ7JuOMt4GE9607Qin").httpManager(mockedHttpManager).build();

    final List<Artist> artists = request.getArtists();

    assertEquals(2, artists.size());

    final Artist firstArtist = artists.get(0);
    final Artist secondArtist = artists.get(1);

    assertEquals("0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getId());
    assertEquals("3dBVyJ7JuOMt4GE9607Qin", secondArtist.getId());
  }

}
