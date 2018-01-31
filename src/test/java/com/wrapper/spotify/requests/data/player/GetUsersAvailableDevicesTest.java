package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.Device;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersAvailableDevicesTest extends AbstractDataTest<Device[]> {
  private final GetUsersAvailableDevicesRequest defaultRequest = SPOTIFY_API
          .getUsersAvailableDevices()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetUsersAvailableDevices.json"))
          .build();

  private final GetUsersAvailableDevicesRequest emptyRequest = SPOTIFY_API
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
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Device[]) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Device[] devices) {
    assertEquals(
            1,
            devices.length);
  }

  @Test
  public void shouldReturnEmpty_sync() throws IOException, SpotifyWebApiException {
    shouldReturnEmpty(emptyRequest.execute());
  }

  @Test
  public void shouldReturnEmpty_async() throws ExecutionException, InterruptedException {
    shouldReturnEmpty((Device[]) emptyRequest.executeAsync().get());
  }

  public void shouldReturnEmpty(final Device[] devices) {
    assertEquals(
            0,
            devices.length);
  }
}
