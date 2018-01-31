package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetInformationAboutUsersCurrentPlaybackRequestTest extends AbstractDataTest<CurrentlyPlayingContext> {
  private final GetInformationAboutUsersCurrentPlaybackRequest defaultRequest = SPOTIFY_API
          .getInformationAboutUsersCurrentPlayback()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetInformationAboutUsersCurrentPlaybackRequest.json"))
          .market(MARKET)
          .build();

  private final GetInformationAboutUsersCurrentPlaybackRequest emptyRequest = SPOTIFY_API
          .getInformationAboutUsersCurrentPlayback()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/player/GetInformationAboutUsersCurrentPlaybackRequest_None.json"))
          .market(MARKET)
          .build();

  public GetInformationAboutUsersCurrentPlaybackRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/me/player?market=SE",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((CurrentlyPlayingContext) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final CurrentlyPlayingContext currentlyPlayingContext) {
    assertNotNull(
            currentlyPlayingContext.getDevice());
    assertEquals(
            "off",
            currentlyPlayingContext.getRepeat_state());
    assertFalse(
            currentlyPlayingContext.getShuffle_state());
    assertNull(
            currentlyPlayingContext.getContext());
    assertEquals(
            1516669848357L,
            (long) currentlyPlayingContext.getTimestamp());
    assertEquals(
            69937,
            (int) currentlyPlayingContext.getProgress_ms());
    assertTrue(
            currentlyPlayingContext.getIs_playing());
    assertNotNull(
            currentlyPlayingContext.getItem());
  }

  @Test
  public void shouldReturnEmpty_sync() throws IOException, SpotifyWebApiException {
    shouldReturnEmpty(emptyRequest.execute());
  }

  @Test
  public void shouldReturnEmpty_async() throws ExecutionException, InterruptedException {
    shouldReturnEmpty((CurrentlyPlayingContext) emptyRequest.executeAsync().get());
  }

  public void shouldReturnEmpty(final CurrentlyPlayingContext currentlyPlayingContext) {
    assertNull(
            currentlyPlayingContext);
  }
}
