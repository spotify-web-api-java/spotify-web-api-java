package com.wrapper.spotify;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpotifyApiThreading {

  public static final ExecutorService THREADPOOL = Executors.newCachedThreadPool();

  public static <T> CompletableFuture<T> executeAsync(final Callable<T> callable) {
    CompletableFuture<T> future = new CompletableFuture<>();

    SpotifyApiThreading.THREADPOOL.execute(() -> {
      try {
        future.complete(callable.call());
      } catch (Exception e) {
        future.completeExceptionally(e);
      }
    });

    return future;
  }

}

