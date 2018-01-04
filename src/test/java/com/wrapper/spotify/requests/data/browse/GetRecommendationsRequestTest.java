package com.wrapper.spotify.requests.data.browse;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

/**
 * Created by doug on 6/16/16.
 */
public class GetRecommendationsRequestTest {

  @Test
  public void shouldGetRecommendationsResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetRecommendationsRequest request = api.getRecommendations()
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/browse/GetRecommendationsRequest.json"))
            .build();

    final Future<Recommendations> requestFuture = request.executeAsync();
    final Recommendations recommendations = requestFuture.get();

    final TrackSimplified[] tracks = recommendations.getTracks();
    assertEquals(10, tracks.length);

    TrackSimplified firstTrack = tracks[0];
    assertEquals("7IXU0WXqnktR0ntaEqzmwR", firstTrack.getId());

    TrackSimplified secondTrack = tracks[1];
    assertEquals("5gWtkdgdyt5bZt9i6n3Kqd", secondTrack.getId());
  }

  @Test
  public void shouldGetRecommendationsResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetRecommendationsRequest request = api.getRecommendations()
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/browse/GetRecommendationsRequest.json"))
            .build();

    final TrackSimplified[] tracks = request.execute().getTracks();

    assertEquals(10, tracks.length);

    final TrackSimplified firstTrack = tracks[0];
    assertEquals("7IXU0WXqnktR0ntaEqzmwR", firstTrack.getId());

    final TrackSimplified secondTrack = tracks[1];
    assertEquals("5gWtkdgdyt5bZt9i6n3Kqd", secondTrack.getId());
  }
}
