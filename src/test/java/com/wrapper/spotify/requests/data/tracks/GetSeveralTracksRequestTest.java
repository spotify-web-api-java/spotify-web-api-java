package com.wrapper.spotify.requests.data.tracks;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetSeveralTracksRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetSeveralTracksRequest request = api.getSeveralTracks("0eGsygTp906u18L0Oimnem", "1lDWb6b6ieDQ2xT7ewTC3G")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/tracks/GetSeveralTracksRequest.json"))
            .build();

    final Future<Track[]> requestFuture = request.executeAsync();
    final Track[] tracks = requestFuture.get();

    assertEquals(2, tracks.length);

    Track firstTrack = tracks[0];
    assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

    Track secondTrack = tracks[1];
    assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());
  }

  @Test
  public void shouldGetTracksResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetSeveralTracksRequest request = api.getSeveralTracks("0eGsygTp906u18L0Oimnem", "1lDWb6b6ieDQ2xT7ewTC3G")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/tracks/GetSeveralTracksRequest.json"))
            .build();

    final Track[] tracks = request.execute();

    assertEquals(2, tracks.length);

    final Track firstTrack = tracks[0];
    assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

    final Track secondTrack = tracks[1];
    assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());
  }
}
