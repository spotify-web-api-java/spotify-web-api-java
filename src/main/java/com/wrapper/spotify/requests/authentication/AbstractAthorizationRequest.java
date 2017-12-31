package com.wrapper.spotify.requests.authentication;

import com.wrapper.spotify.requests.AbstractRequest;
import org.apache.commons.codec.binary.Base64;

public abstract class AbstractAthorizationRequest extends AbstractRequest {
  protected AbstractAthorizationRequest(final Builder builder) {
    super(builder);
  }

  public static abstract class Builder<BuilderType extends Builder<?>> extends AbstractRequest.Builder<BuilderType> {
    protected Builder(final String clientId, final String clientSecret) {
      super();

      assert (clientId != null);
      assert (clientSecret != null);
      assert (!clientId.equals(""));
      assert (!clientSecret.equals(""));

      setHeader("Authorization", "Basic " + Base64.encodeBase64String((clientId + ":" + clientSecret).getBytes()));
    }
  }
}
