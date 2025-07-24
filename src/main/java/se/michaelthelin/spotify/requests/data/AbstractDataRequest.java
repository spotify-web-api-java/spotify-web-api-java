package se.michaelthelin.spotify.requests.data;

import se.michaelthelin.spotify.requests.AbstractRequest;

/**
 * Abstract base class for data requests.
 *
 * @param <T> The response type.
 */
public abstract class AbstractDataRequest<T> extends AbstractRequest<T> {
  /**
   * Protected constructor for data requests.
   *
   * @param builder The builder instance.
   */
  protected AbstractDataRequest(final Builder<T, ?> builder) {
    super(builder);
  }

  /**
   * Abstract builder class for data requests.
   *
   * @param <T>  The response type.
   * @param <BT> The builder type.
   */
  public static abstract class Builder<T, BT extends Builder<T, ?>> extends AbstractRequest.Builder<T, BT> {
    /**
     * Protected constructor for data request builders.
     *
     * @param accessToken The access token.
     */
    protected Builder(String accessToken) {
      super();

      assert (accessToken != null);
      assert (!accessToken.isEmpty());

      setHeader("Authorization", "Bearer " + accessToken);
    }
  }
}
