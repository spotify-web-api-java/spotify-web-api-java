package com.wrapper.spotify.requests.data;

import com.wrapper.spotify.model_objects.specification.PagingCursorbased;

public abstract class AbstractDataPagingCursorbasedRequest<T> extends AbstractDataRequest<T> {
  protected AbstractDataPagingCursorbasedRequest(final AbstractDataRequest.Builder<T, ?> builder) {
    super(builder);
  }

  public static abstract class Builder<T, A, BuilderType extends Builder<T, A, ?>> extends AbstractDataRequest.Builder<PagingCursorbased<T>, BuilderType> implements IPagingCursorbasedRequestBuilder<T, A, BuilderType> {
    protected Builder(String accessToken) {
      super(accessToken);

      assert (!accessToken.equals(""));

      setHeader("Authorization", "Bearer " + accessToken);
    }
  }
}
