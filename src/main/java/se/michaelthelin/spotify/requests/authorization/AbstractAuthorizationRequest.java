package se.michaelthelin.spotify.requests.authorization;

import se.michaelthelin.spotify.Base64;
import se.michaelthelin.spotify.requests.AbstractRequest;

public abstract class AbstractAuthorizationRequest<T> extends AbstractRequest<T> {
  protected AbstractAuthorizationRequest(final Builder<T, ?> builder) {
    super(builder);
  }

  public static abstract class Builder<T, BT extends Builder<T, ?>> extends AbstractRequest.Builder<T, BT> {
    protected Builder(final String clientId, final String clientSecret) {
      super();

      assert (clientId != null);
      assert (clientSecret != null);
      assert (!clientId.equals(""));
      assert (!clientSecret.equals(""));

      setHeader("Authorization", "Basic " + Base64.encode((clientId + ":" + clientSecret).getBytes()));
    }
  }
}
