package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Artist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetSeveralArtistsRequestTest {

  @Test
  public void shouldGetArtistsResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetSeveralArtistsRequest request = api.getSeveralArtists("0oSGxfWSnnOXhD2fKuz2Gy", "3dBVyJ7JuOMt4GE9607Qin")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/artists/GetSeveralArtistsRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final Future<Artist[]> requestFuture = request.executeAsync();
    final Artist[] artists = requestFuture.get();

    assertEquals(2, artists.length);

    final Artist firstArtist = artists[0];
    final Artist secondArtist = artists[1];

    assertEquals("0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getId());
    assertEquals("3dBVyJ7JuOMt4GE9607Qin", secondArtist.getId());
  }

  @Test
  public void shouldGetArtistsResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetSeveralArtistsRequest request = api.getSeveralArtists("0oSGxfWSnnOXhD2fKuz2Gy", "3dBVyJ7JuOMt4GE9607Qin")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/artists/GetSeveralArtistsRequest.json"))
            .build();

    final Artist[] artists = request.execute();

    assertEquals(2, artists.length);

    final Artist firstArtist = artists[0];
    final Artist secondArtist = artists[1];

    assertEquals("0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getId());
    assertEquals("3dBVyJ7JuOMt4GE9607Qin", secondArtist.getId());
  }

}
