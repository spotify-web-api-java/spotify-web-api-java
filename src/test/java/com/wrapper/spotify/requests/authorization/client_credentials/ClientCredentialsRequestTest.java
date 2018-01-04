package com.wrapper.spotify.requests.authorization.client_credentials;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class ClientCredentialsRequestTest implements ITest<ClientCredentials> {
  private final ClientCredentialsRequest successRequest = SPOTIFY_API
          .clientCredentials()
          .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/authorization/client_credentials/ClientCredentials.json"))
          .build();

  public ClientCredentialsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((ClientCredentials) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final ClientCredentials clientCredentials) {
    assertEquals(
            "NgCXRKc...MzYjw",
            clientCredentials.getAccessToken());
    assertEquals(
            "Bearer",
            clientCredentials.getTokenType());
    assertEquals(
            3600,
            (int) clientCredentials.getExpiresIn());
  }
}
