package se.michaelthelin.spotify;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.apache.hc.core5.http.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.michaelthelin.spotify.SpotifyHttpManager.Builder;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.exceptions.detailed.TooManyRequestsException;

import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@WireMockTest(httpPort = 9090)
class SpotifyHttpManagerTest {

  private final SpotifyHttpManager spotifyHttpManager = new SpotifyHttpManager(new Builder());

  @ParameterizedTest
  @ValueSource(ints = {400, 401, 404, 405, 409, 410, 414, 422, 431, 499})
  public void throwsSpotifyWebApiExceptionForAll4xxStatusCodes(int statusCode) {

    stubFor(get("/test/foo/")
      .willReturn(aResponse()
        .withStatus(statusCode)));

    Assertions.assertThrows(SpotifyWebApiException.class, () ->
      spotifyHttpManager.get(URI.create("http://localhost:9090/test/foo/"), new Header[0]));
  }

  @ParameterizedTest
  @ValueSource(ints = {500, 501, 502, 503, 504, 599})
  public void throwsSpotifyWebApiExceptionForAll5xxStatusCodes(int statusCode) {

    stubFor(get("/test/foo/")
      .willReturn(aResponse()
        .withStatus(statusCode)));

    Assertions.assertThrows(SpotifyWebApiException.class, () ->
      spotifyHttpManager.get(URI.create("http://localhost:9090/test/foo/"), new Header[0]));
  }

  @Test
  public void exposesPlayerErrorReasonOnSpotifyWebApiException() {

    stubFor(get("/test/foo/")
      .willReturn(aResponse()
        .withStatus(403)
        .withBody("{\"error\": {\"status\": 403, \"message\": \"Player command failed: Premium required\", \"reason\": \"PREMIUM_REQUIRED\"}}")));

    SpotifyWebApiException exception = Assertions.assertThrows(SpotifyWebApiException.class, () ->
      spotifyHttpManager.get(URI.create("http://localhost:9090/test/foo/"), new Header[0]));

    Assertions.assertEquals("Player command failed: Premium required", exception.getMessage());
    Assertions.assertEquals("PREMIUM_REQUIRED", exception.getReason());
  }

  @Test
  public void throwsTooManyRequestsExceptionWhenNoRetryAfterHeaderIsPresent() {

    stubFor(get("/test/foo/")
      .willReturn(aResponse()
        .withStatus(429)));

    Assertions.assertThrows(TooManyRequestsException.class, () ->
      spotifyHttpManager.get(URI.create("http://localhost:9090/test/foo/"), new Header[0]));
  }

  @Test
  public void readsErrorDescriptionFromOAuthErrorResponse() {

    stubFor(get("/test/foo/")
      .willReturn(aResponse()
        .withStatus(401)
        .withBody("{\"error\": \"invalid_request\", \"error_description\": \"Bad token\"}")));

    SpotifyWebApiException exception = Assertions.assertThrows(SpotifyWebApiException.class, () ->
      spotifyHttpManager.get(URI.create("http://localhost:9090/test/foo/"), new Header[0]));

    Assertions.assertEquals("Bad token", exception.getMessage());
  }

  @Test
  public void keepsReasonNullWhenErrorResponseHasNoReason() {

    stubFor(get("/test/foo/")
      .willReturn(aResponse()
        .withStatus(403)
        .withBody("{\"error\": {\"status\": 403, \"message\": \"Player command failed\"}}")));

    SpotifyWebApiException exception = Assertions.assertThrows(SpotifyWebApiException.class, () ->
      spotifyHttpManager.get(URI.create("http://localhost:9090/test/foo/"), new Header[0]));

    Assertions.assertNull(exception.getReason());
  }
}
