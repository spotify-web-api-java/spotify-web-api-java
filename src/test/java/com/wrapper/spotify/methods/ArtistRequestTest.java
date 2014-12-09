package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.Artist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class ArtistRequestTest {

  @Test
  public void shouldGetArtistResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    ArtistRequest request = api.getArtist("0LcJLqbBmaGUft1e9Mm8HV")
        .httpManager(TestUtil.MockedHttpManager.returningJson("artist.json"))
        .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Artist> artistFuture = request.getAsync();

    Futures.addCallback(artistFuture, new FutureCallback<Artist>() {
      @Override
      public void onSuccess(Artist artist) {
        assertNotNull(artist);
        assertEquals("https://open.spotify.com/artist/0LcJLqbBmaGUft1e9Mm8HV", artist.getExternalUrls().get("spotify"));
        assertNotNull(artist.getGenres());
        assertEquals("0LcJLqbBmaGUft1e9Mm8HV", artist.getId());
        assertNotNull(artist.getImages());
        assertEquals("ABBA", artist.getName());
        assertTrue(artist.getPopularity() >= 0 && artist.getPopularity() <= 100);
        assertEquals("spotify:artist:0LcJLqbBmaGUft1e9Mm8HV", artist.getUri());

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
    ArtistRequest request = api.getArtist("0LcJLqbBmaGUft1e9Mm8HV")
        .httpManager(TestUtil.MockedHttpManager.returningJson("artist.json"))
        .build();

    final Artist artist = request.get();

    assertNotNull(artist);
    assertEquals("https://open.spotify.com/artist/0LcJLqbBmaGUft1e9Mm8HV", artist.getExternalUrls().get("spotify"));
    assertNotNull(artist.getGenres());
    assertEquals("0LcJLqbBmaGUft1e9Mm8HV", artist.getId());
    assertNotNull(artist.getImages());
    assertEquals("ABBA", artist.getName());
    assertTrue(artist.getPopularity() >= 0 && artist.getPopularity() <= 100);
    assertEquals("spotify:artist:0LcJLqbBmaGUft1e9Mm8HV", artist.getUri());
  }

}
