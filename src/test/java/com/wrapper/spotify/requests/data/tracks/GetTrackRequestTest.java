package com.wrapper.spotify.requests.data.tracks;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
public class GetTrackRequestTest {

  @Test
  public void shouldGetTrackResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetTrackRequest request = api.getTrack("0eGsygTp906u18L0Oimnem")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/tracks/GetTrackRequest.json"))
            .build();

    final Future<Track> requestFuture = request.executeAsync();
    final Track track = requestFuture.get();

    assertNotNull(track);
    assertEquals("0eGsygTp906u18L0Oimnem", track.getId());
  }

  @Test
  public void shouldGetTrackResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetTrackRequest request = api.getTrack("0eGsygTp906u18L0Oimnem")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/tracks/GetTrackRequest.json"))
            .build();

    final Track track = request.execute();

    assertNotNull(track);
    assertEquals("0eGsygTp906u18L0Oimnem", track.getId());
  }
}
