package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class GetPlaylistsTracksRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    String accessToken = "someToken";
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken)
            .build();

    final GetPlaylistsTracksRequest request = api
            .getPlaylistsTracks("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetPlaylistsTracksRequest.json"))
            .build();

    final Future<Paging<PlaylistTrack>> requestFuture = request.executeAsync();
    final Paging<PlaylistTrack> playlistTrackPaging = requestFuture.get();

    assertNotNull(playlistTrackPaging);
    assertEquals(
            "https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn/tracks?offset=0&limit=100",
            playlistTrackPaging.getHref());
    assertEquals(100, (int) playlistTrackPaging.getLimit());
    assertNull(playlistTrackPaging.getNext());
    assertEquals(0, (int) playlistTrackPaging.getOffset());
    assertNull(playlistTrackPaging.getPrevious());
    assertTrue(playlistTrackPaging.getTotal() > 0);

    final PlaylistTrack playlistTrack = playlistTrackPaging.getItems()[0];
    assertNotNull(playlistTrack.getAddedAt());
    assertNotNull(playlistTrack.getAddedBy());

    final Track track = playlistTrack.getTrack();
    assertTrue(track.getPopularity() >= 0);
  }

  @Test
  public void shouldGetTracksResult_sync() throws Exception {
    String accessToken = "someToken";
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetPlaylistsTracksRequest request = api
            .getPlaylistsTracks("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetPlaylistsTracksRequest.json"))
            .build();

    final Paging<PlaylistTrack> page = request.execute();

    assertNotNull(page);
    assertEquals(
            "https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn/tracks?offset=0&limit=100",
            page.getHref());
    assertEquals(100, (int) page.getLimit());
    assertNull(page.getNext());
    assertEquals(0, (int) page.getOffset());
    assertNull(page.getPrevious());
    assertTrue(page.getTotal() > 0);

    final PlaylistTrack playlistTrack = page.getItems()[0];
    assertNotNull(playlistTrack.getAddedAt());
    assertNotNull(playlistTrack.getAddedBy());

    final Track track = playlistTrack.getTrack();
    assertTrue(track.getPopularity() >= 0);
  }
}
