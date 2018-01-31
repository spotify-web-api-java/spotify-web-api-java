package com.wrapper.spotify.requests.data;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.requests.IRequest;

import static com.wrapper.spotify.Assertions.assertHasHeader;

public abstract class AbstractDataTest<T> implements ITest<T> {

  public void assertHasAuthorizationHeader(IRequest request) {
    assertHasHeader(
            request,
            "Authorization",
            "Bearer " + SPOTIFY_API.getAccessToken());
  }
}
