package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedAlbum;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetCurrentUsersSavedAlbumsRequestTest extends AbstractDataTest<Paging<SavedAlbum>> {
  private final GetCurrentUsersSavedAlbumsRequest defaultRequest = SPOTIFY_API.getCurrentUsersSavedAlbums()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/library/GetCurrentUsersSavedAlbumsRequest.json"))
          .limit(LIMIT)
          .market(MARKET)
          .offset(OFFSET)
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
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Paging<SavedAlbum>) defaultRequest.executeAsync().get());
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
