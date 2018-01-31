package com.wrapper.spotify.requests.authorization;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.requests.IRequest;

import static com.wrapper.spotify.Assertions.assertHasHeader;

public abstract class AbstractAuthorizationTest<T> implements ITest<T> {

  public void assertHasAuthorizationHeader(IRequest request) {
    assertHasHeader(
            request,
            "Authorization",
            "Basic enl1eGhmbzFjNTFiNWh4amswOXgydWh2NW4wc3ZnZDZnOnp1ZGtueXFiaDN3dW5iaGN2Zzl1eXZvN3V3emV1Nm5uZQ==");
  }
}
