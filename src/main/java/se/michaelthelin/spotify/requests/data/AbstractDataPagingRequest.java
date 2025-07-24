package se.michaelthelin.spotify.requests.data;

import se.michaelthelin.spotify.model_objects.specification.Paging;

/**
 * Abstract base class for paging data requests.
 *
 * @param <T> The response type.
 */
public abstract class AbstractDataPagingRequest<T> extends AbstractDataRequest<T> {
  /**
   * Protected constructor for paging data requests.
   *
   * @param builder The builder instance.
   */
  protected AbstractDataPagingRequest(final AbstractDataRequest.Builder<T, ?> builder) {
    super(builder);
  }

  /**
   * Abstract builder class for paging data requests.
   *
   * @param <T>  The response type.
   * @param <BT> The builder type.
   */
  public static abstract class Builder<T, BT extends Builder<T, ?>>
    extends AbstractDataRequest.Builder<Paging<T>, BT>
    implements IPagingRequestBuilder<T, BT> {
    /**
     * Protected constructor for paging data request builders.
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
