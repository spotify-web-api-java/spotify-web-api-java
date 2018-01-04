package com.wrapper.spotify.requests.authorization.client_credentials;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class ClientCredentialsRequestTest {

  @Test
  public void shouldGetAccessToken_sync() throws Exception {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";

    final SpotifyApi api = SpotifyApi.builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();

    final ClientCredentialsRequest request = api
            .clientCredentials()
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/authorization/client_credentials/ClientCredentials.json"))
            .build();

    final ClientCredentials response = request.execute();
    assertEquals(3600, (int) response.getExpiresIn());
    assertEquals("BQAh_5C4JzOMLuF0W-UVTtaOhZaX0bjgJ5B8giFun_i7AJRKTpZ-VB1mFd3hWLLWRsZNihc_fG1xUlnW9sLBjQ", response.getAccessToken());
    assertEquals("Bearer", response.getTokenType());
  }

  @Test
  public void shouldGetAccessToken_async() throws Exception {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";

    final SpotifyApi api = SpotifyApi.builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();

    final ClientCredentialsRequest request = api
            .clientCredentials()
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/authorization/client_credentials/ClientCredentials.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final Future<ClientCredentials> requestFuture = request.executeAsync();
    ClientCredentials clientCredentials = requestFuture.get();

    assertEquals(3600, (int) clientCredentials.getExpiresIn());
    assertEquals("BQAh_5C4JzOMLuF0W-UVTtaOhZaX0bjgJ5B8giFun_i7AJRKTpZ-VB1mFd3hWLLWRsZNihc_fG1xUlnW9sLBjQ", clientCredentials.getAccessToken());
    assertEquals("Bearer", clientCredentials.getTokenType());
  }

}
