package se.michaelthelin.spotify.requests.authorization.authorization_code.pkce;


import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static se.michaelthelin.spotify.Assertions.assertHasBodyParameter;
import static se.michaelthelin.spotify.Assertions.assertHasHeader;

public class AuthorizationCodePKCERefreshRequestTest implements ITest<AuthorizationCodeCredentials> {

  private final AuthorizationCodePKCERefreshRequest defaultRequest = SPOTIFY_API.authorizationCodePKCERefresh()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/authorization/authorization_code/pkce/AuthorizationCodePKCERefresh.json"))
    .build();

  public AuthorizationCodePKCERefreshRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasHeader(defaultRequest, "Content-Type", "application/x-www-form-urlencoded");
    assertHasBodyParameter(
      defaultRequest,
      "grant_type",
      "refresh_token");
    assertHasBodyParameter(
      defaultRequest,
      "refresh_token",
      SPOTIFY_API.getRefreshToken());
    assertHasBodyParameter(
      defaultRequest,
      "client_id",
      SPOTIFY_API.getClientId());
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
      "9Cysa896KySJLrEcasloD1Gufy9iSq7Wa-K2SbSKwK3rXfizi4GwIS2RCrBmCMsKfkTDm82ez9m47WZ8egFCuRPs4BgEHw",
      authorizationCodeCredentials.getAccessToken());
    assertEquals(
      "Bearer",
      authorizationCodeCredentials.getTokenType());
    assertEquals(
      "user-follow-modify",
      authorizationCodeCredentials.getScope());
    assertEquals(
      3600,
      (int) authorizationCodeCredentials.getExpiresIn());
    assertEquals(
      "PoO04alC_uRJoyd2MLhN53hHv2-sDAJs5mULPPzLW0lgdXXAvZAWEJrBqqd6NfCE4FZo7TcuKXp4grmE-9fKyMaP6zl6g",
      authorizationCodeCredentials.getRefreshToken());
  }
}
