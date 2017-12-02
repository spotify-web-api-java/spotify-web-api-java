package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.objects.Artist;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.*;

public class RelatedArtistsRequestTest {

  @Test
  public void shouldGetRelatedArtists_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final RelatedArtistsRequest request = api
            .getArtistRelatedArtists("0qeei9KQnptjwb8MgkqEoy")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("related-artists.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Artist[]> artistFuture = request.getAsync();

    Futures.addCallback(artistFuture, new FutureCallback<Artist[]>() {

      @Override
      public void onSuccess(Artist[] artists) {
        assertFalse(artists.length == 0);
        final Artist firstArtist = artists[0];
        final String id = firstArtist.getId();
        assertEquals("https://api.spotify.com/v1/artists/" + id, firstArtist.getHref());
        assertEquals(id, firstArtist.getId());
        assertEquals("spotify:artist:" + id, firstArtist.getUri());
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
  public void shouldGetRelatedArtists_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final RelatedArtistsRequest request = api
            .getArtistRelatedArtists("0qeei9KQnptjwb8MgkqEoy")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("related-artists.json"))
            .build();

    final Artist[] artists = request.get();


    assertFalse(artists.length == 0);
    final Artist firstArtist = artists[0];
    final String id = firstArtist.getId();
    assertEquals("https://api.spotify.com/v1/artists/" + id, firstArtist.getHref());
    assertEquals(id, firstArtist.getId());
    assertEquals("spotify:artist:" + id, firstArtist.getUri());
  }


}
