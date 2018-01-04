package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedTrack;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetUsersSavedTracksRequestTest {

  @Test
  public void shouldGetSavedTracks_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("someAccessToken").build();

    final GetUsersSavedTracksRequest request = api.getUsersSavedTracks()
            .limit(5)
            .offset(1)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/library/GetUsersSavedTracksRequest.json"))
            .build();

    final Future<Paging<SavedTrack>> requestFuture = request.executeAsync();
    final Paging<SavedTrack> savedTrackPaging = requestFuture.get();

    assertNotNull(savedTrackPaging);

    assertEquals("https://api.spotify.com/v1/me/tracks?offset=1&limit=1", savedTrackPaging.getHref());

    SavedTrack[] items = savedTrackPaging.getItems();
    assertEquals(1, items.length);

    SavedTrack firstItem = savedTrackPaging.getItems()[0];
    assertNotNull(firstItem.getAddedAt());
    assertNotNull(firstItem.getTrack());
    assertEquals("1bhUWB0zJMIKr9yVPrkEuI", firstItem.getTrack().getId());
  }

  @Test
  public void shouldGetSavedTracks_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("someAccessToken").build();

    final GetUsersSavedTracksRequest request = api.getUsersSavedTracks()
            .limit(5)
            .offset(1)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/library/GetUsersSavedTracksRequest.json"))
            .build();

    final Paging<SavedTrack> libraryTracks = request.execute();

    assertNotNull(libraryTracks);

    assertEquals("https://api.spotify.com/v1/me/tracks?offset=1&limit=1", libraryTracks.getHref());

    SavedTrack[] items = libraryTracks.getItems();
    assertEquals(1, items.length);

    SavedTrack firstItem = libraryTracks.getItems()[0];
    assertNotNull(firstItem.getAddedAt());
    assertNotNull(firstItem.getTrack());
    assertEquals("1bhUWB0zJMIKr9yVPrkEuI", firstItem.getTrack().getId());
  }

}
