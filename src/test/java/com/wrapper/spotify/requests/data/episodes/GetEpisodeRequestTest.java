package com.wrapper.spotify.requests.data.episodes;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.enums.ReleaseDatePrecision;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Episode;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.apache.hc.core5.http.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetEpisodeRequestTest extends AbstractDataTest<Episode> {
  private final GetEpisodeRequest defaultRequest = SPOTIFY_API
    .getEpisode(ID_EPISODE)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/episodes/GetEpisodeRequest.json"))
    .market(MARKET)
    .build();

  public GetEpisodeRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/episodes/4GI3dxEafwap1sFiTGPKd1?market=SE",
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

  public void shouldReturnDefault(final Episode episode) {
    assertEquals(
      "https://p.scdn.co/mp3-preview/9b0ebffde166917f35f9eab25af874e765261776",
      episode.getAudioPreviewUrl());
    assertEquals(
      255,
      episode.getDescription().length());
    assertEquals(
      8052140,
      (int) episode.getDurationMs());
    assertFalse(
      episode.getExplicit());
    assertNotNull(
      episode.getExternalUrls());
    assertEquals(
      "https://api.spotify.com/v1/episodes/4GI3dxEafwap1sFiTGPKd1",
      episode.getHref());
    assertEquals(
      ID_EPISODE,
      episode.getId());
    assertEquals(
      3,
      episode.getImages().length);
    assertFalse(
      episode.getExternallyHosted());
    assertTrue(
      episode.getPlayable());
    assertEquals(
      1,
      episode.getLanguages().length);
    assertEquals(
      "The Giant Beastcast: Episode 0",
      episode.getName());
    assertEquals(
      "2015-05-22",
      episode.getReleaseDate());
    assertEquals(
      ReleaseDatePrecision.DAY,
      episode.getReleaseDatePrecision());
    assertFalse(
      episode.getResumePoint().getFullyPlayed());
    assertEquals(
      3576000,
      (int) episode.getResumePoint().getResumePositionMs());
    assertFalse(
      episode.getResumePoint().getFullyPlayed());
    assertNotNull(
      episode.getShow());
    assertEquals(
      ModelObjectType.EPISODE,
      episode.getType());
    assertEquals(
      "spotify:episode:4GI3dxEafwap1sFiTGPKd1",
      episode.getUri());
  }
}
