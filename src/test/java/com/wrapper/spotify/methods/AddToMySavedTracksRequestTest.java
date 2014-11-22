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

public class AddToMySavedTracksRequestTest {

  @Test
  public void shouldAddToMySavedTracks_async() throws Exception {
    final String accessToken = "someAccessToken";

    final Api api = Api.builder().accessToken(accessToken).build();

    final List<String>
        tracksToAdd = Arrays.asList("4BYGxv4rxSNcTgT3DsFB9o", "0BG2iE6McPhmAEKIhfqy1X");

    final AddToMySavedTracksRequest request = api.addToMySavedTracks(tracksToAdd)
      .httpManager(TestUtil.MockedHttpManager.returningString(""))
      .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<String> addTrackFuture = request.getAsync();

    Futures.addCallback(addTrackFuture, new FutureCallback<String>() {

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
  public void shouldAddToMySavedTracks_sync() throws Exception {
    final String accessToken = "someAccessToken";

    final Api api = Api.builder().accessToken(accessToken).build();

    final List<String>
        tracksToAdd = Arrays.asList("4BYGxv4rxSNcTgT3DsFB9o", "0BG2iE6McPhmAEKIhfqy1X");

    final AddToMySavedTracksRequest request = api.addToMySavedTracks(tracksToAdd)
        .httpManager(TestUtil.MockedHttpManager.returningString(""))
        .build();

    final String addTrackResponse = request.get();

    assertEquals("", addTrackResponse);
  }

}
