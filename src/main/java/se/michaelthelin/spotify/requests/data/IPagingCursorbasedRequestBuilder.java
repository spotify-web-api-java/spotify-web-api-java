package se.michaelthelin.spotify.requests.data;

import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;
import se.michaelthelin.spotify.requests.IRequest;

/**
 * Interface for request builders that support cursor-based paging functionality.
 *
 * @param <T> the type of objects contained in the paging result
 * @param <A> the type of the cursor/after parameter
 * @param <BT> the specific builder type extending this interface
 */
public interface IPagingCursorbasedRequestBuilder<T, A, BT extends IRequest.Builder<PagingCursorbased<T>, ?>>
  extends IRequest.Builder<PagingCursorbased<T>, BT> {
  
  /**
   * Sets the maximum number of items to return in the response.
   *
   * @param limit the maximum number of items to return
   * @return this builder instance for method chaining
   */
  BT limit(final Integer limit);

  /**
   * Sets the cursor position to start returning results from.
   *
   * @param after the cursor value to start returning results after
   * @return this builder instance for method chaining
   */
  BT after(final A after);
}
