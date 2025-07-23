package se.michaelthelin.spotify.requests.authorization;

import se.michaelthelin.spotify.Base64;
import se.michaelthelin.spotify.requests.AbstractRequest;

/**
 * Abstract base class for authorization requests.
 *
 * @param <T> The response type.
 */
public abstract class AbstractAuthorizationRequest<T> extends AbstractRequest<T> {
  /**
   * Protected constructor for authorization requests.
   *
   * @param builder The builder instance.
   */
  protected AbstractAuthorizationRequest(final Builder<T, ?> builder) {
    super(builder);
  }

  /**
   * Abstract builder class for authorization requests.
   *
   * @param <T>  The response type.
   * @param <BT> The builder type.
   */
  public static abstract class Builder<T, BT extends Builder<T, ?>> extends AbstractRequest.Builder<T, BT> {
    /**
     * Protected constructor for authorization request builders.
     *
     * @param clientId     The client ID.
     * @param clientSecret The client secret.
     */
    protected Builder(final String clientId, final String clientSecret) {
      super();

      assert (clientId != null);
      assert (clientSecret != null);
      assert (!clientId.isEmpty());
      assert (!clientSecret.isEmpty());

      setHeader("Authorization", "Basic " + Base64.encode((clientId + ":" + clientSecret).getBytes()));
    }
  }
}
