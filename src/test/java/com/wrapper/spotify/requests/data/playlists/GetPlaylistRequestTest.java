package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Playlist;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/*
 * TODO: Add negative tests
 */
public class GetPlaylistRequestTest {

  @Test
  public void shouldCreatePlaylistPage_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetPlaylistRequest request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetPlaylistRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final Future<Playlist> requestFuture = request.executeAsync();
    final Playlist playlist = requestFuture.get();

    assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn", playlist.getHref());

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldCreatePlaylistPage() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetPlaylistRequest request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetPlaylistRequest.json"))
            .build();

    final Playlist playlist = request.execute();

    assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn", playlist.getHref());
  }

  @Test
  public void shouldBeAbleToHandlePlaylistsWithLocalFiles() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetPlaylistRequest request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetPlaylistRequest_Local.json"))
            .build();

    Playlist playlist = request.execute();

    assertTrue(playlist.getTracks().getItems()[0].getTrack().getAlbum().getAlbumType() == null);

    assertNotNull(playlist);
  }

  @Ignore
  @Test
  public void shouldFailFutureIfPlaylistIsNotFound() {
  }

  @Test
  @Ignore
  public void shouldFailFutureIfNotAllowedAccess() {
  }

  @Test
  @Ignore
  public void shouldFailFutureIfUserDoesNotExist() {
  }

  @Test
  @Ignore
  public void shouldThrowExceptionIfPlaylistIsNotFound() {
  }

  @Test
  @Ignore
  public void shouldThrowExceptionIfNotAllowedAccess() {
  }

}
