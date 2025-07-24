package se.michaelthelin.spotify;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Threading utilities for the Spotify API.
 */
public class SpotifyApiThreading {

  /** The thread pool used for async execution. */
  public static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

  /**
   * Default constructor.
   */
  public SpotifyApiThreading() {
    super();
  }

  /**
   * Execute a callable asynchronously.
   *
   * @param <T>      The return type.
   * @param callable The callable to execute.
   * @return A CompletableFuture for the result.
   */
  public static <T> CompletableFuture<T> executeAsync(final Callable<T> callable) {
    CompletableFuture<T> future = new CompletableFuture<>();

    SpotifyApiThreading.THREAD_POOL.execute(() -> {
      try {
        future.complete(callable.call());
      } catch (Exception e) {
        future.completeExceptionally(e);
      }
    });

    return future;
  }

}

