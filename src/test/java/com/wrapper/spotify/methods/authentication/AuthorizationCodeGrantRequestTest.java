package com.wrapper.spotify.methods.authentication;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.AuthorizationCodeCredentials;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class AuthorizationCodeGrantRequestTest {

  @Test
  public void shouldGetTokenResponse_sync() throws Exception {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String redirectUri = "myRedirectUri";
    final String code = "myCode";

    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectURI(redirectUri)
            .build();

    final AuthorizationCodeGrantRequest request = api.authorizationCodeGrant(code)
            .httpManager(TestUtil.MockedHttpManager.returningJson("auth-tokens.json"))
            .build();
    try {
      final AuthorizationCodeCredentials tokens = request.get();
      assertEquals("BQBY2M94xNVE_7p7x1MhNd2I1UNs62cv-CVDXkDwh5YqSiKJceKRXwJfUrLmJFKO7GfiCZKTh8oEEj3b84bZx1Qy52qwGYCVhX6yHPJY4VDday-hC1YMPOWyIt9Bp05UuJb673btr6T1YOd0DliheWDyqQ", tokens.getAccessToken());
      assertEquals("Bearer", tokens.getTokenType());
      assertEquals(3600, tokens.getExpiresIn());
      assertEquals("AQAZ54v-sV7LO_R64q76KtDMKeQcPkBIPAuKFqYr1kSAeaU8_S8ZxbnqcNizeQiSJr5DhMsJvCdgS7_KUrHd7rw1z7h_FJkL5OVOnthZrNFdO5NL7gUvNJRF6hdbIkAnEHM", tokens.getRefreshToken());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

}
