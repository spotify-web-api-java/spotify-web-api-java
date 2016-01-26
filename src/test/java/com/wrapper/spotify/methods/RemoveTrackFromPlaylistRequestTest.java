package com.wrapper.spotify.methods;


import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.PlaylistTrackPosition;
import com.wrapper.spotify.models.SnapshotResult;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class RemoveTrackFromPlaylistRequestTest
{
    @Test
    public void shouldAddTracksToPlaylist_async() throws Exception {
        final String accessToken = "someAccessToken";

        final Api api = Api.builder().accessToken(accessToken).build();

        final String myUsername = "thelinmichael";
        final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
        final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
        PlaylistTrackPosition playlistTrackPosition1 = new PlaylistTrackPosition("spotify:track:4BYGxv4rxSNcTgT3DsFB9o");
        PlaylistTrackPosition playlistTrackPosition2 = new PlaylistTrackPosition("spotify:track:0BG2iE6McPhmAEKIhfqy1X", new int[]{5});
        final List<PlaylistTrackPosition> tracksToRemove = Arrays.asList(playlistTrackPosition1, playlistTrackPosition2);

        final RemoveTrackFromPlaylistRequest request = api.removeTrackFromPlaylist(myUsername, myPlaylistId, tracksToRemove)
            .snapshotId(snapshotId)
            .httpManager(TestUtil.MockedHttpManager.returningJson("remove-tracks.json"))
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
    public void shouldAddTracksToPlaylist_sync() throws Exception {
        final String accessToken = "someAccessToken";

        final Api api = Api.builder().accessToken(accessToken).build();

        final String myUsername = "thelinmichael";
        final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
        final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
        PlaylistTrackPosition playlistTrackPosition1 = new PlaylistTrackPosition("spotify:track:4BYGxv4rxSNcTgT3DsFB9o");
        PlaylistTrackPosition playlistTrackPosition2 = new PlaylistTrackPosition("spotify:track:0BG2iE6McPhmAEKIhfqy1X", new int[]{5});
        final List<PlaylistTrackPosition> tracksToRemove = Arrays.asList(playlistTrackPosition1, playlistTrackPosition2);

        final RemoveTrackFromPlaylistRequest request = api.removeTrackFromPlaylist(myUsername, myPlaylistId, tracksToRemove)
            .snapshotId(snapshotId)
            .httpManager(TestUtil.MockedHttpManager.returningJson("remove-tracks.json"))
            .build();

        final SnapshotResult snapshotResult = request.get();
        assertEquals(snapshotId, snapshotResult.getSnapshotId());
    }
}
