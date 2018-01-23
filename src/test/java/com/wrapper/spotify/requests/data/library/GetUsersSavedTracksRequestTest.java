package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedTrack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersSavedTracksRequestTest implements ITest<Paging<SavedTrack>> {
  private final GetUsersSavedTracksRequest successRequest = SPOTIFY_API
          .getUsersSavedTracks()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/library/GetUsersSavedTracksRequest.json"))
          .build();

  public GetUsersSavedTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Paging<SavedTrack>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Paging<SavedTrack> savedTrackPaging) {
    assertEquals(
            "https://api.spotify.com/v1/me/tracks?offset=5&limit=10&market=ES",
            savedTrackPaging.getHref());
    assertEquals(
            0,
            savedTrackPaging.getItems().length);
    assertEquals(
            10,
            (int) savedTrackPaging.getLimit());
    assertNull(
            savedTrackPaging.getNext());
    assertEquals(
            5,
            (int) savedTrackPaging.getOffset());
    assertEquals(
            "https://api.spotify.com/v1/me/tracks?offset=0&limit=10&market=ES",
            savedTrackPaging.getPrevious());
    assertEquals(
            0,
            (int) savedTrackPaging.getTotal());
  }
}
