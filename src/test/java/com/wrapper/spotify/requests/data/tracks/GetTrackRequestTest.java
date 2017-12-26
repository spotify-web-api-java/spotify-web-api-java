package com.wrapper.spotify.requests.data.tracks;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.Track;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class GetTrackRequestTest {

  @Test
  public void shouldGetTrackResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final GetTrackRequest request = api.getTrack("0eGsygTp906u18L0Oimnem")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("GetTrackRequest.json"))
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

    final GetTrackRequest request = api.getTrack("0eGsygTp906u18L0Oimnem")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("GetTrackRequest.json"))
            .build();

    final Track track = request.get();

    assertNotNull(track);
    assertEquals("0eGsygTp906u18L0Oimnem", track.getId());
  }
}
