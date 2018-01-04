package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Artist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetArtistRequestTest {

  @Test
  public void shouldGetArtistResult_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    GetArtistRequest request = api.getArtist("2BTZIqw0ntH9MvilQ3ewNY")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/artists/GetArtistRequest.json"))
            .build();

    final Future<Artist> requestFuture = request.executeAsync();
    final Artist artist = requestFuture.get();

    assertNotNull(artist);
    assertEquals("https://open.spotify.com/artist/2BTZIqw0ntH9MvilQ3ewNY", artist.getExternalUrls().get("spotify"));
    assertNotNull(artist.getGenres());
    assertEquals("2BTZIqw0ntH9MvilQ3ewNY", artist.getId());
    assertNotNull(artist.getImages());
    assertNotNull(artist.getFollowers());
    assertTrue(artist.getFollowers().getTotal() >= 0);
    assertEquals("Cyndi Lauper", artist.getName());
    assertTrue(artist.getPopularity() >= 0 && artist.getPopularity() <= 100);
    assertEquals("spotify:artist:2BTZIqw0ntH9MvilQ3ewNY", artist.getUri());
  }

  @Test
  public void shouldGetArtistResult_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();
    GetArtistRequest request = api.getArtist("2BTZIqw0ntH9MvilQ3ewNY")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/artists/GetArtistRequest.json"))
            .build();

    final Artist artist = request.execute();

    assertNotNull(artist);
    assertEquals("https://open.spotify.com/artist/2BTZIqw0ntH9MvilQ3ewNY", artist.getExternalUrls().get("spotify"));
    assertNotNull(artist.getGenres());
    assertEquals("2BTZIqw0ntH9MvilQ3ewNY", artist.getId());
    assertNotNull(artist.getImages());
    assertEquals("Cyndi Lauper", artist.getName());
    assertTrue(artist.getPopularity() >= 0 && artist.getPopularity() <= 100);
    assertEquals("spotify:artist:2BTZIqw0ntH9MvilQ3ewNY", artist.getUri());
  }

}
