package se.michaelthelin.spotify;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import se.michaelthelin.spotify.methods.Request;

import static junit.framework.TestCase.assertEquals;
import static se.michaelthelin.spotify.Assertions.assertHasBodyParameter;
import static se.michaelthelin.spotify.Assertions.assertHasHeader;

public class AuthenticationApiTest {

  @Test
  public void shouldCreateRequestForTokensUrl() {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String code = "returnedCode";
    final String redirectUri = "myRedirectUri";

    final AuthenticationApi api = AuthenticationApi.DEFAULT_API;
    final Request request = api.getTokens(clientId, clientSecret, code, redirectUri).build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.toString());
    assertHasBodyParameter(request.toUrl(), "grant_type", "authorization_code");
    assertHasBodyParameter(request.toUrl(), "code", code);
    assertHasBodyParameter(request.toUrl(), "redirect_uri", redirectUri);

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

  @Test
  public void shouldCreateRefreshAccessTokenUrl() {
    final AuthenticationApi api = AuthenticationApi.DEFAULT_API;

    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String refreshToken = "myRefreshToken";

    final Request request = api.refreshAccessToken(clientId, clientSecret, refreshToken).build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.toString());
    assertHasBodyParameter(request.toUrl(), "grant_type", "refresh_token");
    assertHasBodyParameter(request.toUrl(), "refresh_token", refreshToken);

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

}
