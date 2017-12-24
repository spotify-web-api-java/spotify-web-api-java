package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.SnapshotResult;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class ReorderTracksInPlaylistRequestTest
{
    @Test
    public void shouldReorderTracksInPlaylist_async() throws Exception {
        final String accessToken = "someAccessToken";

        final Api api = Api.builder().accessToken(accessToken).build();

        final String myUsername = "thelinmichael";
        final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
        final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
        final int rangeStart = 10;
        final int rangeLength = 2;
        final int insertBefore = 5;

        final ReorderTracksInPlaylistRequest request = api.reorderTracksInPlaylist(myUsername, myPlaylistId, rangeStart, insertBefore)
            .rangeLength(rangeLength)
            .snapshotId(snapshotId)
            .httpManager(TestUtil.MockedHttpManager.returningJson("reorder-tracks.json"))
            .build();

        final CountDownLatch asyncCompleted = new CountDownLatch(1);

        final SettableFuture<SnapshotResult> addTrackFuture = request.getAsync();

        Futures.addCallback(addTrackFuture, new FutureCallback<SnapshotResult>() {
            @Override
            public void onSuccess(SnapshotResult response) {
                assertEquals(snapshotId, response.getSnapshotId());
                asyncCompleted.countDown();
            }

            @Override
            public void onFailure(Throwable throwable) {
                fail("Failed to resolve future: " + throwable.getMessage());
            }
        });

        asyncCompleted.await(1, TimeUnit.SECONDS);
    }

    @Test
    public void shouldReorderTracksInPlaylist_sync() throws Exception {
        final String accessToken = "someAccessToken";

        final Api api = Api.builder().accessToken(accessToken).build();

        final String myUsername = "thelinmichael";
        final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
        final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
        final int rangeStart = 10;
        final int rangeLength = 2;
        final int insertBefore = 5;

        final ReorderTracksInPlaylistRequest request = api.reorderTracksInPlaylist(myUsername, myPlaylistId, rangeStart, insertBefore)
            .rangeLength(rangeLength)
            .snapshotId(snapshotId)
            .httpManager(TestUtil.MockedHttpManager.returningJson("reorder-tracks.json"))
            .build();

        final SnapshotResult snapshotResult = request.get();
        assertEquals(snapshotId, snapshotResult.getSnapshotId());
    }
}
