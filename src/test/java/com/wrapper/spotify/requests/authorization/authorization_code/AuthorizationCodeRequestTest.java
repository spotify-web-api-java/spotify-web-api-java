package com.wrapper.spotify.requests.authorization.authorization_code;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AuthorizationCodeRequestTest {

  @Test
  public void shouldGetTokenResponse_sync() throws Exception {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String redirectUri = "myRedirectUri";
    final String code = "myCode";

    final SpotifyApi api = SpotifyApi.builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();

    final AuthorizationCodeRequest request = api.authorizationCode(code)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/authorization/authorization_code/AuthorizationCode.json"))
            .build();
    try {
      final AuthorizationCodeCredentials tokens = request.execute();
      assertEquals("BQBY2M94xNVE_7p7x1MhNd2I1UNs62cv-CVDXkDwh5YqSiKJceKRXwJfUrLmJFKO7GfiCZKTh8oEEj3b84bZx1Qy52qwGYCVhX6yHPJY4VDday-hC1YMPOWyIt9Bp05UuJb673btr6T1YOd0DliheWDyqQ", tokens.getAccessToken());
      assertEquals("Bearer", tokens.getTokenType());
      assertEquals(3600, (int) tokens.getExpiresIn());
      assertEquals("AQAZ54v-sV7LO_R64q76KtDMKeQcPkBIPAuKFqYr1kSAeaU8_S8ZxbnqcNizeQiSJr5DhMsJvCdgS7_KUrHd7rw1z7h_FJkL5OVOnthZrNFdO5NL7gUvNJRF6hdbIkAnEHM", tokens.getRefreshToken());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
