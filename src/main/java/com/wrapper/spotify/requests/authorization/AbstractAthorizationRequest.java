package com.wrapper.spotify.requests.authorization;

import com.wrapper.spotify.requests.AbstractRequest;

import javax.xml.bind.DatatypeConverter;

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

      setHeader("Authorization", "Basic " + DatatypeConverter.printBase64Binary((clientId + ":" + clientSecret).getBytes()));
    }
  }
}
