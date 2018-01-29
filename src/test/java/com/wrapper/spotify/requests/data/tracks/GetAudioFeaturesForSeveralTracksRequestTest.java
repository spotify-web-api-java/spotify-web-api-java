package com.wrapper.spotify.requests.data.tracks;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetAudioFeaturesForSeveralTracksRequestTest implements ITest<AudioFeatures[]> {
  private final GetAudioFeaturesForSeveralTracksRequest defaultRequest = SPOTIFY_API
          .getAudioFeaturesForSeveralTracks("id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/tracks/GetAudioFeaturesForSeveralTracksRequest.json"))
          .build();

  public GetAudioFeaturesForSeveralTracksRequestTest() throws Exception {
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
