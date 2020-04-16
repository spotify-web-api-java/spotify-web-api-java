package com.wrapper.spotify.requests.data;

import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import com.wrapper.spotify.requests.IRequest;

public interface IPagingCursorbasedRequestBuilder<T, A, BT extends IRequest.Builder<PagingCursorbased<T>, ?>>
  extends IRequest.Builder<PagingCursorbased<T>, BT> {
  BT limit(final Integer limit);

  BT after(final A after);
}
