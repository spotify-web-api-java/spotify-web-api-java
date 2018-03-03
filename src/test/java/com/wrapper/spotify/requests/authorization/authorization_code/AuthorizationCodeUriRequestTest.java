package com.wrapper.spotify.requests.authorization.authorization_code;

import com.wrapper.spotify.requests.authorization.AbstractAuthorizationTest;
import org.junit.Test;

import java.net.URI;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class AuthorizationCodeUriRequestTest extends AbstractAuthorizationTest<URI> {

  private final AuthorizationCodeUriRequest defaultRequest = SPOTIFY_API.authorizationCodeUri()
          .scope(SCOPE)
          .show_dialog(SHOW_DIALOG)
          .build();

  public AuthorizationCodeUriRequestTest() {
  }

  @Test
  public void shouldComplyWithReference() {
    assertEquals(
            "https://accounts.spotify.com:443/authorize?client_id=zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g&response_type=code&redirect_uri=https%3A%2F%2Fexample.com%2Fspotify-redirect&scope=user-read-birthday+user-read-email&show_dialog=true",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((URI) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final URI uri) {
    assertEquals(
            "https://accounts.spotify.com:443/authorize?client_id=zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g&response_type=code&redirect_uri=https%3A%2F%2Fexample.com%2Fspotify-redirect&scope=user-read-birthday+user-read-email&show_dialog=true",
            uri.toString());
  }
}
