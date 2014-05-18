package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.TestConfiguration;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.models.Artist;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class ArtistsRequestTest {

  @Test
  public void shouldGetArtistsResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final ArtistsRequest.Builder requestBuilder = api.getArtists("0oSGxfWSnnOXhD2fKuz2Gy", "3dBVyJ7JuOMt4GE9607Qin");
    if (TestConfiguration.USE_MOCK_RESPONSES) {
      requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("artists.json"));
    }
    final ArtistsRequest request = requestBuilder.build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<List<Artist>> artistsFuture = request.getAsync();

    Futures.addCallback(artistsFuture, new FutureCallback<List<Artist>>() {
      @Override
      public void onSuccess(List<Artist> artists) {
        assertEquals(2, artists.size());

        final Artist firstArtist = artists.get(0);
        final Artist secondArtist = artists.get(1);

        assertEquals("0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getId());
        assertEquals("3dBVyJ7JuOMt4GE9607Qin", secondArtist.getId());

        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetArtistsResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final ArtistsRequest.Builder requestBuilder = api.getArtists("0oSGxfWSnnOXhD2fKuz2Gy", "3dBVyJ7JuOMt4GE9607Qin");
    if (TestConfiguration.USE_MOCK_RESPONSES) {
      requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("artists.json"));
    }
    final ArtistsRequest request = requestBuilder.build();

    final List<Artist> artists = request.get();

    assertEquals(2, artists.size());

    final Artist firstArtist = artists.get(0);
    final Artist secondArtist = artists.get(1);

    assertEquals("0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getId());
    assertEquals("3dBVyJ7JuOMt4GE9607Qin", secondArtist.getId());
  }

}
