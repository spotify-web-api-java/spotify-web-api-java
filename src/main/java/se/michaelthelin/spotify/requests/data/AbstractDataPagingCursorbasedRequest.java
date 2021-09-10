package se.michaelthelin.spotify.requests.data;

import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;

public abstract class AbstractDataPagingCursorbasedRequest<T> extends AbstractDataRequest<T> {
  protected AbstractDataPagingCursorbasedRequest(final AbstractDataRequest.Builder<T, ?> builder) {
    super(builder);
  }

  public static abstract class Builder<T, A, BT extends Builder<T, A, ?>> extends AbstractDataRequest.Builder<PagingCursorbased<T>, BT> implements IPagingCursorbasedRequestBuilder<T, A, BT> {
    protected Builder(String accessToken) {
      super(accessToken);

      assert (!accessToken.equals(""));

      setHeader("Authorization", "Bearer " + accessToken);
    }
  }
}
