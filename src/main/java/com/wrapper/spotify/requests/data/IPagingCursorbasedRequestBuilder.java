package com.wrapper.spotify.requests.data;

import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import com.wrapper.spotify.requests.IRequest;

public interface IPagingCursorbasedRequestBuilder<T, A, Builder> extends IRequest.Builder<PagingCursorbased<T>, Builder> {
  Builder limit(final Integer limit);

  Builder after(final A after);
}
