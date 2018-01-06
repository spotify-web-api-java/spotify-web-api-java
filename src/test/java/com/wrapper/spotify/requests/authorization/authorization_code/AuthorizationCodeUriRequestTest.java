package com.wrapper.spotify.requests.authorization.authorization_code;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class AuthorizationCodeUriRequestTest implements ITest<URI> {

  private final AuthorizationCodeUriRequest successRequest = SPOTIFY_API.authorizationCodeUri()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/authorization/authorization_code/AuthorizationCodeUri.txt"))
          .build();

  public AuthorizationCodeUriRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((URI) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final URI uri) {
    assertEquals(
            "https://example.com/callback?code=NApCCg..BkWtQ&state=profile%2Factivity",
            uri.toString());
  }
}
