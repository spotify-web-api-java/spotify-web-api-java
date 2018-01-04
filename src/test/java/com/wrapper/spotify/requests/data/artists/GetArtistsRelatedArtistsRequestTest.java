package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Artist;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetArtistsRelatedArtistsRequestTest {

  @Test
  public void shouldGetRelatedArtists_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetArtistsRelatedArtistsRequest request = api
            .getArtistsRelatedArtists("0qeei9KQnptjwb8MgkqEoy")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/artists/GetArtistsRelatedArtistsRequest.json"))
            .build();

    final Future<Artist[]> requestFuture = request.executeAsync();
    final Artist[] artists = requestFuture.get();

    assertFalse(artists.length == 0);
    final Artist firstArtist = artists[0];
    final String id = firstArtist.getId();
    assertEquals("https://api.spotify.com/v1/artists/" + id, firstArtist.getHref());
    assertEquals(id, firstArtist.getId());
    assertEquals("spotify:artist:" + id, firstArtist.getUri());
  }

  @Test
  public void shouldGetRelatedArtists_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetArtistsRelatedArtistsRequest request = api
            .getArtistsRelatedArtists("0qeei9KQnptjwb8MgkqEoy")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/artists/GetArtistsRelatedArtistsRequest.json"))
            .build();

    final Artist[] artists = request.execute();


    assertFalse(artists.length == 0);
    final Artist firstArtist = artists[0];
    final String id = firstArtist.getId();
    assertEquals("https://api.spotify.com/v1/artists/" + id, firstArtist.getHref());
    assertEquals(id, firstArtist.getId());
    assertEquals("spotify:artist:" + id, firstArtist.getUri());
  }


}
