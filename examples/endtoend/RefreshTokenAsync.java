package com.wrapper.spotify.endtoend;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.TokenResponse;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class RefreshTokenAsync {

  public static void main(String[] args) {
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";
    final String clientSecret = "0d3adc510bb94bcf8129301c46058dd9";
    final String code = "";
    final String redirectUri = "http://www.wrapper.com/test-callback";

    final Api api = Api.builder().build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<TokenResponse> tokenResponseFuture = api.getTokens(clientId, clientSecret, code, redirectUri).build().getAsync();

    Futures.addCallback(tokenResponseFuture, new FutureCallback<TokenResponse>() {
      @Override
      public void onSuccess(TokenResponse tokenResponse) {
        assertEquals(3600, tokenResponse.getExpiresIn());
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail(throwable.getMessage());
      }
    });


    try {
      asyncCompleted.await(1, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      fail(e.getMessage());
    }
  }

}
