package com.wrapper.spotify.requests.authentication;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.RefreshAccessTokenCredentials;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RefreshAccessTokenRequestTest {

  @Test
  public void shouldRefreshToken() throws Exception {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String refreshToken = "myRefreshToken";

    final Api api = Api
            .builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .refreshToken(refreshToken)
            .build();

    final RefreshAccessTokenRequest request = api
            .refreshAccessToken()
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/authentication/auth-tokens.json"))
            .build();

    RefreshAccessTokenCredentials refreshAccessTokenResponse = request.get();
    assertNotNull(refreshAccessTokenResponse.getExpiresIn());
    assertNotNull(refreshAccessTokenResponse.getAccessToken());
    assertNotNull(refreshAccessTokenResponse.getTokenType());
  }
}
