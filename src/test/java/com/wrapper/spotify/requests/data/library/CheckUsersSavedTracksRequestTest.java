package com.wrapper.spotify.requests.data.library;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CheckUsersSavedTracksRequestTest {

  @Test
  public void shouldCheckContains_Async() throws Exception {
    final String accessToken = "someAccessToken";

    final Api api = new Api.Builder().setAccessToken("AccessToken").build();

    CheckUsersSavedTracksRequest request = api.containsMySavedTracks(
            new String[]{"0udZHhCi7p1YzMlvI4fXoK", "1e1VmyiAuPyM4SHhySP1oU"})
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/library/CheckUsersSavedTracksRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Boolean[]> searchResultFuture = request.getAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Boolean[]>() {
      @Override
      public void onSuccess(Boolean... containsResult) {
        assertFalse(containsResult[0]);
        assertTrue(containsResult[1]);

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
  public void shouldCheckContains_sync() throws Exception {
    final String accessToken = "someAccessToken";

    final Api api = new Api.Builder().setAccessToken("AccessToken").build();

    CheckUsersSavedTracksRequest request = api.containsMySavedTracks(
            new String[]{"0udZHhCi7p1YzMlvI4fXoK", "1e1VmyiAuPyM4SHhySP1oU"})
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/library/CheckUsersSavedTracksRequest.json"))
            .build();

    Boolean[] response = request.get();
    assertFalse(response[0]);
    assertTrue(response[1]);
  }

}
