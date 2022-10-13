package se.michaelthelin.spotify.requests.data.artists;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetArtistRequestTest extends AbstractDataTest<Artist> {
  private final GetArtistRequest defaultRequest = ITest.SPOTIFY_API.getArtist(ITest.ID_ARTIST)
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
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
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
}
