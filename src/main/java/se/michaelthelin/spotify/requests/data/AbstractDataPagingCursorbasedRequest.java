package se.michaelthelin.spotify.requests.data;

import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;

/**
 * Abstract base class for cursor-based paging data requests.
 *
 * @param <T> The response type.
 */
public abstract class AbstractDataPagingCursorbasedRequest<T> extends AbstractDataRequest<T> {
  /**
   * Protected constructor for cursor-based paging data requests.
   *
   * @param builder The builder instance.
   */
  protected AbstractDataPagingCursorbasedRequest(final AbstractDataRequest.Builder<T, ?> builder) {
    super(builder);
  }

  /**
   * Abstract builder class for cursor-based paging data requests.
   *
   * @param <T>  The response type.
   * @param <A>  The cursor type.
   * @param <BT> The builder type.
   */
  public static abstract class Builder<T, A, BT extends Builder<T, A, ?>> extends AbstractDataRequest.Builder<PagingCursorbased<T>, BT> implements IPagingCursorbasedRequestBuilder<T, A, BT> {
    /**
     * Protected constructor for cursor-based paging data request builders.
     *
     * @param accessToken The access token.
     */
    protected Builder(String accessToken) {
      super(accessToken);

      assert (!accessToken.isEmpty());

      setHeader("Authorization", "Bearer " + accessToken);
    }
  }
}
