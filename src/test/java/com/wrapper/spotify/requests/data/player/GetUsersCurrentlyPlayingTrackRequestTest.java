package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.CurrentlyPlayingType;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlaying;
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
public class GetUsersCurrentlyPlayingTrackRequestTest extends AbstractDataTest<CurrentlyPlaying> {
  private final GetUsersCurrentlyPlayingTrackRequest defaultRequest = SPOTIFY_API
    .getUsersCurrentlyPlayingTrack()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/player/GetUsersCurrentlyPlayingTrackRequest.json"))
    .market(MARKET)
    .additionalTypes(ADDITIONAL_TYPES)
    .build();

  private final GetUsersCurrentlyPlayingTrackRequest defaultEpisodeRequest = SPOTIFY_API
    .getUsersCurrentlyPlayingTrack()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/player/GetUsersCurrentlyPlayingTrackRequest_Episode.json"))
    .market(MARKET)
    .additionalTypes(ADDITIONAL_TYPES)
    .build();

  private final GetUsersCurrentlyPlayingTrackRequest emptyRequest = SPOTIFY_API
    .getUsersCurrentlyPlayingTrack()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .market(MARKET)
    .additionalTypes(ADDITIONAL_TYPES)
    .build();

  public GetUsersCurrentlyPlayingTrackRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/player/currently-playing?market=SE&additional_types=track%2Cepisode",
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

  public void shouldReturnDefault(final CurrentlyPlaying currentlyPlaying) {
    assertNull(
      currentlyPlaying.getContext());
    assertEquals(
      1516669900630L,
      (long) currentlyPlaying.getTimestamp());
    assertEquals(
      78810,
      (int) currentlyPlaying.getProgress_ms());
    assertFalse(
      currentlyPlaying.getIs_playing());
    assertNotNull(
      currentlyPlaying.getItem());
    assertTrue(
      currentlyPlaying.getItem() instanceof Track);
    assertNotNull(
      currentlyPlaying.getActions());
    assertEquals(
      4,
      currentlyPlaying.getActions().getDisallows().getDisallowedActions().size());
    assertEquals(
      CurrentlyPlayingType.TRACK,
      currentlyPlaying.getCurrentlyPlayingType());
  }

  @Test
  public void shouldReturnDefaultEpisode_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefaultEpisode(defaultEpisodeRequest.execute());
  }

  @Test
  public void shouldReturnDefaultEpisode_async() throws ExecutionException, InterruptedException {
    shouldReturnDefaultEpisode(defaultEpisodeRequest.executeAsync().get());
  }

  public void shouldReturnDefaultEpisode(final CurrentlyPlaying currentlyPlaying) {
    assertEquals(
      1516669848357L,
      (long) currentlyPlaying.getTimestamp());
    assertNotNull(
      currentlyPlaying.getContext());
    assertEquals(
      currentlyPlaying.getContext().getType(),
      ModelObjectType.SHOW);
    assertEquals(
      3636145,
      (int) currentlyPlaying.getProgress_ms());
    assertNotNull(
      currentlyPlaying.getItem());
    assertTrue(
      currentlyPlaying.getItem() instanceof Episode);
    assertEquals(
      CurrentlyPlayingType.EPISODE,
      currentlyPlaying.getCurrentlyPlayingType());
    assertNotNull(
      currentlyPlaying.getActions());
    assertEquals(
      4,
      currentlyPlaying.getActions().getDisallows().getDisallowedActions().size());
    assertTrue(
      currentlyPlaying.getIs_playing());
  }

  @Test
  public void shouldReturnEmpty_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnEmpty(emptyRequest.execute());
  }

  @Test
  public void shouldReturnEmpty_async() throws ExecutionException, InterruptedException {
    shouldReturnEmpty(emptyRequest.executeAsync().get());
  }

  public void shouldReturnEmpty(final CurrentlyPlaying currentlyPlaying) {
    assertNull(
      currentlyPlaying);
  }
}
