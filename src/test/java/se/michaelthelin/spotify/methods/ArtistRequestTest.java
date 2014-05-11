package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.models.Artist;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class ArtistRequestTest {

  @Test
  public void shouldGetArtistResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("artist.json");
    final ArtistRequest request = api.getArtist("0LcJLqbBmaGUft1e9Mm8HV").httpManager(mockedHttpManager).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Artist> artistFuture = request.getAsync();

    Futures.addCallback(artistFuture, new FutureCallback<Artist>() {
      @Override
      public void onSuccess(Artist artist) {
        assertNotNull(artist);
        assertEquals("0LcJLqbBmaGUft1e9Mm8HV", artist.getId());
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });
  }

  @Test
  public void shouldGetArtistResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("artist.json");
    final ArtistRequest request = api.getArtist("0LcJLqbBmaGUft1e9Mm8HV").httpManager(mockedHttpManager).build();

    final Artist artist = request.get();

    assertNotNull(artist);
    assertEquals("0LcJLqbBmaGUft1e9Mm8HV", artist.getId());
  }

}
