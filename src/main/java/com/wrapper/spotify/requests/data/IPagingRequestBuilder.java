package com.wrapper.spotify.requests.data;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.IRequest;

public interface IPagingRequestBuilder<T, Builder> extends IRequest.Builder<Paging<T>, Builder> {
  Builder limit(final Integer limit);

  Builder offset(final Integer offset);
}
