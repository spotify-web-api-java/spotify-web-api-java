package com.wrapper.spotify.requests.authorization.client_credentials;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.AbstractAuthorizationTest;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.wrapper.spotify.Assertions.assertHasBodyParameter;
import static com.wrapper.spotify.Assertions.assertHasHeader;
import static org.junit.Assert.assertEquals;

public class ClientCredentialsRequestTest extends AbstractAuthorizationTest<ClientCredentials> {
  private final ClientCredentialsRequest defaultRequest = SPOTIFY_API.clientCredentials()
          .setHttpManager(TestUtil.MockedHttpManager.returningJson(
                  "requests/authorization/client_credentials/ClientCredentials.json"))
          .grant_type("client_credentials")
          .build();

  public ClientCredentialsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/x-www-form-urlencoded");
    assertHasBodyParameter(
            defaultRequest,
            "grant_type",
            "client_credentials");
    assertEquals(
            "https://accounts.spotify.com:443/api/token",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((ClientCredentials) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final ClientCredentials clientCredentials) {
    assertEquals(
            "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk",
            clientCredentials.getAccessToken());
    assertEquals(
            "Bearer",
            clientCredentials.getTokenType());
    assertEquals(
            3600,
            (int) clientCredentials.getExpiresIn());
  }
}
