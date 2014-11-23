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

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class RemoveFromMySavedTracksRequestTest {

  @Test
  public void removeFromMySavedTracks_async() throws Exception {
    final String accessToken = "accessToken";

    final Api api = Api.builder().accessToken(accessToken)
        .httpManager(TestUtil.MockedHttpManager.returningString(""))
        .build();

    final List<String> tracksToAdd = Arrays.asList("5xFF6wNcoRwx7N3cDTgVWP", "13zm8XhfM4RBtQpjdqY44e");

    final RemoveFromMySavedTracksRequest request = api.removeFromMySavedTracks(tracksToAdd).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<String> removeTrackFuture = request.getAsync();

    Futures.addCallback(removeTrackFuture, new FutureCallback<String>() {

      @Override
      public void onSuccess(String response) {
        assertEquals("", response);

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
  public void removeFromMySavedTracks_sync() throws Exception {
    final String accessToken = "accessToken";

    final Api api = Api.builder().accessToken(accessToken)
        .httpManager(TestUtil.MockedHttpManager.returningString(""))
        .build();

    final List<String> tracksToAdd = Arrays.asList("5xFF6wNcoRwx7N3cDTgVWP", "13zm8XhfM4RBtQpjdqY44e");

    final RemoveFromMySavedTracksRequest request = api.removeFromMySavedTracks(tracksToAdd).build();

    String response = request.get();
    assertEquals("", response);
  }

}
