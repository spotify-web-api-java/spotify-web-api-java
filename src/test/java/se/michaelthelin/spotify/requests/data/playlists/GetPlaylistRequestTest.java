package se.michaelthelin.spotify.requests.data.playlists;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.exceptions.detailed.TooManyRequestsException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

@WireMockTest(httpPort = 9090)
public class GetPlaylistRequestTest extends AbstractDataTest<Playlist> {
  private final GetPlaylistRequest defaultRequest = ITest.SPOTIFY_API
    .getPlaylist(ITest.ID_PLAYLIST)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/GetPlaylistRequest.json"))
    .fields(ITest.FIELDS)
    .market(ITest.MARKET)
    .additionalTypes(ITest.ADDITIONAL_TYPES)
    .build();

  public GetPlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldThrowTooManyRequestExceptionAndNotBlockThread_WhenSpotifyReturns429() {
    SpotifyApi spotifyApi = new SpotifyApi.Builder()
      .setScheme("http")
      .setHost("localhost")
      .setPort(9090)
      .setClientId("zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g")
      .setClientSecret("zudknyqbh3wunbhcvg9uyvo7uwzeu6nne")
      .setRedirectUri(SpotifyHttpManager.makeUri("https://example.com/spotify-redirect"))
      .setAccessToken("taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk")
      .setRefreshToken("b0KuPuLw77Z0hQhCsK-GTHoEx_kethtn357V7iqwEpCTIsLgqbBC_vQBTGC6M5rINl0FrqHK-D3cbOsMOlfyVKuQPvpyGcLcxAoLOTpYXc28nVwB7iBq2oKj9G9lHkFOUKn")
      .build();

    String playlistId = "5iZh1symrVbgqWNwXACTZ2";
    stubFor(get("/v1/playlists/"+ playlistId + "?fields=description%2Cowner")
      .willReturn(aResponse()
        .withBody("Too many requests")
        .withHeader("Retry-After", "4397")
        .withStatus(429))); // With default http client configuration, this property makes the client to block the thread

    GetPlaylistRequest req = spotifyApi.getPlaylist(playlistId)
      .fields("description,owner").build();

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Callable<Playlist> playlistCall = req::execute;

    try {
      Future<Playlist> submit = executor.submit(playlistCall);
      submit.get(10, TimeUnit.SECONDS);
    } catch (ExecutionException e) {
      assertEquals(e.getCause().getClass(), TooManyRequestsException.class);
    } catch (TimeoutException | InterruptedException e) {
      fail("Timeout, the thread blocked");
    }
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5?fields=description&market=SE&additional_types=track%2Cepisode",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Playlist playlist) {
    assertFalse(
      playlist.getIsCollaborative());
    assertEquals(
      "Having friends over for dinner? Here´s the perfect playlist.",
      playlist.getDescription());
    assertNotNull(
      playlist.getExternalUrls());
    assertNotNull(
      playlist.getFollowers());
    assertEquals(
      "https://api.spotify.com/v1/users/spotify/playlists/59ZbFPES4DQwEjBpWHzrtC",
      playlist.getHref());
    assertEquals(
      "59ZbFPES4DQwEjBpWHzrtC",
      playlist.getId());
    assertEquals(
      1,
      playlist.getImages().length);
    assertEquals(
      "Dinner with Friends",
      playlist.getName());
    assertNotNull(
      playlist.getOwner());
    assertNull(
      playlist.getIsPublicAccess());
    assertEquals(
      "bNLWdmhh+HDsbHzhckXeDC0uyKyg4FjPI/KEsKjAE526usnz2LxwgyBoMShVL+z+",
      playlist.getSnapshotId());
    assertNotNull(
      playlist.getTracks());
    assertEquals(
      ModelObjectType.PLAYLIST,
      playlist.getType());
    assertEquals(
      "spotify:user:spotify:playlist:59ZbFPES4DQwEjBpWHzrtC",
      playlist.getUri());
  }
}
