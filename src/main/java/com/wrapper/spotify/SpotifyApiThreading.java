package com.wrapper.spotify;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SpotifyApiThreading {

  public static final ExecutorService THREADPOOL = Executors.newCachedThreadPool();

  public static <T> Future<T> executeAsync(final Callable<T> callable) {
    return SpotifyApiThreading.THREADPOOL.submit(new Callable<T>() {
      public T call() throws Exception {
        return callable.call();
      }
    });
  }

}

