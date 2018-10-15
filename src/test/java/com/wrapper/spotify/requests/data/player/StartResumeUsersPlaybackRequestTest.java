package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.wrapper.spotify.Assertions.assertHasBodyParameter;
import static com.wrapper.spotify.Assertions.assertHasHeader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class StartResumeUsersPlaybackRequestTest extends AbstractDataTest<String> {
  private final StartResumeUsersPlaybackRequest defaultRequest = SPOTIFY_API
          .startResumeUsersPlayback()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/StartResumeUsersPlaybackRequest.json"))
          .context_uri(CONTEXT_URI)
          .device_id(DEVICE_ID)
          .offset(OFFSET_JSON)
          .uris(URIS)
          .position_ms(POSITION_MS)
          .build();

  public StartResumeUsersPlaybackRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
            defaultRequest,
            "context_uri",
            CONTEXT_URI);
    assertHasBodyParameter(
            defaultRequest,
            "uris",
            URIS);
    assertHasBodyParameter(
            defaultRequest,
            "offset",
            OFFSET_JSON);
    assertEquals(
            "https://api.spotify.com:443/v1/me/player/play?device_id=5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e&position_ms=10000",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((String) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final String string) {
    assertNull(
            string);
  }
}
