package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedTrack;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersSavedTracksRequestTest extends AbstractDataTest<Paging<SavedTrack>> {
  private final GetUsersSavedTracksRequest defaultRequest = SPOTIFY_API.getUsersSavedTracks()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/library/GetUsersSavedTracksRequest.json"))
          .limit(LIMIT)
          .market(MARKET)
          .offset(OFFSET)
          .build();

  public GetUsersSavedTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/me/tracks?limit=10&market=SE&offset=0",
            defaultRequest.getUri().toString());
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
