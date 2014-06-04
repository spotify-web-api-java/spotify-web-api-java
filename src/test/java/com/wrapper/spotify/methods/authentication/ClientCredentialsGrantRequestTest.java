package com.wrapper.spotify.methods.authentication;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.WebApiAuthenticationException;
import com.wrapper.spotify.models.ClientCredentials;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class ClientCredentialsGrantRequestTest {

  @Test
  public void shouldGetAccessToken_sync() throws Exception {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";

    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();

    final ClientCredentialsGrantRequest request = api
            .clientCredentialsGrant()
            .httpManager(TestUtil.MockedHttpManager.returningJson("application-authentication-token.json"))
            .build();

    final ClientCredentials response = request.get();
    assertEquals(3600, response.getExpiresIn());
    assertEquals("BQAh_5C4JzOMLuF0W-UVTtaOhZaX0bjgJ5B8giFun_i7AJRKTpZ-VB1mFd3hWLLWRsZNihc_fG1xUlnW9sLBjQ", response.getAccessToken());
    assertEquals("Bearer", response.getTokenType());
  }

  @Test(expected=WebApiAuthenticationException.class)
  public void shouldFailGettingAccessToken_incorrectClientId_sync() throws Exception {
    final String clientId = "myClientId";
    final String clientSecret = "incorrectClientSecret";

    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();

    final ClientCredentialsGrantRequest request = api
            .clientCredentialsGrant()
            .httpManager(TestUtil.MockedHttpManager.returningJson("auth-invalid-client.json"))
            .build();

    request.get();
  }

  @Test
  public void shouldGetAccessToken_async() throws Exception {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";

    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();

    final ClientCredentialsGrantRequest request = api
            .clientCredentialsGrant()
            .httpManager(TestUtil.MockedHttpManager.returningJson("application-authentication-token.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<ClientCredentials> responseFuture = request.getAsync();

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

  @Test
  public void shouldFailGettingAccessToken_incorrectClientId_async() throws Exception {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";

    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();

    final ClientCredentialsGrantRequest request = api
            .clientCredentialsGrant()
            .httpManager(TestUtil.MockedHttpManager.returningJson("auth-invalid-client.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<ClientCredentials> responseFuture = request.getAsync();

    Futures.addCallback(responseFuture, new FutureCallback<ClientCredentials>() {
      @Override
      public void onSuccess(ClientCredentials response) {
        fail("Should have failed to resolve future");
      }

      @Override
      public void onFailure(Throwable throwable) {
        assertEquals(throwable.getClass(), WebApiAuthenticationException.class);
        asyncCompleted.countDown();
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

}
