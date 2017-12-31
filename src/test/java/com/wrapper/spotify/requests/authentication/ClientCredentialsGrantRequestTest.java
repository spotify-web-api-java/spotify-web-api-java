package com.wrapper.spotify.requests.authentication;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ClientCredentialsGrantRequestTest {

  @Test
  public void shouldGetAccessToken_sync() throws Exception {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";

    final Api api = Api.builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();

    final ClientCredentialsRequest request = api
            .clientCredentialsGrant()
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/authentication/ClientCredentials.json"))
            .build();

    final ClientCredentials response = request.post();
    assertEquals(3600, response.getExpiresIn());
    assertEquals("BQAh_5C4JzOMLuF0W-UVTtaOhZaX0bjgJ5B8giFun_i7AJRKTpZ-VB1mFd3hWLLWRsZNihc_fG1xUlnW9sLBjQ", response.getAccessToken());
    assertEquals("Bearer", response.getTokenType());
  }

  @Test
  public void shouldGetAccessToken_async() throws Exception {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";

    final Api api = Api.builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();

    final ClientCredentialsRequest request = api
            .clientCredentialsGrant()
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/authentication/ClientCredentials.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<ClientCredentials> responseFuture = request.postAsync();

    Futures.addCallback(responseFuture, new FutureCallback<ClientCredentials>() {
      @Override
      public void onSuccess(ClientCredentials response) {
        assertEquals(3600, response.getExpiresIn());
        assertEquals("BQAh_5C4JzOMLuF0W-UVTtaOhZaX0bjgJ5B8giFun_i7AJRKTpZ-VB1mFd3hWLLWRsZNihc_fG1xUlnW9sLBjQ", response.getAccessToken());
        assertEquals("Bearer", response.getTokenType());
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future: " + throwable.getMessage());
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

}
