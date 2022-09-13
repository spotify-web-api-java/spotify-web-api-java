package se.michaelthelin.spotify.requests.authorization.authorization_code;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.AbstractAuthorizationTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static se.michaelthelin.spotify.Assertions.assertHasBodyParameter;
import static se.michaelthelin.spotify.Assertions.assertHasHeader;

public class AuthorizationCodeRefreshRequestTest extends AbstractAuthorizationTest<AuthorizationCodeCredentials> {

  private final AuthorizationCodeRefreshRequest defaultRequest = ITest.SPOTIFY_API.authorizationCodeRefresh()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/authorization/authorization_code/AuthorizationCodeRefresh.json"))
    .grant_type("refresh_token")
    .refresh_token(ITest.SPOTIFY_API.getRefreshToken())
    .build();

  public AuthorizationCodeRefreshRequestTest() throws Exception {
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
      ITest.SPOTIFY_API.getRefreshToken());
    assertEquals(
      "https://accounts.spotify.com:443/api/token",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
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
  }
}
