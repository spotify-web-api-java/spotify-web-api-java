package com.wrapper.spotify.requests.data.tracks;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.Modality;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetAudioFeaturesForTrackRequestTest extends AbstractDataTest<AudioFeatures> {
  private final GetAudioFeaturesForTrackRequest defaultRequest = SPOTIFY_API
          .getAudioFeaturesForTrack(ID_TRACK)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/tracks/GetAudioFeaturesForTrackRequest.json"))
          .build();

  public GetAudioFeaturesForTrackRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/audio-features/01iyCAUm8EvOFqVWYJ3dVX",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((AudioFeatures) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final AudioFeatures audioFeatures) {
    assertEquals(
            0.514,
            audioFeatures.getAcousticness(), 0.001);
    assertEquals(
            "https://api.spotify.com/v1/audio-analysis/06AKEBrKUckW0KREUWRnvT",
            audioFeatures.getAnalysisUrl());
    assertEquals(
            0.735,
            audioFeatures.getDanceability(), 0.001);
    assertEquals(
            255349,
            (int) audioFeatures.getDurationMs());
    assertEquals(
            0.578,
            audioFeatures.getEnergy(), 0.001);
    assertEquals(
            "06AKEBrKUckW0KREUWRnvT",
            audioFeatures.getId());
    assertEquals(
            0.0902,
            audioFeatures.getInstrumentalness(), 0.001);
    assertEquals(
            5,
            (int) audioFeatures.getKey());
    assertEquals(
            0.159,
            audioFeatures.getLiveness(), 0.001);
    assertEquals(
            -11.840,
            audioFeatures.getLoudness(), 0.001);
    assertEquals(
            Modality.MINOR,
            audioFeatures.getMode());
    assertEquals(
            0.0461,
            audioFeatures.getSpeechiness(), 0.001);
    assertEquals(
            98.002,
            audioFeatures.getTempo(), 0.001);
    assertEquals(
            4,
            (int) audioFeatures.getTimeSignature());
    assertEquals(
            "https://api.spotify.com/v1/tracks/06AKEBrKUckW0KREUWRnvT",
            audioFeatures.getTrackHref());
    assertEquals(
            ModelObjectType.AUDIO_FEATURES,
            audioFeatures.getType());
    assertEquals(
            "spotify:track:06AKEBrKUckW0KREUWRnvT",
            audioFeatures.getUri());
    assertEquals(
            0.624,
            audioFeatures.getValence(), 0.001);
  }
}
