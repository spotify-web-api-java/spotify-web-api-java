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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;


@RunWith(MockitoJUnitRunner.class)
public class TrackRequestTest {

  @Test
  public void shouldGetTrackResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final TrackRequest request = api.getTrack("0eGsygTp906u18L0Oimnem")
        .httpManager(TestUtil.MockedHttpManager.returningJson("track.json"))
        .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Track> trackFuture = request.getAsync();

    Futures.addCallback(trackFuture, new FutureCallback<Track>() {
      @Override
      public void onSuccess(Track track) {
        assertNotNull(track);
        assertEquals("0eGsygTp906u18L0Oimnem", track.getId());

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
  public void shouldGetTrackResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final TrackRequest request = api.getTrack("0eGsygTp906u18L0Oimnem")
        .httpManager(TestUtil.MockedHttpManager.returningJson("track.json"))
        .build();

    final Track track = request.get();

    assertNotNull(track);
    assertEquals("0eGsygTp906u18L0Oimnem", track.getId());
  }
}
