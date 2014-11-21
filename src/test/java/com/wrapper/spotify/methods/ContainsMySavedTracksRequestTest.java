/**
 * Copyright (C) 2014 Spotify AB
 */
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

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class ContainsMySavedTracksRequestTest {

  @Test
  public void  shouldCheckContains_Async() throws Exception {
    final String accessToken = "someAccessToken";

    final Api api = Api.builder().accessToken(accessToken).build();

    ContainsMySavedTracksRequest request = api.containsMySavedTracks(
        Arrays.asList("0udZHhCi7p1YzMlvI4fXoK", "1e1VmyiAuPyM4SHhySP1oU"))
        .httpManager(TestUtil.MockedHttpManager.returningJson("yourmusic-contains.json"))
        .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<List<Boolean>> searchResultFuture = request.getAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<List<Boolean>>() {
      @Override
      public void onSuccess(List<Boolean> containsResult) {
        assertFalse(containsResult.get(0));
        assertTrue(containsResult.get(1));

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

    final Api api = Api.builder().accessToken(accessToken).build();

    ContainsMySavedTracksRequest request = api.containsMySavedTracks(
        Arrays.asList("0udZHhCi7p1YzMlvI4fXoK", "1e1VmyiAuPyM4SHhySP1oU"))
        .httpManager(TestUtil.MockedHttpManager.returningJson("yourmusic-contains.json"))
        .build();

    List<Boolean> response = request.get();
    assertFalse(response.get(0));
    assertTrue(response.get(1));
  }

}
