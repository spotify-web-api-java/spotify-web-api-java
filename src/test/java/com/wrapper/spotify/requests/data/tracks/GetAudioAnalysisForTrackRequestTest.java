package com.wrapper.spotify.requests.data.tracks;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.AudioAnalysis;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GetAudioAnalysisForTrackRequestTest extends AbstractDataTest<AudioAnalysis> {
  private final GetAudioAnalysisForTrackRequest defaultRequest = SPOTIFY_API
          .getAudioAnalysisForTrack(ID_TRACK)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/tracks/GetAudioAnalysisForTrackRequest.json"))
          .build();

  public GetAudioAnalysisForTrackRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/audio-analysis/01iyCAUm8EvOFqVWYJ3dVX",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((AudioAnalysis) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final AudioAnalysis audioAnalysis) {
    assertEquals(
            1,
            audioAnalysis.getBars().length);
    assertEquals(
            1,
            audioAnalysis.getBeats().length);
    assertNotNull(
            audioAnalysis.getMeta());
    assertEquals(
            1,
            audioAnalysis.getSections().length);
    assertEquals(
            1,
            audioAnalysis.getSegments().length);
    assertEquals(
            1,
            audioAnalysis.getTatums().length);
    assertNotNull(
            "",
            audioAnalysis.getTrack());
  }
}
