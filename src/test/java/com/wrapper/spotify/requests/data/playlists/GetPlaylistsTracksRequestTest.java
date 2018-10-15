package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetPlaylistsTracksRequestTest extends AbstractDataTest<Paging<PlaylistTrack>> {
  private final GetPlaylistsTracksRequest defaultRequest = SPOTIFY_API
          .getPlaylistsTracks(ID_PLAYLIST)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/GetPlaylistsTracksRequest.json"))
          .fields(FIELDS)
          .limit(LIMIT)
          .market(MARKET)
          .offset(OFFSET)
          .build();

  public GetPlaylistsTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks?fields=description&limit=10&market=SE&offset=0",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<PlaylistTrack>) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<PlaylistTrack> playlistTrackPaging) {
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
