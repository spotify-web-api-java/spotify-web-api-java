package se.michaelthelin.spotify.methods.authentication;

import org.junit.Test;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.models.RefreshAccessTokenResponse;

import static junit.framework.Assert.assertNotNull;

public class RefreshAccessTokenRequestTest {

  @Test
  public void shouldRefreshToken() throws Exception {
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";
    final String clientSecret = "0d3adc510bb94bcf8129301c46058dd9";
    final String refreshToken = "AQAZ54v-sV7LO_R64q76KtDMKeQcPkBIPAuKFqYr1kSAeaU8_S8ZxbnqcNizeQiSJr5DhMsJvCdgS7_KUrHd7rw1z7h_FJkL5OVOnthZrNFdO5NL7gUvNJRF6hdbIkAnEHM";

    final Api api = Api.DEFAULT_API;

    final RefreshAccessTokenRequest request = api
            .refreshAccessToken(clientId, clientSecret, refreshToken)
            .httpManager(TestUtil.MockedHttpManager.returningJson("auth-tokens.json"))
            .build();

    RefreshAccessTokenResponse refreshAccessTokenResponse = request.get();
    assertNotNull(refreshAccessTokenResponse.getExpiresIn());
    assertNotNull(refreshAccessTokenResponse.getAccessToken());
    assertNotNull(refreshAccessTokenResponse.getTokenType());
  }
}
