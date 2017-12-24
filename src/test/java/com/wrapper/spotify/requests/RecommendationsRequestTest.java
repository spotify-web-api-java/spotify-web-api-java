package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.Track;
import com.wrapper.spotify.model_objects.TrackSimplified;
import com.wrapper.spotify.requests.RecommendationsRequest;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Created by doug on 6/16/16.
 */
public class RecommendationsRequestTest {

    @Test
    public void shouldGetRecommendationsResult_async() throws Exception {
        final Api api = Api.DEFAULT_API;

        final RecommendationsRequest request = api.getRecommendations()
                .setHttpManager(TestUtil.MockedHttpManager.returningJson("recommendations.json"))
                .build();

        final CountDownLatch asyncCompleted = new CountDownLatch(1);

        final SettableFuture<TrackSimplified[]> tracksFuture = request.getAsync();

        Futures.addCallback(tracksFuture, new FutureCallback<TrackSimplified[]>() {
            @Override
            public void onSuccess(TrackSimplified[] tracks) {
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
        final Api api = Api.DEFAULT_API;

        final RecommendationsRequest request = api.getRecommendations()
                .setHttpManager(TestUtil.MockedHttpManager.returningJson("recommendations.json"))
                .build();

        final TrackSimplified[] tracks = request.get();

        assertEquals(10, tracks.length);

        final TrackSimplified firstTrack = tracks[0];
        assertEquals("7IXU0WXqnktR0ntaEqzmwR", firstTrack.getId());

        final TrackSimplified secondTrack = tracks[1];
        assertEquals("5gWtkdgdyt5bZt9i6n3Kqd", secondTrack.getId());
    }
}
