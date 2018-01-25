package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Followers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetArtistRequestTest implements ITest<Artist> {
  private final GetArtistRequest successRequest = SPOTIFY_API.getArtist("id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/artists/GetArtistRequest.json"))
          .build();

  public GetArtistRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Artist) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Artist artist) {
    assertEquals(
            "https://open.spotify.com/artist/0OdUWJ0sBjDrqHygGUXeCF",
            artist.getExternalUrls().get("spotify"));
    assertNotNull(
            artist.getFollowers());
    assertEquals(
            "indie folk",
            artist.getGenres()[0]);
    assertEquals(
            "https://api.spotify.com/v1/artists/0OdUWJ0sBjDrqHygGUXeCF",
            artist.getHref());
    assertEquals(
            "0OdUWJ0sBjDrqHygGUXeCF",
            artist.getId());
    assertEquals(
            4,
            artist.getImages().length);
    assertEquals(
            "Band of Horses",
            artist.getName());
    assertEquals(
            59,
            (int) artist.getPopularity());
    assertEquals(
            ModelObjectType.ARTIST,
            artist.getType());
    assertEquals(
            "spotify:artist:0OdUWJ0sBjDrqHygGUXeCF",
            artist.getUri());
  }

  private void assertNotNull(Followers followers) {
  }
}
