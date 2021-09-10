package se.michaelthelin.spotify.requests.data;

import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;
import se.michaelthelin.spotify.requests.IRequest;

public interface IPagingCursorbasedRequestBuilder<T, A, BT extends IRequest.Builder<PagingCursorbased<T>, ?>>
  extends IRequest.Builder<PagingCursorbased<T>, BT> {
  BT limit(final Integer limit);

  BT after(final A after);
}
