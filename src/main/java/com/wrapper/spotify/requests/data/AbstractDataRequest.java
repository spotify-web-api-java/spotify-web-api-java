package com.wrapper.spotify.requests.data;

import com.wrapper.spotify.requests.AbstractRequest;

public abstract class AbstractDataRequest extends AbstractRequest {
  protected AbstractDataRequest(final Builder builder) {
    super(builder);
  }

  public static abstract class Builder<BuilderType extends Builder<?>> extends AbstractRequest.Builder<BuilderType> {
    protected Builder(String accessToken) {
      super();

      assert (accessToken != null);
      assert (!accessToken.equals(""));

      setHeader("Authorization", "Bearer " + accessToken);
    }
  }
}
