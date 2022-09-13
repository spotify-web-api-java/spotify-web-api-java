package se.michaelthelin.spotify.requests.data.albums;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetSeveralAlbumsRequestTest extends AbstractDataTest<Album[]> {

  private final GetSeveralAlbumsRequest defaultRequest = SPOTIFY_API.getSeveralAlbums(ID_ALBUM, ID_ALBUM)
    .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetSeveralAlbumsRequest.json"))
    .build();

  private final GetSeveralAlbumsRequest emptyRequest = SPOTIFY_API.getSeveralAlbums(ID_ALBUM, ID_ALBUM)
    .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetSeveralAlbumsRequest_None.json"))
    .build();

  public GetSeveralAlbumsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/albums?ids=5zT1JLIj9E57p3e1rFm9Uq%2C5zT1JLIj9E57p3e1rFm9Uq",
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

  public void shouldReturnDefault(final Album[] albums) {
    assertEquals(
      1,
      albums.length);
  }

  @Test
  public void shouldReturnEmpty_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnEmpty(emptyRequest.execute());
  }

  @Test
  public void shouldReturnEmpty_async() throws ExecutionException, InterruptedException {
    shouldReturnEmpty(emptyRequest.executeAsync().get());
  }

  public void shouldReturnEmpty(final Album[] albums) {
    assertEquals(
      0,
      albums.length);
  }
}
