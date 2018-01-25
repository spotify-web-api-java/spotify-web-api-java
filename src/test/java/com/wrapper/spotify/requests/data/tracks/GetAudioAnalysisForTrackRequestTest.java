package com.wrapper.spotify.requests.data.tracks;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.AudioAnalysis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GetAudioAnalysisForTrackRequestTest implements ITest<AudioAnalysis> {
  private final GetAudioAnalysisForTrackRequest successRequest = SPOTIFY_API
          .getAudioAnalysisForTrack("id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/tracks/GetAudioAnalysisForTrackRequest.json"))
          .build();

  public GetAudioAnalysisForTrackRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((AudioAnalysis) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final AudioAnalysis audioAnalysis) {
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
