package se.michaelthelin.spotify;

import org.junit.Test;
import se.michaelthelin.spotify.methods.Request;

import static junit.framework.TestCase.assertEquals;

/**
 * Unit tests for the API class.
 */
public class ApiTest {

  @Test
  public void shouldCreateAGetAlbumUrl() {
    Api api = new Api();
    Request request = api.album().id("5oEljuMoe9MXH6tBIPbd5e").build();

    assertEquals("https://api.spotify.com/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
  }
}
