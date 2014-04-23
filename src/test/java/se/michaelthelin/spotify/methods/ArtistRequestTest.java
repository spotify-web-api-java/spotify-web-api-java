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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistRequestTest {

  @Test
  public void shouldGetArtistResult_async() throws Exception {
    Api api = Api.DEFAULT_API;
    ArtistRequest request = api.artist().id("0LcJLqbBmaGUft1e9Mm8HV").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("artist.json");
    ArtistRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    ListenableFuture<Artist> artistFuture = spy.getArtistAsync();

    Futures.addCallback(artistFuture, new FutureCallback<Artist>() {
      @Override
      public void onSuccess(Artist artist) {
        assertNotNull(artist);
        assertEquals("0LcJLqbBmaGUft1e9Mm8HV", artist.getId());
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });
  }

  @Test
  public void shouldGetArtistResult_sync() throws Exception {
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
