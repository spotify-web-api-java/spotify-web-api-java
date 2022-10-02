package se.michaelthelin.spotify.requests.authorization.authorization_code;

import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;

import java.net.URI;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizationCodeUriRequestTest implements ITest<URI> {

  private final AuthorizationCodeUriRequest defaultRequest = SPOTIFY_API.authorizationCodeUri()
    .scope(SCOPE)
    .show_dialog(SHOW_DIALOG)
    .build();

  private final AuthorizationCodeUriRequest defaultPKCERequest = SPOTIFY_API.authorizationCodePKCEUri(CODE_CHALLENGE)
    .scope(SCOPE)
    .show_dialog(SHOW_DIALOG)
    .build();

  public AuthorizationCodeUriRequestTest() {
  }

  @Test
  public void shouldComplyWithReference() {
    assertEquals(
      "https://accounts.spotify.com:443/authorize?client_id=zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g&response_type=code&redirect_uri=https%3A%2F%2Fexample.com%2Fspotify-redirect&scope=user-read-birthday%20user-read-email&show_dialog=true",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final URI uri) {
    assertEquals(
      "https://accounts.spotify.com:443/authorize?client_id=zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g&response_type=code&redirect_uri=https%3A%2F%2Fexample.com%2Fspotify-redirect&scope=user-read-birthday%20user-read-email&show_dialog=true",
      uri.toString());
  }

  @Test
  public void shouldReturnDefaultPKCE_sync() {
    shouldReturnDefaultPKCE(defaultPKCERequest.execute());
  }

  @Test
  public void shouldReturnDefaultPKCE_async() throws ExecutionException, InterruptedException {
    shouldReturnDefaultPKCE(defaultPKCERequest.executeAsync().get());
  }

  public void shouldReturnDefaultPKCE(final URI uri) {
    assertEquals(
      "https://accounts.spotify.com:443/authorize?client_id=zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g&response_type=code&code_challenge_method=S256&code_challenge=w6iZIj99vHGtEx_NVl9u3sthTN646vvkiP8OMCGfPmo&redirect_uri=https%3A%2F%2Fexample.com%2Fspotify-redirect&scope=user-read-birthday%20user-read-email&show_dialog=true",
      uri.toString());
  }
}
