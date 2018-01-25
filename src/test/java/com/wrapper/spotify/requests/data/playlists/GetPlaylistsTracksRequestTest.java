package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetPlaylistsTracksRequestTest implements ITest<Paging<PlaylistTrack>> {
  private final GetPlaylistsTracksRequest successRequest = SPOTIFY_API
          .getPlaylistsTracks("user_id", "playlist_id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/GetPlaylistsTracksRequest.json"))
          .build();

  public GetPlaylistsTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Paging<PlaylistTrack>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Paging<PlaylistTrack> playlistTrackPaging) {
    assertEquals(
            "https://api.spotify.com/v1/users/spotify_espa%C3%B1a/playlists/21THa8j9TaSGuXYNBU5tsC/tracks",
            playlistTrackPaging.getHref());
    assertEquals(
            2,
            playlistTrackPaging.getItems().length);
    assertEquals(
            100,
            (int) playlistTrackPaging.getLimit());
    assertNull(
            playlistTrackPaging.getNext());
    assertEquals(
            0,
            (int) playlistTrackPaging.getOffset());
    assertNull(
            playlistTrackPaging.getPrevious());
    assertEquals(
            58,
            (int) playlistTrackPaging.getTotal());
  }
}
