package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by doug on 6/16/16.
 */
public class GetRecommendationsRequestTest {

  @Test
  public void shouldGetRecommendationsResult_async() throws Exception {
    final Api api = new Api.Builder().setAccessToken("AccessToken").build();

    final GetRecommendationsRequest request = api.getRecommendations()
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/browse/GetRecommendationsRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Recommendations> tracksFuture = request.getAsync();

    Futures.addCallback(tracksFuture, new FutureCallback<Recommendations>() {
      @Override
      public void onSuccess(Recommendations recommendations) {
        final TrackSimplified[] tracks = recommendations.getTracks();
        assertEquals(10, tracks.length);

        TrackSimplified firstTrack = tracks[0];
        assertEquals("7IXU0WXqnktR0ntaEqzmwR", firstTrack.getId());

        TrackSimplified secondTrack = tracks[1];
        assertEquals("5gWtkdgdyt5bZt9i6n3Kqd", secondTrack.getId());

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
  public void shouldGetRecommendationsResult_sync() throws Exception {
    final Api api = new Api.Builder().setAccessToken("AccessToken").build();

    final GetRecommendationsRequest request = api.getRecommendations()
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/browse/GetRecommendationsRequest.json"))
            .build();

    final TrackSimplified[] tracks = request.get().getTracks();

    assertEquals(10, tracks.length);

    final TrackSimplified firstTrack = tracks[0];
    assertEquals("7IXU0WXqnktR0ntaEqzmwR", firstTrack.getId());

    final TrackSimplified secondTrack = tracks[1];
    assertEquals("5gWtkdgdyt5bZt9i6n3Kqd", secondTrack.getId());
  }
}
