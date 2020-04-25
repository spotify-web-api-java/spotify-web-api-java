package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Episode;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.apache.hc.core5.http.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetPlaylistsItemsRequestTest extends AbstractDataTest<Paging<PlaylistTrack>> {
  private final GetPlaylistsItemsRequest defaultRequest = SPOTIFY_API
    .getPlaylistsItems(ID_PLAYLIST)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/GetPlaylistsItemsRequest.json"))
    .fields(FIELDS)
    .limit(LIMIT)
    .market(MARKET)
    .offset(OFFSET)
    .additionalTypes(ADDITIONAL_TYPES)
    .build();

  private final GetPlaylistsItemsRequest defaultEpisodeRequest = SPOTIFY_API
    .getPlaylistsItems(ID_PLAYLIST)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/GetPlaylistsItemsRequest_Episode.json"))
    .fields(FIELDS)
    .limit(LIMIT)
    .market(MARKET)
    .offset(OFFSET)
    .additionalTypes(ADDITIONAL_TYPES)
    .build();

  public GetPlaylistsItemsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks?fields=description&limit=10&market=SE&offset=0&additional_types=track%2Cepisode",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<PlaylistTrack> playlistTrackPaging) {
    assertEquals(
      "https://api.spotify.com/v1/users/spotify_espa%C3%B1a/playlists/21THa8j9TaSGuXYNBU5tsC/tracks",
      playlistTrackPaging.getHref());
    assertEquals(
      2,
      playlistTrackPaging.getItems().length);
    assertTrue(
      playlistTrackPaging.getItems()[0].getTrack() instanceof Track);
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

  @Test
  public void shouldReturnDefaultEpisode_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefaultEpisode(defaultEpisodeRequest.execute());
  }

  @Test
  public void shouldReturnDefaultEpisode_async() throws ExecutionException, InterruptedException {
    shouldReturnDefaultEpisode(defaultEpisodeRequest.executeAsync().get());
  }

  public void shouldReturnDefaultEpisode(final Paging<PlaylistTrack> playlistTrackPaging) {
    assertEquals(
      "https://api.spotify.com/v1/playlists/21THa8j9TaSGuXYNBU5tsC/tracks?offset=0&limit=100&additional_types=episode",
      playlistTrackPaging.getHref());
    assertEquals(
      1,
      playlistTrackPaging.getItems().length);
    assertTrue(
      playlistTrackPaging.getItems()[0].getTrack() instanceof Episode);
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
