package com.wrapper.spotify.requests.data.artists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetArtistsTopTracksRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetArtistsTopTracksRequest request = api.getArtistsTopTracks("43ZHCT0cAZBISjO8DG9PnE", CountryCode.GB)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/artists/GetArtistsTopTracksRequest.json"))
            .build();

    final Future<Track[]> requestFuture = request.executeAsync();
    final Track[] tracks = requestFuture.get();

    assertTrue(tracks.length > 0);

    Track firstTrack = tracks[0];

    assertNotNull(firstTrack.getAlbum());
    assertNotNull(firstTrack.getArtists());
    assertNotNull(firstTrack.getAvailableMarkets());
    assertTrue(firstTrack.getDiscNumber() > 0);
    assertTrue(firstTrack.getDurationMs() > 0);
    assertNotNull(firstTrack.getIsExplicit());
    assertNotNull(firstTrack.getExternalIds());

    String id = firstTrack.getId();
    assertNotNull(firstTrack.getId());
    assertEquals("https://open.spotify.com/track/" + id, firstTrack.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());
    assertTrue(firstTrack.getPopularity() >= 0 && firstTrack.getPopularity() <= 100);
    assertNotNull(firstTrack.getPreviewUrl());
    assertTrue(firstTrack.getTrackNumber() >= 0);
    assertEquals(ModelObjectType.TRACK, firstTrack.getType());
    assertEquals("spotify:track:" + id, firstTrack.getUri());
  }

  @Test
  public void shouldGetTracksResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetArtistsTopTracksRequest request = api.getArtistsTopTracks("43ZHCT0cAZBISjO8DG9PnE", CountryCode.GB)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/artists/GetArtistsTopTracksRequest.json"))
            .build();

    final Track[] tracks = request.execute();

    assertTrue(tracks.length > 0);

    Track firstTrack = tracks[0];

    assertNotNull(firstTrack.getAlbum());
    assertNotNull(firstTrack.getArtists());
    assertNotNull(firstTrack.getAvailableMarkets());
    assertTrue(firstTrack.getDiscNumber() > 0);
    assertTrue(firstTrack.getDurationMs() > 0);
    assertNotNull(firstTrack.getIsExplicit());
    assertNotNull(firstTrack.getExternalIds());

    String id = firstTrack.getId();
    assertNotNull(firstTrack.getId());
    assertEquals("https://open.spotify.com/track/" + id, firstTrack.getExternalUrls().get("spotify"));
    assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());
    assertTrue(firstTrack.getPopularity() >= 0 && firstTrack.getPopularity() <= 100);
    assertNotNull(firstTrack.getPreviewUrl());
    assertTrue(firstTrack.getTrackNumber() >= 0);
    assertEquals(ModelObjectType.TRACK, firstTrack.getType());
    assertEquals("spotify:track:" + id, firstTrack.getUri());
  }

}
