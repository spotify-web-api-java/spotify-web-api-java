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
  private final GetUsersSavedTracksRequest defaultRequest = SPOTIFY_API
          .getUsersSavedTracks()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/library/GetUsersSavedTracksRequest.json"))
          .build();

  public GetUsersSavedTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<SavedTrack>) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<SavedTrack> savedTrackPaging) {
    assertEquals(
            "https://api.spotify.com/v1/me/tracks?offset=0&limit=20",
            savedTrackPaging.getHref());
    assertEquals(
            1,
            savedTrackPaging.getItems().length);
    assertEquals(
            20,
            (int) savedTrackPaging.getLimit());
    assertEquals(
            "https://api.spotify.com/v1/me/tracks?offset=20&limit=20",
            savedTrackPaging.getNext());
    assertEquals(
            0,
            (int) savedTrackPaging.getOffset());
    assertNull(
            savedTrackPaging.getPrevious());
    assertEquals(
            53,
            (int) savedTrackPaging.getTotal());
  }
}
