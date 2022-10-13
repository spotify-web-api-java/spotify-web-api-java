package se.michaelthelin.spotify.requests.data.player;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.CurrentlyPlayingType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class GetUsersCurrentlyPlayingTrackRequestTest extends AbstractDataTest<CurrentlyPlaying> {
  private final GetUsersCurrentlyPlayingTrackRequest defaultRequest = ITest.SPOTIFY_API
    .getUsersCurrentlyPlayingTrack()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/player/GetUsersCurrentlyPlayingTrackRequest.json"))
    .market(ITest.MARKET)
    .additionalTypes(ITest.ADDITIONAL_TYPES)
    .build();

  private final GetUsersCurrentlyPlayingTrackRequest defaultEpisodeRequest = ITest.SPOTIFY_API
    .getUsersCurrentlyPlayingTrack()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/player/GetUsersCurrentlyPlayingTrackRequest_Episode.json"))
    .market(ITest.MARKET)
    .additionalTypes(ITest.ADDITIONAL_TYPES)
    .build();

  private final GetUsersCurrentlyPlayingTrackRequest emptyRequest = ITest.SPOTIFY_API
    .getUsersCurrentlyPlayingTrack()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .market(ITest.MARKET)
    .additionalTypes(ITest.ADDITIONAL_TYPES)
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
