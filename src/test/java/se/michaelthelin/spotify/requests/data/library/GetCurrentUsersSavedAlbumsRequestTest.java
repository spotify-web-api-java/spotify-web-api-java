package se.michaelthelin.spotify.requests.data.library;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.SavedAlbum;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetCurrentUsersSavedAlbumsRequestTest extends AbstractDataTest<Paging<SavedAlbum>> {
  private final GetCurrentUsersSavedAlbumsRequest defaultRequest = ITest.SPOTIFY_API.getCurrentUsersSavedAlbums()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/library/GetCurrentUsersSavedAlbumsRequest.json"))
    .limit(ITest.LIMIT)
    .market(ITest.MARKET)
    .offset(ITest.OFFSET)
    .build();

  public GetCurrentUsersSavedAlbumsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/albums?limit=10&market=SE&offset=0",
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

  public void shouldReturnDefault(final Paging<SavedAlbum> savedAlbumPaging) {
    assertEquals(
      "https://api.spotify.com/v1/me/albums?offset=0&limit=1",
      savedAlbumPaging.getHref());
    assertEquals(
      1,
      savedAlbumPaging.getItems().length);
    assertEquals(
      1,
      (int) savedAlbumPaging.getLimit());
    assertEquals(
      "https://api.spotify.com/v1/me/albums?offset=1&limit=1",
      savedAlbumPaging.getNext());
    assertEquals(
      0,
      (int) savedAlbumPaging.getOffset());
    assertNull(
      savedAlbumPaging.getPrevious());
    assertEquals(
      19,
      (int) savedAlbumPaging.getTotal());
  }
}
