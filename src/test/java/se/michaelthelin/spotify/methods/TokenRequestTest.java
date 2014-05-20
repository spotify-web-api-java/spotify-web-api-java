package se.michaelthelin.spotify.methods;

import org.junit.Test;
import se.michaelthelin.spotify.AuthenticationApi;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.ErrorResponseException;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.RefreshAccessTokenResponse;
import se.michaelthelin.spotify.models.TokenResponse;

import java.io.IOException;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class TokenRequestTest {

  @Test
  public void shouldGetTokenResponse() throws IOException, UnexpectedResponseException {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String redirectUri = "myRedirectUri";
    final String code = "myCode";

    final AuthenticationApi api = AuthenticationApi.DEFAULT_API;

    final TokenRequest.Builder requestBuilder = api.getTokens();
    requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("auth-tokens.json"));
    final TokenRequest request = requestBuilder.build();

    final TokenResponse tokens;
    try {
      tokens = request.post();
      assertEquals("BQBY2M94xNVE_7p7x1MhNd2I1UNs62cv-CVDXkDwh5YqSiKJceKRXwJfUrLmJFKO7GfiCZKTh8oEEj3b84bZx1Qy52qwGYCVhX6yHPJY4VDday-hC1YMPOWyIt9Bp05UuJb673btr6T1YOd0DliheWDyqQ", tokens.getAccessToken());
      assertEquals("Bearer", tokens.getTokenType());
      assertEquals(3600, tokens.getExpiresIn());
      assertEquals("AQAZ54v-sV7LO_R64q76KtDMKeQcPkBIPAuKFqYr1kSAeaU8_S8ZxbnqcNizeQiSJr5DhMsJvCdgS7_KUrHd7rw1z7h_FJkL5OVOnthZrNFdO5NL7gUvNJRF6hdbIkAnEHM", tokens.getRefreshtoken());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void shouldRefreshToken() throws Exception {
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";
    final String clientSecret = "0d3adc510bb94bcf8129301c46058dd9";
    final String refreshToken = "AQAZ54v-sV7LO_R64q76KtDMKeQcPkBIPAuKFqYr1kSAeaU8_S8ZxbnqcNizeQiSJr5DhMsJvCdgS7_KUrHd7rw1z7h_FJkL5OVOnthZrNFdO5NL7gUvNJRF6hdbIkAnEHM";

    final AuthenticationApi api = AuthenticationApi.DEFAULT_API;

    final RefreshAccessTokenRequest.Builder requestBuilder = api.refreshAccessToken(clientId, clientSecret, refreshToken);
    requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("auth-tokens.json"));
    final RefreshAccessTokenRequest request = requestBuilder.build();

    try {
      RefreshAccessTokenResponse refreshAccessTokenResponse = request.post();
      assertNotNull(refreshAccessTokenResponse.getExpiresIn());
      assertNotNull(refreshAccessTokenResponse.getAccessToken());
      assertNotNull(refreshAccessTokenResponse.getTokenType());
    } catch (ErrorResponseException e) {
      fail(e.getMessage());
    }
  }
}
