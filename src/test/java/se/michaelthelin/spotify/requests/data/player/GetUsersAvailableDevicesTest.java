package se.michaelthelin.spotify.requests.data.player;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.Device;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUsersAvailableDevicesTest extends AbstractDataTest<Device[]> {
  private final GetUsersAvailableDevicesRequest defaultRequest = ITest.SPOTIFY_API
    .getUsersAvailableDevices()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/player/GetUsersAvailableDevices.json"))
    .build();

  private final GetUsersAvailableDevicesRequest emptyRequest = ITest.SPOTIFY_API
    .getUsersAvailableDevices()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/player/GetUsersAvailableDevices_None.json"))
    .build();

  public GetUsersAvailableDevicesTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/player/devices",
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

  public void shouldReturnDefault(final Device[] devices) {
    assertEquals(
      1,
      devices.length);
  }

  @Test
  public void shouldReturnEmpty_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnEmpty(emptyRequest.execute());
  }

  @Test
  public void shouldReturnEmpty_async() throws ExecutionException, InterruptedException {
    shouldReturnEmpty(emptyRequest.executeAsync().get());
  }

  public void shouldReturnEmpty(final Device[] devices) {
    assertEquals(
      0,
      devices.length);
  }
}
