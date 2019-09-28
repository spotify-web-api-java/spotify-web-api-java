package com.wrapper.spotify.requests.data;

import com.wrapper.spotify.requests.AbstractRequest;

public abstract class AbstractDataRequest<T> extends AbstractRequest<T> {
  protected AbstractDataRequest(final Builder<T, ?> builder) {
    super(builder);
  }

  public static abstract class Builder<T, BuilderType extends Builder<T, ?>> extends AbstractRequest.Builder<T, BuilderType> {
    protected Builder(String accessToken) {
      super();

      assert (accessToken != null);
      assert (!accessToken.equals(""));

      setHeader("Authorization", "Bearer " + accessToken);
    }
  }
}
