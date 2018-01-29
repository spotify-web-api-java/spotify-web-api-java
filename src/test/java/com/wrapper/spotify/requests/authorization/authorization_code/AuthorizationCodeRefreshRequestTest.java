package com.wrapper.spotify.requests.authorization.authorization_code;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class AuthorizationCodeRefreshRequestTest implements ITest<AuthorizationCodeCredentials> {

  private final AuthorizationCodeRefreshRequest successRequest = SPOTIFY_API.authorizationCodeRefresh()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/authorization/authorization_code/AuthorizationCodeRefresh.json"))
          .build();

  public AuthorizationCodeRefreshRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((AuthorizationCodeCredentials) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final AuthorizationCodeCredentials authorizationCodeCredentials) {
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
