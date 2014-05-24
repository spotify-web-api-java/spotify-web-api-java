package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.fail;

// Todo: Add negative test cases.
public class AddTrackToPlaylistRequestTest {

  @Test
  public void shouldAddTracksToPlaylist_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final String accessToken = "myAccessToken";
    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final List<String> tracksToAdd = Arrays.asList("spotify:track:4BYGxv4rxSNcTgT3DsFB9o","spotify:track:0BG2iE6McPhmAEKIhfqy1X");
    final int insertIndex = 3;

    final AddTrackToPlaylistRequest request = api.addTracksToPlaylist(myPlaylistId, myUsername)
            .tracks(tracksToAdd)
            .position(insertIndex)
            .httpManager(TestUtil.MockedHttpManager.returningString(""))
            .accessToken(accessToken)
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<String> addTrackFuture = request.getAsync();

    Futures.addCallback(addTrackFuture, new FutureCallback<String>() {
      @Override
      public void onSuccess(String response) {
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future: " + throwable.getMessage());
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }
}
