package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Followers;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetArtistRequestTest extends AbstractDataTest<Artist> {
  private final GetArtistRequest defaultRequest = SPOTIFY_API.getArtist(ID_ARTIST)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/artists/GetArtistRequest.json"))
          .build();

  public GetArtistRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Artist) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Artist artist) {
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
