package se.michaelthelin.spotify.requests.data;

import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.IRequest;

/**
 * Interface for request builders that support paging functionality.
 *
 * @param <T> the type of objects contained in the paging result
 * @param <BT> the specific builder type extending this interface
 */
public interface IPagingRequestBuilder<T, BT extends IRequest.Builder<Paging<T>, ?>>
  extends IRequest.Builder<Paging<T>, BT> {
  
  /**
   * Sets the maximum number of items to return in the response.
   *
   * @param limit the maximum number of items to return (1-50)
   * @return this builder instance for method chaining
   */
  BT limit(final Integer limit);

  /**
   * Sets the index of the first item to return.
   *
   * @param offset the offset index (0-based)
   * @return this builder instance for method chaining
   */
  BT offset(final Integer offset);
}
