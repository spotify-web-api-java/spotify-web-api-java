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

public class AuthorizationCodeRequestTest extends AbstractAuthorizationTest<AuthorizationCodeCredentials> {

  private final AuthorizationCodeRequest defaultRequest = SPOTIFY_API.authorizationCode(AUTHORIZATION_CODE)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/authorization/authorization_code/AuthorizationCode.json"))
          .grant_type("authorization_code")
          .redirect_uri(SPOTIFY_API.getRedirectURI())
          .build();

  public AuthorizationCodeRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/x-www-form-urlencoded");
    assertHasBodyParameter(
            defaultRequest,
            "code",
            AUTHORIZATION_CODE);
    assertHasBodyParameter(
            defaultRequest,
            "grant_type",
            "authorization_code");
    assertHasBodyParameter(
            defaultRequest,
            "redirect_uri",
            SPOTIFY_API.getRedirectURI());
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
    assertEquals(
            "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk",
            authorizationCodeCredentials.getAccessToken());
    assertEquals(
            "Bearer",
            authorizationCodeCredentials.getTokenType());
    assertEquals(
            "user-read-birthdate user-read-email",
            authorizationCodeCredentials.getScope());
    assertEquals(
            3600,
            (int) authorizationCodeCredentials.getExpiresIn());
    assertEquals(
            "b0KuPuLw77Z0hQhCsK-GTHoEx_kethtn357V7iqwEpCTIsLgqbBC_vQBTGC6M5rINl0FrqHK-D3cbOsMOlfyVKuQPvpyGcLcxAoLOTpYXc28nVwB7iBq2oKj9G9lHkFOUKn",
            authorizationCodeCredentials.getRefreshToken());
  }
}
