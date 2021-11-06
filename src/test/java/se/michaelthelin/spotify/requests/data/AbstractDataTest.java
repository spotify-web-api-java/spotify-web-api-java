package se.michaelthelin.spotify.requests.data;

import se.michaelthelin.spotify.ITest;

import static se.michaelthelin.spotify.Assertions.assertHasHeader;

public abstract class AbstractDataTest<T> implements ITest<T> {

  public void assertHasAuthorizationHeader(AbstractDataRequest<T> request) {
    assertHasHeader(
      request,
      "Authorization",
      "Bearer " + SPOTIFY_API.getAccessToken());
  }
}
