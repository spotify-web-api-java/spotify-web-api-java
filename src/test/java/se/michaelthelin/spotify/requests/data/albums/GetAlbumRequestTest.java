package se.michaelthelin.spotify.requests.data.albums;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.enums.ReleaseDatePrecision;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class GetAlbumRequestTest extends AbstractDataTest<Album> {
  private final GetAlbumRequest defaultRequest = SPOTIFY_API.getAlbum(ID_ALBUM)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/albums/GetAlbumRequest.json"))
    .market(MARKET)
    .build();

  public GetAlbumRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/albums/5zT1JLIj9E57p3e1rFm9Uq?market=SE",
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

  public void shouldReturnDefault(final Album album) {
    assertEquals(
      AlbumType.ALBUM,
      album.getAlbumType());
    assertNotNull(
      album.getArtists());
    assertEquals(
      57,
      album.getAvailableMarkets().length);
    assertEquals(
      1,
      album.getCopyrights().length);
    assertEquals(
      "P",
      album.getCopyrights()[0].getType().toString());
    assertNotNull(
      album.getExternalIds());
    assertNotNull(
      album.getExternalUrls());
    assertEquals(
      0,
      album.getGenres().length);
    assertEquals(
      "https://api.spotify.com/v1/albums/0sNOF9WDwhWunNAHPD3Baj",
      album.getHref());
    assertEquals(
      "0sNOF9WDwhWunNAHPD3Baj",
      album.getId());
    assertEquals(
      3,
      album.getImages().length);
    assertNull(
      album.getLabel());
    assertEquals(
      "She's So Unusual",
      album.getName());
    assertEquals(
      39,
      (int) album.getPopularity());
    assertEquals(
      "1983",
      album.getReleaseDate());
    assertEquals(
      ReleaseDatePrecision.YEAR,
      album.getReleaseDatePrecision());
    assertNotNull(
      album.getTracks());
    assertEquals(
      ModelObjectType.ALBUM,
      album.getType());
    assertEquals(
      "spotify:album:0sNOF9WDwhWunNAHPD3Baj",
      album.getUri());
  }
}
