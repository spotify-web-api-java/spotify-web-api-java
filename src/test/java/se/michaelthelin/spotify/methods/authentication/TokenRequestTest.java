package se.michaelthelin.spotify.methods.authentication;

import org.junit.Test;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.TokenResponse;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class TokenRequestTest {

  @Test
  public void shouldGetTokenResponse() throws IOException, UnexpectedResponseException {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String redirectUri = "myRedirectUri";
    final String code = "myCode";

    final Api api = Api.DEFAULT_API;

    final TokenRequest.Builder requestBuilder = api
            .getTokens()
            .authorizationHeader(clientId, clientSecret)
            .redirectUri(redirectUri)
            .code(code)
            .grantType("authorization_code")
            .httpManager(TestUtil.MockedHttpManager.returningJson("auth-tokens.json"));
    final TokenRequest request = requestBuilder.build();

    try {
      final TokenResponse tokens = request.post();
      assertEquals("BQBY2M94xNVE_7p7x1MhNd2I1UNs62cv-CVDXkDwh5YqSiKJceKRXwJfUrLmJFKO7GfiCZKTh8oEEj3b84bZx1Qy52qwGYCVhX6yHPJY4VDday-hC1YMPOWyIt9Bp05UuJb673btr6T1YOd0DliheWDyqQ", tokens.getAccessToken());
      assertEquals("Bearer", tokens.getTokenType());
      assertEquals(3600, tokens.getExpiresIn());
      assertEquals("AQAZ54v-sV7LO_R64q76KtDMKeQcPkBIPAuKFqYr1kSAeaU8_S8ZxbnqcNizeQiSJr5DhMsJvCdgS7_KUrHd7rw1z7h_FJkL5OVOnthZrNFdO5NL7gUvNJRF6hdbIkAnEHM", tokens.getRefreshtoken());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

}
