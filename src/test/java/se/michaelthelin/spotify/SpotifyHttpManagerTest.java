package se.michaelthelin.spotify;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.apache.hc.core5.http.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.michaelthelin.spotify.SpotifyHttpManager.Builder;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@WireMockTest(httpPort = 9090)
class SpotifyHttpManagerTest {

  private final SpotifyHttpManager spotifyHttpManager = new SpotifyHttpManager(new Builder());

  @ParameterizedTest
  @ValueSource(ints = {405, 409, 410, 414, 422, 431, 499})
  public void throwsSpotifyWebApiExceptionForAll4xxStatusCodes(int statusCode) {

    stubFor(get("/test/foo/")
      .willReturn(aResponse()
        .withStatus(statusCode)));

    Assertions.assertThrows(SpotifyWebApiException.class, () ->
      spotifyHttpManager.get(URI.create("http://localhost:9090/test/foo/"), new Header[0]));
  }

  @ParameterizedTest
  @ValueSource(ints = {501, 504, 599})
  public void throwsSpotifyWebApiExceptionForAll5xxStatusCodes(int statusCode) {

    stubFor(get("/test/foo/")
      .willReturn(aResponse()
        .withStatus(statusCode)));

    Assertions.assertThrows(SpotifyWebApiException.class, () ->
      spotifyHttpManager.get(URI.create("http://localhost:9090/test/foo/"), new Header[0]));
  }
}
