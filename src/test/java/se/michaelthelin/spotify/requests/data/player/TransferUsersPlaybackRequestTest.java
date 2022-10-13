package se.michaelthelin.spotify.requests.data.player;

import com.google.gson.JsonParser;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static se.michaelthelin.spotify.Assertions.assertHasBodyParameter;
import static se.michaelthelin.spotify.Assertions.assertHasHeader;

public class TransferUsersPlaybackRequestTest extends AbstractDataTest<String> {
  private final TransferUsersPlaybackRequest defaultRequest = ITest.SPOTIFY_API
    .transferUsersPlayback(JsonParser.parseString("[\"" + ITest.DEVICE_ID + "\"]").getAsJsonArray())
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .play(ITest.PLAY)
    .build();

  public TransferUsersPlaybackRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
      defaultRequest,
      "device_ids",
      "[\"" + ITest.DEVICE_ID + "\"]");
    assertHasBodyParameter(
      defaultRequest,
      "play",
      ITest.PLAY);
    assertEquals(
      "https://api.spotify.com:443/v1/me/player",
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

  public void shouldReturnDefault(final String string) {
    assertNull(
      string);
  }
}
