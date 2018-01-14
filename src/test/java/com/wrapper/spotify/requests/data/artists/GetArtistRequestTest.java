package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
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
            "https://open.spotify.com/artist/0TnOYISbd1XYRBk9myaseg",
            artist.getExternalUrls().get("spotify"));
    assertEquals(
            4715206,
            (int) artist.getFollowers().getTotal());
    assertEquals(
            "dance pop",
            artist.getGenres()[0]);
    assertEquals(
            "https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg",
            artist.getHref());
    assertEquals(
            "0TnOYISbd1XYRBk9myaseg",
            artist.getId());
    assertEquals(
            3,
            artist.getImages().length);
    assertEquals(
            "Pitbull",
            artist.getName());
    assertEquals(
            85,
            (int) artist.getPopularity());
    assertEquals(
            ModelObjectType.ARTIST,
            artist.getType());
    assertEquals(
            "spotify:artist:0TnOYISbd1XYRBk9myaseg",
            artist.getUri());
  }
}
