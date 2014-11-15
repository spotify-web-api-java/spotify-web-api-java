package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.Track;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class TracksRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final TracksRequest request = api.getTracks("0eGsygTp906u18L0Oimnem", "1lDWb6b6ieDQ2xT7ewTC3G")
        .httpManager(TestUtil.MockedHttpManager.returningJson("tracks.json"))
        .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<List<Track>> tracksFuture = request.getAsync();

    Futures.addCallback(tracksFuture, new FutureCallback<List<Track>>() {
      @Override
      public void onSuccess(List<Track> tracks) {
        assertEquals(2, tracks.size());

        Track firstTrack = tracks.get(0);
        assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

        Track secondTrack = tracks.get(1);
        assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());

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
  public void shouldGetTracksResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final TracksRequest request = api.getTracks("0eGsygTp906u18L0Oimnem", "1lDWb6b6ieDQ2xT7ewTC3G")
        .httpManager(TestUtil.MockedHttpManager.returningJson("tracks.json"))
        .build();

    final List<Track> tracks = request.get();

    assertEquals(2, tracks.size());

    final Track firstTrack = tracks.get(0);
    assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

    final Track secondTrack = tracks.get(1);
    assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());
  }
}
