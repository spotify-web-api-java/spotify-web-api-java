package se.michaelthelin.spotify.requests.data.player;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.CurrentlyPlayingType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class GetInformationAboutUsersCurrentPlaybackRequestTest extends AbstractDataTest<CurrentlyPlayingContext> {
  private final GetInformationAboutUsersCurrentPlaybackRequest defaultRequest = ITest.SPOTIFY_API
    .getInformationAboutUsersCurrentPlayback()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/player/GetInformationAboutUsersCurrentPlaybackRequest.json"))
    .market(ITest.MARKET)
    .additionalTypes(ITest.ADDITIONAL_TYPES)
    .build();

  private final GetInformationAboutUsersCurrentPlaybackRequest defaultEpisodeRequest = ITest.SPOTIFY_API
    .getInformationAboutUsersCurrentPlayback()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/player/GetInformationAboutUsersCurrentPlaybackRequest_Episode.json"))
    .market(ITest.MARKET)
    .additionalTypes(ITest.ADDITIONAL_TYPES)
    .build();

  private final GetInformationAboutUsersCurrentPlaybackRequest emptyRequest = ITest.SPOTIFY_API
    .getInformationAboutUsersCurrentPlayback()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .market(ITest.MARKET)
    .additionalTypes(ITest.ADDITIONAL_TYPES)
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
