package com.wrapper.spotify.requests.data.artists;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.Artist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class ArtistsRequestTest {

  @Test
  public void shouldGetArtistsResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final GetSeveralArtistsRequest request = api.getArtists("0oSGxfWSnnOXhD2fKuz2Gy", "3dBVyJ7JuOMt4GE9607Qin")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("artists.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Artist[]> artistsFuture = request.getAsync();

    Futures.addCallback(artistsFuture, new FutureCallback<Artist[]>() {
      @Override
      public void onSuccess(Artist[] artists) {
        assertEquals(2, artists.length);

        final Artist firstArtist = artists[0];
        final Artist secondArtist = artists[1];

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

    final GetSeveralArtistsRequest request = api.getArtists("0oSGxfWSnnOXhD2fKuz2Gy", "3dBVyJ7JuOMt4GE9607Qin")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("artists.json"))
            .build();

    final Artist[] artists = request.get();

    assertEquals(2, artists.length);

    final Artist firstArtist = artists[0];
    final Artist secondArtist = artists[1];

    assertEquals("0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getId());
    assertEquals("3dBVyJ7JuOMt4GE9607Qin", secondArtist.getId());
  }

}
