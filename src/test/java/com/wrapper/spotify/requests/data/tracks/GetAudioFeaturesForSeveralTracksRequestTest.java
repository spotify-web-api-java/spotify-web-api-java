package com.wrapper.spotify.requests.data.tracks;

import com.wrapper.spotify.TestUtil;
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
public class GetAudioFeaturesForSeveralTracksRequestTest extends AbstractDataTest<AudioFeatures[]> {
  private final GetAudioFeaturesForSeveralTracksRequest defaultRequest = SPOTIFY_API
          .getAudioFeaturesForSeveralTracks(ID_TRACK, ID_TRACK)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/tracks/GetAudioFeaturesForSeveralTracksRequest.json"))
          .build();

  public GetAudioFeaturesForSeveralTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/audio-features?ids=01iyCAUm8EvOFqVWYJ3dVX%2C01iyCAUm8EvOFqVWYJ3dVX",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((AudioFeatures[]) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final AudioFeatures[] audioFeatures) {
    assertEquals(
            3,
            audioFeatures.length);
  }
}
