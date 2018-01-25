package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersAvailableDevicesTest implements ITest<Device[]> {
  private final GetUsersAvailableDevicesRequest successRequest = SPOTIFY_API
          .getUsersAvailableDevices()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetUsersAvailableDevices.json"))
          .build();

  private final GetUsersAvailableDevicesRequest failureRequest = SPOTIFY_API
          .getUsersAvailableDevices()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetUsersAvailableDevices_None.json"))
          .build();

  public GetUsersAvailableDevicesTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Device[]) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Device[] devices) {
    assertEquals(
            1,
            devices.length);
  }

  @Test
  public void shouldFail_sync() throws IOException, SpotifyWebApiException {
    shouldFail(failureRequest.execute());
  }

  @Test
  public void shouldFail_async() throws ExecutionException, InterruptedException {
    shouldFail((Device[]) failureRequest.executeAsync().get());
  }

  public void shouldFail(final Device[] devices) {
    assertEquals(
            0,
            devices.length);
  }
}
