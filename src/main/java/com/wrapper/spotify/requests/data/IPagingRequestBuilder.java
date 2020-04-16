package com.wrapper.spotify.requests.data;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.IRequest;

public interface IPagingRequestBuilder<T, BT extends IRequest.Builder<Paging<T>, ?>>
  extends IRequest.Builder<Paging<T>, BT> {
  BT limit(final Integer limit);

  BT offset(final Integer offset);
}
