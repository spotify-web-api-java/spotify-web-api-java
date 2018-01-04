package com.wrapper.spotify.requests.data.search;

import com.wrapper.spotify.IHttpManager;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrackSearchRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final SearchTracksRequest request = api.searchTracks("tania bowra")
            .offset(0)
            .limit(20)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/search/TrackSearchRequest.json"))
            .build();

    final Future<Paging<Track>> requestFuture = request.executeAsync();
    final Paging<Track> trackPaging = requestFuture.get();

    assertTrue(trackPaging.getTotal() > 0);
    assertEquals(20, (int) trackPaging.getLimit());
    assertEquals(0, (int) trackPaging.getOffset());

    Track[] tracks = trackPaging.getItems();

    Track firstTrack = tracks[0];
    assertNotNull(firstTrack.getId());

    String id = firstTrack.getId();
    assertNotNull(firstTrack.getAlbum());
    assertNotNull(firstTrack.getArtists());
    assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());
  }

  @Test
  public void shouldGetTracksResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();
    final IHttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("requests/data/search/TrackSearchRequest.json");
    final SearchTracksRequest request = api.searchTracks("Mr. Brightside").setHttpManager(mockedHttpManager).build();

    final Paging<Track> trackSearchResult = request.execute();

    assertTrue(trackSearchResult.getTotal() > 0);
    assertEquals(20, (int) trackSearchResult.getLimit());
    assertEquals(0, (int) trackSearchResult.getOffset());

    Track[] tracks = trackSearchResult.getItems();

    Track firstTrack = tracks[0];
    assertNotNull(firstTrack.getId());

    String id = firstTrack.getId();
    assertNotNull(firstTrack.getAlbum());
    assertNotNull(firstTrack.getArtists());
    assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());
  }

}
