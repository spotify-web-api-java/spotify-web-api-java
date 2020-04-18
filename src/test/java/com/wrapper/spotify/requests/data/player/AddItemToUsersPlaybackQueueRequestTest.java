package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.apache.hc.core5.http.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.wrapper.spotify.Assertions.assertHasHeader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class AddItemToUsersPlaybackQueueRequestTest extends AbstractDataTest<String> {
  private final AddItemToUsersPlaybackQueueRequest defaultRequest = SPOTIFY_API
    .addItemToUsersPlaybackQueue("spotify:track:" + ID_TRACK)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .device_id(DEVICE_ID)
    .build();

  public AddItemToUsersPlaybackQueueRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertEquals("https://api.spotify.com:443/v1/me/player/queue?uri=spotify%3Atrack%3A01iyCAUm8EvOFqVWYJ3dVX&device_id=5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e",
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

  public void shouldReturnDefault(String string) {
    assertNull(string);
  }
}
