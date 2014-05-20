package se.michaelthelin.spotify.endtoend;

import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.models.RefreshAccessTokenResponse;
import se.michaelthelin.spotify.models.TokenResponse;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class RefreshTokenSync {

  public static void main(String[] args) {
    String clientId = "fcecfc79122e4cd299473677a17cbd4d";
    String clientSecret = "0d3adc510bb94bcf8129301c46058dd9";
    String code = "";
    String redirectUri = "http://www.michaelthelin.se/test-callback";

    Api api = Api.builder().build();

    try {
      final TokenResponse tokenResponse = api.getTokens(clientId, clientSecret, code, redirectUri).build().get();
      assertEquals(3600, tokenResponse.getExpiresIn());

      final RefreshAccessTokenResponse refreshResponse = api.refreshAccessToken(clientId, clientSecret, tokenResponse.getRefreshtoken()).build().get();
      assertEquals(3600, refreshResponse.getExpiresIn());

    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

}
