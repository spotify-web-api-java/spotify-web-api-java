package com.wrapper.spotify.requests.authorization.authorization_code;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.AbstractAuthorizationTest;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.wrapper.spotify.Assertions.assertHasBodyParameter;
import static com.wrapper.spotify.Assertions.assertHasHeader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AuthorizationCodeRefreshRevokedRequestTest extends AbstractAuthorizationTest<AuthorizationCodeCredentials> {

  private final AuthorizationCodeRefreshRequest defaultRequest = SPOTIFY_API.authorizationCodeRefresh()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/authorization/authorization_code/AuthorizationCodeRefreshRevoked.json"))
          .grant_type("refresh_token")
          .refresh_token(SPOTIFY_API.getRefreshToken())
          .build();

  public AuthorizationCodeRefreshRevokedRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/x-www-form-urlencoded");
    assertHasBodyParameter(
            defaultRequest,
            "grant_type",
            "refresh_token");
    assertHasBodyParameter(
            defaultRequest,
            "refresh_token",
            SPOTIFY_API.getRefreshToken());
    assertEquals(
            "https://accounts.spotify.com:443/api/token",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((AuthorizationCodeCredentials) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final AuthorizationCodeCredentials authorizationCodeCredentials) {
    assertTrue(authorizationCodeCredentials.isRevoked());
    assertNull(authorizationCodeCredentials.getRefreshToken());
  }
}
