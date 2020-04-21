package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.CurrentlyPlayingType;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.model_objects.specification.Episode;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.apache.hc.core5.http.ParseException;
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
    .additionalTypes(ADDITIONAL_TYPES)
    .build();

  private final GetInformationAboutUsersCurrentPlaybackRequest defaultEpisodeRequest = SPOTIFY_API
    .getInformationAboutUsersCurrentPlayback()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/player/GetInformationAboutUsersCurrentPlaybackRequest_Episode.json"))
    .market(MARKET)
    .additionalTypes(ADDITIONAL_TYPES)
    .build();

  private final GetInformationAboutUsersCurrentPlaybackRequest emptyRequest = SPOTIFY_API
    .getInformationAboutUsersCurrentPlayback()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .market(MARKET)
    .additionalTypes(ADDITIONAL_TYPES)
    .build();

  public GetInformationAboutUsersCurrentPlaybackRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/player?market=SE&additional_types=track%2Cepisode",
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
    assertTrue(
      currentlyPlayingContext.getItem() instanceof Track);
    assertEquals(
      CurrentlyPlayingType.TRACK,
      currentlyPlayingContext.getCurrentlyPlayingType());
    assertNotNull(
      currentlyPlayingContext.getActions());
    assertEquals(
      2,
      currentlyPlayingContext.getActions().getDisallows().getDisallowedActions().size());
  }

  @Test
  public void shouldReturnDefaultEpisode_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefaultEpisode(defaultEpisodeRequest.execute());
  }

  @Test
  public void shouldReturnDefaultEpisode_async() throws ExecutionException, InterruptedException {
    shouldReturnDefaultEpisode(defaultEpisodeRequest.executeAsync().get());
  }

  public void shouldReturnDefaultEpisode(final CurrentlyPlayingContext currentlyPlayingContext) {
    assertNotNull(
      currentlyPlayingContext.getDevice());
    assertEquals(
      "off",
      currentlyPlayingContext.getRepeat_state());
    assertFalse(
      currentlyPlayingContext.getShuffle_state());
    assertNotNull(
      currentlyPlayingContext.getContext());
    assertEquals(
      currentlyPlayingContext.getContext().getType(),
      ModelObjectType.SHOW);
    assertEquals(
      1516669848357L,
      (long) currentlyPlayingContext.getTimestamp());
    assertEquals(
      1625,
      (int) currentlyPlayingContext.getProgress_ms());
    assertTrue(
      currentlyPlayingContext.getIs_playing());
    assertNotNull(
      currentlyPlayingContext.getItem());
    assertTrue(
      currentlyPlayingContext.getItem() instanceof Episode);
    assertEquals(
      CurrentlyPlayingType.EPISODE,
      currentlyPlayingContext.getCurrentlyPlayingType());
    assertNotNull(
      currentlyPlayingContext.getActions());
    assertEquals(
      4,
      currentlyPlayingContext.getActions().getDisallows().getDisallowedActions().size());
  }

  @Test
  public void shouldReturnEmpty_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnEmpty(emptyRequest.execute());
  }

  @Test
  public void shouldReturnEmpty_async() throws ExecutionException, InterruptedException {
    shouldReturnEmpty(emptyRequest.executeAsync().get());
  }

  public void shouldReturnEmpty(final CurrentlyPlayingContext currentlyPlayingContext) {
    assertNull(
      currentlyPlayingContext);
  }
}
